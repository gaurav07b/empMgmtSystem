package com.ems.security.service;

import java.util.List;

import com.ems.dao.entity.User;
import com.ems.security.model.UserDto;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(long id);
}
