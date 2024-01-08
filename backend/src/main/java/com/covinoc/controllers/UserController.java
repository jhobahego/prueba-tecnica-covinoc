package com.covinoc.controllers;

import com.covinoc.models.dtos.UserDTO;
import com.covinoc.models.entities.User;
import com.covinoc.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
@RestController()
public class UserController {

    private final IUserService userService;

    @GetMapping(value = "/getUsers")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping(value = "/newUser")
    public ResponseEntity<User> saveUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
        User user = userService.save(userDTO);

        return ResponseEntity.created(new URI("/api/user/newUser")).body(user);
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userService.update(id, userDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
