package com.covinoc.services;

import com.covinoc.models.dtos.UserDTO;
import com.covinoc.models.entities.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findById(Long id);

    User save(UserDTO userDTO);

    void update(Long id, UserDTO userDTO);

    void deleteById(Long id);
}
