package com.bootcamp.demo_restapi.service.impl;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo_restapi.entity.PostEntity;
import com.bootcamp.demo_restapi.infra.ApiUtil;
import com.bootcamp.demo_restapi.infra.Scheme;
import com.bootcamp.demo_restapi.model.Post;
import com.bootcamp.demo_restapi.model.mapper.Mapper;
import com.bootcamp.demo_restapi.repository.PostRepository;
import com.bootcamp.demo_restapi.service.PostService;

@Service
public class PostServiceHolder implements PostService {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private ApiUtil apiUtil;

  @Autowired
  private Mapper mapper;

  @Value("${api.url.endpoint.post}")
  private String postEndpoint;

  @Override
  public Post[] getPosts() {
    Post[] posts = restTemplate
        .getForObject(apiUtil.getUrl(Scheme.HTTPS, postEndpoint), Post[].class);

    if (postRepository.findAll().isEmpty()) {
      Arrays.asList(posts).stream()//
          .forEach(post -> {
            PostEntity postEntity = mapper.map(post);
            postRepository.save(postEntity);
          });
    } else {
      System.out.println("Already have data");
    }
    return restTemplate.getForObject(apiUtil.getUrl(Scheme.HTTP, postEndpoint),
        Post[].class);
  }

}
