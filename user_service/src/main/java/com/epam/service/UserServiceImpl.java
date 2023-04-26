package com.epam.service;

import com.epam.domain.User;
import com.epam.domain.UserCreateForm;
import com.epam.domain.UserUpdateForm;
import com.epam.exceptions.BadRequestException;
import com.epam.exceptions.NotFoundException;
import com.epam.repo.UserRepository;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {

    this.userRepository = userRepository;
  }

  @Override
  public User get(Long id) {

    Optional<User> optional = userRepository.findById(id);
    return optional.orElseThrow(() -> new NotFoundException());
  }

  @Override
  public List<User> getAll() {

    return userRepository.findAll();
  }

  @Override
  public User create(UserCreateForm userCreateForm) {

    validate(userCreateForm);
    String username = userCreateForm.getUsername();
    User user = new User();
    user.setUsername(username);
    return userRepository.save(user);
  }

  @Override
  public void delete(Long id) {

    User user = get(id);
    userRepository.delete(user);
  }

  @Override
  public User update(Long id, UserUpdateForm userUpdateForm){

    validate(userUpdateForm);
    User user = get(id);
    user.setUsername(userUpdateForm.getUsername());
    user.setAmountOfPosts(userUpdateForm.getAmountOfPosts());
    return userRepository.save(user);
  }

  private void validate(UserCreateForm userCreateForm) {

    String username = userCreateForm.getUsername();

    if (StringUtils.isBlank(username)) {
      throw new BadRequestException();
    }
  }

  private void validate(UserUpdateForm userUpdateForm) {

    String username = userUpdateForm.getUsername();

    if (StringUtils.isBlank(username)) {
      throw new BadRequestException();
    }

    Long amountOfPosts = userUpdateForm.getAmountOfPosts();

    if (amountOfPosts < 0 ) {
      throw new BadRequestException();
    }
  }
}
