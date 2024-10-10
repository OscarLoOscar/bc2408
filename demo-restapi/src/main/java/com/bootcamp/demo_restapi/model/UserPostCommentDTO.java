package com.bootcamp.demo_restapi.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Builder
public class UserPostCommentDTO {
  private int userID;
  private String name;
  private String username;
  String email;
  AddressDTO address;
  String phone;
  String website;
  CompanyDTO company;
  @Builder.Default
  List<PostDTO> postDTO = new ArrayList<>();

  @Getter
  @Builder
  @ToString
  public static class AddressDTO {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDTO geo;

    @Getter
    @Builder
    @ToString
    public static class GeoDTO {
      private String lat;
      private String lng;
    }
  }

  @Getter
  @Builder
  @ToString
  public static class CompanyDTO {
    private String name;
    private String catchPhrase;
    private String bs;
  }

  @Data
  @Builder
  public static class PostDTO {
    private int postID;
    private String title;
    private String body;

    @Builder.Default
    private List<CommentDTO> commentDTO = new ArrayList<>();

  }

  @Data
  @Builder
  public static class CommentDTO {
    private int commentID;
    private String name;
    private String email;
    private String body;

  }
}
