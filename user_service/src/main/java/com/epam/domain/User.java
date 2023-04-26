package com.epam.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  private String username;

  private Long amountOfPosts = 0L;

  public User() {

  }

  public User(Long id, String username, Long amountOfPosts) {

    this.id = id;
    this.username = username;
    this.amountOfPosts = amountOfPosts;
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
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
