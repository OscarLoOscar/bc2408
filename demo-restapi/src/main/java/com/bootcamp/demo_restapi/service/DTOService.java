package com.bootcamp.demo_restapi.service;

import java.util.List;
import com.bootcamp.demo_restapi.model.UserPostCommentDTO;
import com.bootcamp.demo_restapi.model.UserPostDTO;

public interface DTOService {
  List<UserPostDTO> getUserPostDTO();

  List<UserPostCommentDTO> getAllUserPostComment();
}
