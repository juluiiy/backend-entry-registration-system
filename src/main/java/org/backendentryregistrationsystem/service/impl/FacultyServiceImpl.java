package org.backendentryregistrationsystem.service.impl;

import org.backendentryregistrationsystem.model.Faculty;
import org.backendentryregistrationsystem.repository.FacultyRepository;
import org.backendentryregistrationsystem.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Faculty> findAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Optional<Faculty> findFacultyById(UUID id) {
        return facultyRepository.findById(id);
    }
}
