package org.backendentryregistrationsystem.controller;

import org.backendentryregistrationsystem.DTO.UserDTO;
import org.backendentryregistrationsystem.model.UserEntity;
import org.backendentryregistrationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserDTO> findAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO findUserById(@PathVariable UUID id) {
        UserEntity user = userService.getUser(id).orElse(null);
        if (user != null) {
            return convertToDTO(user);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @PatchMapping("/{id}")
    public UserDTO updateUser(@RequestBody UserEntity user) {
        return convertToDTO(userService.updateUser(user));
    }

    private UserDTO convertToDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setNmtResults(user.getNmtResults());
        userDTO.setApplications(user.getApplications());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}