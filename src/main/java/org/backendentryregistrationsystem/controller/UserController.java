package org.backendentryregistrationsystem.controller;

import org.backendentryregistrationsystem.model.UserEntity;
import org.backendentryregistrationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserEntity> findAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> findUserById(@PathVariable UUID id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @PatchMapping("/{id}")
    public UserEntity updateUser(@RequestBody UserEntity user) {
       return userService.updateUser(user);
    }

}
