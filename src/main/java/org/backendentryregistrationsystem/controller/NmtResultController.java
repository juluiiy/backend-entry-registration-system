package org.backendentryregistrationsystem.controller;

import org.backendentryregistrationsystem.model.NmtResult;
import org.backendentryregistrationsystem.model.UserEntity;
import org.backendentryregistrationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/nmt-results")
public class NmtResultController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addNmtResultToUser(@PathVariable UUID userId,
                                                @RequestBody List<NmtResult> nmtResults) {
        Optional<UserEntity> userOptional = userService.getUser(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            for (NmtResult nmtResult : nmtResults) {
                nmtResult.setName(nmtResult.getName());
                nmtResult.setValue(nmtResult.getValue());
                user.getNmtResults().add(nmtResult);
            }
            userService.updateUser(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteNmtResultFromUser(@PathVariable UUID userId) {
        Optional<UserEntity> userOptional = userService.getUser(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.getNmtResults().clear();
            userService.updateUser(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
