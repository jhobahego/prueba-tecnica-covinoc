package com.covinoc.services.impl;

import com.covinoc.models.dtos.UserDTO;
import com.covinoc.models.entities.User;
import com.covinoc.repository.UserRepository;
import com.covinoc.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll(Sort.sort(User.class).by(User::getName));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User save(UserDTO userRequest) {
        User user = User.builder()
                .name(userRequest.name())
                .phoneNumber(userRequest.phoneNumber())
                .build();

        return userRepository.save(user);
    }

    @Override
    public void update(Long id, UserDTO userRequest) {
        User user = findById(id);
        user.setName(userRequest.name());
        user.setPhoneNumber(userRequest.phoneNumber());

        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);

        userRepository.deleteById(id);
    }
}
