package com.epam.service;

import com.epam.domain.User;
import com.epam.domain.UserCreateForm;
import com.epam.domain.UserUpdateForm;
import java.util.List;

public interface UserService {

  User get(Long id);

  List<User> getAll();

  User create(UserCreateForm userCreateForm);

  void delete(Long id);

  User update(Long id, UserUpdateForm userUpdateForm);

}
