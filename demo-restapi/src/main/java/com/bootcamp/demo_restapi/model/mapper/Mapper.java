package com.bootcamp.demo_restapi.model.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo_restapi.entity.CommentEntity;
import com.bootcamp.demo_restapi.entity.PostEntity;
import com.bootcamp.demo_restapi.entity.UserEntity;
import com.bootcamp.demo_restapi.model.Comment;
import com.bootcamp.demo_restapi.model.Post;
import com.bootcamp.demo_restapi.model.User;
import com.bootcamp.demo_restapi.model.User.Address;
import com.bootcamp.demo_restapi.model.User.Address.Geo;
import com.bootcamp.demo_restapi.model.User.Company;
import com.bootcamp.demo_restapi.model.UserPostCommentDTO;
import com.bootcamp.demo_restapi.model.UserPostCommentDTO.AddressDTO;
import com.bootcamp.demo_restapi.model.UserPostCommentDTO.AddressDTO.GeoDTO;
import com.bootcamp.demo_restapi.model.UserPostCommentDTO.CompanyDTO;
import com.bootcamp.demo_restapi.model.UserPostDTO;

@Component
public class Mapper {

  public UserEntity map(User user) {
    return UserEntity.builder()//
        .name(user.getName())//
        .username(user.getUsername())//
        .email(user.getEmail())//
        .phone(user.getPhone())//
        .website(user.getWebsite())//
        .dummy(String.valueOf(""))//
        .build();
  }

  public PostEntity map(Post post) {
    return PostEntity.builder()//
        .userId(post.getUserId())//
        .id(post.getId())//
        .title(post.getTitle())//
        .body(post.getBody())//
        .build();
  }

  public CommentEntity map(Comment comment) {
    return CommentEntity.builder()//
        .commentID(comment.getId())//
        .email(comment.getEmail())//
        .body(comment.getBody())//
        .build();
  }
  public UserPostDTO map(User user, Post post) {
    return UserPostDTO.builder()//
        .userId(user.getId())//
        .username(user.getUsername())//
        .useremail(user.getEmail())//
        .postid(post.getId())//
        .title(post.getTitle())//
        .body(post.getBody())//
        .build();
  }

  public GeoDTO mapToDTO(Geo geo) {
    return GeoDTO.builder()//
        .lat(geo.getLat())//
        .lng(geo.getLng())//
        .build();
  }

  public AddressDTO mapToDTO(Address address) {
    return AddressDTO.builder()//
        .street(address.getStreet())//
        .suite(address.getSuite())//
        .city(address.getCity())//
        .zipcode(address.getZipcode())//
        .geo(this.mapToDTO(address.getGeo()))//
        .build();
  }

  public CompanyDTO mapToDTO(Company company) {
    return CompanyDTO.builder()//
        .name(company.getName())//
        .catchPhrase(company.getCatchPhrase())//
        .bs(company.getBs())
        .build();
  }

  public UserPostCommentDTO mapToDTO(User user) {
    return UserPostCommentDTO.builder()//
        .name(user.getName())//
        .username(user.getUsername())//
        .email(user.getEmail())//
        .phone(user.getPhone())//
        .website(user.getWebsite())//
        .build();
  }

  public UserPostCommentDTO.PostDTO mapToDTO(Post post) {
    return UserPostCommentDTO.PostDTO.builder()//
        .postID(post.getId())//
        .title(post.getTitle())//
        .body(post.getBody())//
        .build();
  }

  public UserPostCommentDTO.CommentDTO mapToDTO(Comment comment) {
    return UserPostCommentDTO.CommentDTO.builder()//
        .commentID(comment.getId())//
        .name(comment.getName())//
        .email(comment.getEmail())//
        .body(comment.getBody())//
        .build();
  }
}
