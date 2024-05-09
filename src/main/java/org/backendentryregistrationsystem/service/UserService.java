package org.backendentryregistrationsystem.service;

import org.backendentryregistrationsystem.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {
    void deleteUser(UUID id);
    Optional<UserEntity> getUser(UUID id);
    List<UserEntity> getAllUsers();
    UserEntity updateUser(UserEntity user);
}
