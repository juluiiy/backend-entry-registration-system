package org.backendentryregistrationsystem.controller;

import org.backendentryregistrationsystem.model.Faculty;
import org.backendentryregistrationsystem.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping()
    public List<Faculty> findAllFaculties() {
        return facultyService.findAllFaculties();
    }
    @GetMapping("/{id}")
    public Optional<Faculty> findFacultyById(UUID id) {
        return facultyService.findFacultyById(id);
    }
}
