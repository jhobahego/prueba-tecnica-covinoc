package com.covinoc.services;

import com.covinoc.models.entities.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    void deleteById(Long id);
}
