package org.backendentryregistrationsystem.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.backendentryregistrationsystem.model.Application;
import org.backendentryregistrationsystem.model.NmtResult;
import org.backendentryregistrationsystem.model.Role;

import java.util.List;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String avatar;
    private List<NmtResult> nmtResults;
    private List<Application> applications;
    private List<Role> roles;
}
