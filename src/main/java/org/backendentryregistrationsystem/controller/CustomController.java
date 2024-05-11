package org.backendentryregistrationsystem.controller;

import org.backendentryregistrationsystem.model.Faculty;
import org.backendentryregistrationsystem.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/custom")
public class CustomController {

    private final FacultyRepository facultyRepository;

    @Autowired
    public CustomController(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @PostMapping("/faculties")
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        facultyRepository.save(faculty);
        return new ResponseEntity<>(faculty, HttpStatus.CREATED);
    }
}