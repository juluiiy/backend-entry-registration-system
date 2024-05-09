package org.backendentryregistrationsystem.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationDTO {
    private String specialtyName;
    private String educationForm;
    private List<NmtResultDTO> nmtResults;
    private String motivationLetter;
}
