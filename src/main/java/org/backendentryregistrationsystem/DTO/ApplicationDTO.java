package org.backendentryregistrationsystem.DTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ApplicationDTO {
    private UUID specialtyId;
    private String motivationLetter;
    private List<NmtResultDTO> nmtResults;
}
