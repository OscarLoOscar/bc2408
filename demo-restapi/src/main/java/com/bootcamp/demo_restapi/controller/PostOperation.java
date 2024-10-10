package com.bootcamp.demo_restapi.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bootcamp.demo_restapi.model.Post;

public interface PostOperation {
  @GetMapping(value = "/posts") // 查詢
  List<Post> getPosts();

  // @GetMapping(value = "/posts/{postNum}") // 查詢
  // Post getPosts(@PathVariable String postNum);
}
