package com.bootcamp.demo_api.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo_api.infra.ApiUtil;
import com.bootcamp.demo_api.infra.Scheme;
import com.bootcamp.demo_api.service.UserService;
import com.bootcamp.demo_api.model.User;

@Service
public class UserServiceImpl implements UserService {

  @Value("${api.endpoint.user}")
  String userEndpoint;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ApiUtil apiUtil;

  @Override
  public List<User> getUsers() {
    User[] userArr = restTemplate
        .getForObject(apiUtil.getUrl(Scheme.HTTPS, userEndpoint), User[].class);
    return this.convertArrToList(userArr);
  }

  private List<User> convertArrToList(User[] userArr) {
    return List.of(userArr);
  }

}
