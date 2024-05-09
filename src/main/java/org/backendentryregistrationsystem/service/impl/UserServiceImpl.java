package org.backendentryregistrationsystem.service.impl;

import org.backendentryregistrationsystem.model.UserEntity;
import org.backendentryregistrationsystem.repository.RoleRepository;
import org.backendentryregistrationsystem.repository.UserRepository;
import org.backendentryregistrationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void deleteUser(UUID id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.getRoles().clear();
            userRepository.deleteById(id);
        }
    }

    @Override
    public Optional<UserEntity> getUser(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }
}
