package com.epam.controller;

import com.epam.domain.User;
import com.epam.domain.UserCreateForm;
import com.epam.domain.UserUpdateForm;
import com.epam.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final String GREETING = "Hello, k8s!";

  @Autowired
  private final UserService userService;

  public UserController(UserService userService) {

    this.userService = userService;
  }

  @GetMapping("/greeting")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public String getGreeting() {

    return GREETING;
  }

  @GetMapping("/users/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public User get(@PathVariable Long id) {

    return userService.get(id);
  }

  @GetMapping("/users")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<User> getAll() {

    return userService.getAll();
  }

  @PostMapping("/users")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public User create(@RequestBody UserCreateForm userCreateForm) {

    return userService.create(userCreateForm);
  }

  @DeleteMapping("/users/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public void delete(@PathVariable Long id) {

    userService.delete(id);
  }

  @PutMapping("/users/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public User update(@PathVariable Long id, @RequestBody UserUpdateForm userUpdateForm) {

    return userService.update(id, userUpdateForm);
  }

}
