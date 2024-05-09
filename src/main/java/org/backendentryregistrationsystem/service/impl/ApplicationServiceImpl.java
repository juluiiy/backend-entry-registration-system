package org.backendentryregistrationsystem.service.impl;

import org.backendentryregistrationsystem.DTO.ApplicationDTO;
import org.backendentryregistrationsystem.DTO.NmtResultDTO;
import org.backendentryregistrationsystem.model.Application;
import org.backendentryregistrationsystem.model.NmtResult;
import org.backendentryregistrationsystem.model.Specialty;
import org.backendentryregistrationsystem.model.UserEntity;
import org.backendentryregistrationsystem.repository.ApplicationRepository;
import org.backendentryregistrationsystem.repository.Specialtyrepository;
import org.backendentryregistrationsystem.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private Specialtyrepository specialtyrepository;
    private ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationServiceImpl(Specialtyrepository specialtyrepository, ApplicationRepository applicationRepository) {
        this.specialtyrepository = specialtyrepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public void createApplication(UserEntity user, ApplicationDTO applicationDTO) {
        Application application = new Application();
        application.setApplied(false);
        application.setMotivationList(applicationDTO.getMotivationLetter());

        Specialty specialty;
        specialty = specialtyrepository.findSpecialtyByNameAndEducationForm(applicationDTO.getSpecialtyName(),
                applicationDTO.getEducationForm());
        application.setSpecialty(specialty);
        for(NmtResultDTO nmtResultDTO : applicationDTO.getNmtResults()) {
            NmtResult nmtResult = new NmtResult();
            nmtResult.setName(nmtResultDTO.getName());
            nmtResult.setValue(nmtResultDTO.getValue());
            application.getNmtResults().add(nmtResult);
        }
        user.getApplications().add(application);
    }

    @Override
    public void deleteApplication(UUID applicationId) {
        applicationRepository.deleteById(applicationId);
    }

    @Override
    public void changeStatus(UUID applicationId, boolean status) {
        Optional<Application> application = applicationRepository.findById(applicationId);
        application.ifPresent(value -> value.setApplied(status));
    }
}
