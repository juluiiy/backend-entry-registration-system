package org.backendentryregistrationsystem.service;

import org.backendentryregistrationsystem.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface FacultyService {
    public List<Faculty> findAllFaculties();
    public Optional<Faculty> findFacultyById(UUID id);
}
