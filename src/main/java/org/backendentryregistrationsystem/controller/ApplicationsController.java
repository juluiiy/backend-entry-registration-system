package org.backendentryregistrationsystem.controller;

import org.backendentryregistrationsystem.DTO.ApplicationDTO;
import org.backendentryregistrationsystem.model.UserEntity;
import org.backendentryregistrationsystem.service.ApplicationService;
import org.backendentryregistrationsystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
public class ApplicationsController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationsController.class);

    private UserService userService;
    private ApplicationService applicationService;

    @Autowired
    public ApplicationsController(UserService userService, ApplicationService applicationService) {
        this.userService = userService;
        this.applicationService = applicationService;
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<?> createApplication(@PathVariable UUID userId,
                                               @RequestBody ApplicationDTO applicationDTO) {
        logger.info("Received application: {}", applicationDTO);
        Optional<UserEntity> userOptional = userService.getUser(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            applicationService.createApplication(user, applicationDTO);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<?> deleteApplication(@PathVariable UUID applicationId) {
        applicationService.deleteApplication(applicationId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{applicationId}")
    public ResponseEntity<?> changeStatus(@PathVariable UUID applicationId, @RequestParam boolean status) {
        applicationService.changeStatus(applicationId, status);
        return ResponseEntity.ok().build();
    }
}
