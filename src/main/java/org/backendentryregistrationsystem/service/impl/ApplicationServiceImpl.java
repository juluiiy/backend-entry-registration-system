package org.backendentryregistrationsystem.service.impl;

import org.backendentryregistrationsystem.DTO.ApplicationDTO;
import org.backendentryregistrationsystem.DTO.NmtResultDTO;
import org.backendentryregistrationsystem.model.Application;
import org.backendentryregistrationsystem.model.NmtResult;
import org.backendentryregistrationsystem.model.Specialty;
import org.backendentryregistrationsystem.model.UserEntity;
import org.backendentryregistrationsystem.repository.ApplicationRepository;
import org.backendentryregistrationsystem.repository.Specialtyrepository;
import org.backendentryregistrationsystem.repository.UserRepository;
import org.backendentryregistrationsystem.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private Specialtyrepository specialtyrepository;
    private ApplicationRepository applicationRepository;
    private UserRepository userRepository;

    @Autowired
    public ApplicationServiceImpl(Specialtyrepository specialtyrepository, ApplicationRepository applicationRepository,
                                  UserRepository userRepository) {
        this.specialtyrepository = specialtyrepository;
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void createApplication(UserEntity user, ApplicationDTO applicationDTO) {
        Application application = new Application();

        application.setUserId(user.getId());
        application.setApplied(false);
        application.setMotivationLetter(applicationDTO.getMotivationLetter());

        Specialty specialty = specialtyrepository.findById(applicationDTO.getSpecialtyId())
                .orElseThrow(() -> new RuntimeException("Specialty not found"));

        application.setSpecialty(specialty);

        List<NmtResult> nmtResults = new ArrayList<>();
        for (NmtResultDTO nmtResultDTO : applicationDTO.getNmtResults()) {
            NmtResult nmtResult = new NmtResult();
            nmtResult.setName(nmtResultDTO.getName());
            nmtResult.setValue(nmtResultDTO.getValue());
            nmtResults.add(nmtResult);
        }
        application.setNmtResults(nmtResults);

        user.getApplications().add(application);

        applicationRepository.save(application);
        userRepository.save(user);
    }

    @Override
    public void deleteApplication(UUID applicationId) {
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isPresent()) {
            Application application = applicationOptional.get();

            Optional<UserEntity> userOptional = userRepository.findById(application.getUserId());
            userOptional.ifPresent(user -> user.getApplications().remove(application));

            applicationRepository.deleteById(applicationId);
        }
    }

    @Override
    public void changeStatus(UUID applicationId, boolean status) {
        Optional<Application> application = applicationRepository.findById(applicationId);
        application.ifPresent(value -> value.setApplied(status));
    }
}
