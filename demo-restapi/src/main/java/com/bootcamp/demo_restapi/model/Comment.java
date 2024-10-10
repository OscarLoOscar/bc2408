package com.bootcamp.demo_restapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
  private Integer id;
  private String name;
  private String email;
  private String body;

}
