package com.bootcamp.demo_restapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserPostDTO {
  private Integer userId;
  private String username;
  private String useremail;
  private Integer postid;
  private String title;
  private String body;
}
