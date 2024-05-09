package org.backendentryregistrationsystem.service;

import org.backendentryregistrationsystem.DTO.ApplicationDTO;
import org.backendentryregistrationsystem.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ApplicationService {
    void createApplication(UserEntity user, ApplicationDTO applicationDTO);
    void deleteApplication(UUID applicationId);
    void changeStatus(UUID applicationId, boolean status);
}
