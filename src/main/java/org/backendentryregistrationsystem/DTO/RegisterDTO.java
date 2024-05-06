package org.backendentryregistrationsystem.DTO;

import lombok.Data;

@Data
public class RegisterDTO {
    public String email;
    public String password;
    public String username;
    public String phoneNumber;
}
