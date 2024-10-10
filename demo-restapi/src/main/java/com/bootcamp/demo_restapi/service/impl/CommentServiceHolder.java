package com.bootcamp.demo_restapi.service.impl;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo_restapi.entity.CommentEntity;
import com.bootcamp.demo_restapi.infra.ApiUtil;
import com.bootcamp.demo_restapi.infra.Scheme;
import com.bootcamp.demo_restapi.model.Comment;
import com.bootcamp.demo_restapi.model.mapper.Mapper;
import com.bootcamp.demo_restapi.repository.CommentRepository;
import com.bootcamp.demo_restapi.service.CommentService;

@Service
public class CommentServiceHolder implements CommentService {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ApiUtil apiUtil;

  @Autowired
  private Mapper mapper;
  
  @Autowired
  private CommentRepository commentRepository;

  @Value("${api.url.endpoint.comment}")
  private String commentEndpoint;

  @Override
  public Comment[] getComments() {
    Comment[] comments = restTemplate
        .getForObject(apiUtil.getUrl(Scheme.HTTPS, commentEndpoint), Comment[].class);

    if (commentRepository.findAll().isEmpty()) {
      Arrays.asList(comments).stream()//
          .forEach(comment -> {
            CommentEntity commentEntity = mapper.map(comment);
            commentRepository.save(commentEntity);
          });
    } else {
      System.out.println("Already have data");
    }
    return restTemplate.getForObject(
        apiUtil.getUrl(Scheme.HTTPS, commentEndpoint), Comment[].class);
  }

}
