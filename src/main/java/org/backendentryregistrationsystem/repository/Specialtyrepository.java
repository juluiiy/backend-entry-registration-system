package org.backendentryregistrationsystem.repository;

import org.backendentryregistrationsystem.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Specialtyrepository extends JpaRepository<Specialty, UUID> {

}
