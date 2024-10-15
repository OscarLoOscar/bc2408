package com.bootcamp.demo_restapi.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.demo_restapi.model.Comment;
import com.bootcamp.demo_restapi.model.Post;
import com.bootcamp.demo_restapi.model.User;
import com.bootcamp.demo_restapi.model.UserPostCommentDTO;
import com.bootcamp.demo_restapi.model.UserPostDTO;
import com.bootcamp.demo_restapi.model.mapper.Mapper;
import com.bootcamp.demo_restapi.service.CommentService;
import com.bootcamp.demo_restapi.service.DTOService;
import com.bootcamp.demo_restapi.service.PostService;
import com.bootcamp.demo_restapi.service.UserService;

@Service
public class DTOServiceHolder implements DTOService {
  @Autowired
  private CommentService commentService;

  @Autowired
  private PostService postService;

  @Autowired
  private UserService userService;

  @Autowired
  private Mapper mapper;

  @Override
  public List<UserPostDTO> getUserPostDTO() {
    List<UserPostDTO> result = new ArrayList<>();

    Arrays.stream(userService.getUsers()).forEach(user -> {
      Arrays.stream(postService.getPosts()).forEach(post -> {
        UserPostDTO userPostDTO = mapper.map(user, post);
        result.add(userPostDTO);
      });
    });
    return result;
  }

  @Override
  public List<UserPostCommentDTO> getAllUserPostComment() {
    List<User> userlists = List.of(this.userService.getUsers());
    List<Post> postlists = List.of(this.postService.getPosts());
    List<Comment> commentlists = List.of(this.commentService.getComments());

    List<UserPostCommentDTO> userPostCommentDTO = new ArrayList<>();

    // Approach 2
    for (int i = 0; i < userlists.size(); i++) {
      List<UserPostCommentDTO.PostDTO> userPosts = new ArrayList<>();
      for (int j = 0; j < postlists.size(); j++) {
        if (postlists.get(j).getUserId().equals(userlists.get(i).getId())) {
          List<UserPostCommentDTO.CommentDTO> postsComments = new ArrayList<>();
          for (int k = 0; k < commentlists.size(); k++) {
            if (commentlists.get(k).getId().equals(postlists.get(j).getId())) {
              UserPostCommentDTO.CommentDTO commentDTO =
                  mapper.mapToDTO(commentlists.get(k));
              postsComments.add(commentDTO);
            }
          } ;
          UserPostCommentDTO.PostDTO postDTO =
              mapper.mapToDTO(postlists.get(j));
          postDTO.setCommentDTO(postsComments);
          userPosts.add(postDTO);
        }
      } ;
      UserPostCommentDTO userDto = mapper.mapToDTO(userlists.get(i));
      userDto.setPostDTO(userPosts);
      userPostCommentDTO.add(userDto);
    } ;
    return userPostCommentDTO;
  };

}
