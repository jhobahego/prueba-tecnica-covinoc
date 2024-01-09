package com.covinoc.services.impl;

import com.covinoc.exceptions.BadRequestException;
import com.covinoc.exceptions.NotFoundException;
import com.covinoc.models.dtos.UserDTO;
import com.covinoc.models.entities.User;
import com.covinoc.repository.UserRepository;
import com.covinoc.services.IUserService;
import com.covinoc.utils.UserValidation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final Validator validator;

    @Override
    public List<User> findAll() {
        return userRepository.findAll(Sort.sort(User.class).by(User::getName));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    @Override
    public User save(UserDTO userRequest) {
        UserValidation.validateUser(userRequest, validator);

        userRepository.findByPhoneNumber(userRequest.phoneNumber())
                .ifPresent(user -> {
                    throw new BadRequestException("Phone number already registered");
                });

        User user = User.builder()
                .name(userRequest.name())
                .phoneNumber(userRequest.phoneNumber())
                .build();

        return userRepository.save(user);
    }

    @Override
    public void update(Long id, UserDTO userRequest) {
        UserValidation.validateUser(userRequest, validator);

        User user = findById(id);
        user.setName(userRequest.name());
        user.setPhoneNumber(userRequest.phoneNumber());

        userRepository.findByPhoneNumber(userRequest.phoneNumber())
                .ifPresent(userWithPhone -> {
                    if (!userWithPhone.getId().equals(id)) {
                        throw new BadRequestException("Phone number already registered");
                    }
                });

        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);

        userRepository.deleteById(id);
    }
}
