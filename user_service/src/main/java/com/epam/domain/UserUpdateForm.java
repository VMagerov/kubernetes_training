package com.epam.domain;

public class UserUpdateForm {

  private String username;

  private Long amountOfPosts;

  public UserUpdateForm() {

  }

  public UserUpdateForm(String username, Long amountOfPosts) {

    this.username = username;
    this.amountOfPosts = amountOfPosts;
  }

  public String getUsername() {

    return username;
  }

  public void setUsername(String username) {

    this.username = username;
  }

  public Long getAmountOfPosts() {

    return amountOfPosts;
  }

  public void setAmountOfPosts(Long amountOfPosts) {

    this.amountOfPosts = amountOfPosts;
  }
}
