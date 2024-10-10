package com.bootcamp.demo_restapi.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.demo_restapi.model.UserPostCommentDTO;
import com.bootcamp.demo_restapi.model.UserPostDTO;

public interface DTOOperation {
   @GetMapping(value = "/usersposts") 
  List<UserPostDTO> getUsersPosts();

  @GetMapping(value = "/allUserPostComment") 
  List<UserPostCommentDTO> getAllUserPostComment();

}