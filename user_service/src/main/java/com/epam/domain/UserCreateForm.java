package com.epam.domain;

public class UserCreateForm {

  private String username;

  public UserCreateForm() {

  }

  public UserCreateForm(String username) {

    this.username = username;
  }

  public String getUsername() {

    return username;
  }

  public void setUsername(String username) {

    this.username = username;
  }
}
