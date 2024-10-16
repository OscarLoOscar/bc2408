package com.bootcamp.demo_restapi.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo_restapi.entity.UserEntity;
import com.bootcamp.demo_restapi.infra.ApiUtil;
import com.bootcamp.demo_restapi.infra.Scheme;
import com.bootcamp.demo_restapi.model.User;
import com.bootcamp.demo_restapi.model.mapper.Mapper;
import com.bootcamp.demo_restapi.repository.UserRepository;
import com.bootcamp.demo_restapi.service.UserService;

@Service
public class UserServiceHolder implements UserService {

  @Value("${api.url.endpoint.user}")
  private String userEndpoint;

  @Autowired
  private ApiUtil apiUtil;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private Mapper mapper;

  @Override
  public User[] getUsers() {
    User[] users = restTemplate
        .getForObject(apiUtil.getUrl(Scheme.HTTPS, userEndpoint), User[].class);


    if (userRepository.findAll().isEmpty()) {
      Arrays.asList(users).stream()//
          .forEach(user -> {
            UserEntity userEntity = mapper.map(user);
            userRepository.save(userEntity);
          });
    } else {
      System.out.println("Already have data");
    }

    return restTemplate.getForObject(apiUtil.getUrl(Scheme.HTTP, userEndpoint),
        User[].class);
  }

  @Override
  public User getUser(Long userNum) {
    return this.convertToList(this.getUsers()).stream()//
        .filter(user -> user.getId().equals(userNum)) //
        .findFirst()//
        .get();
  }

  private List<User> convertToList(User[] userArr) {
    return List.of(userArr);
  }

}
