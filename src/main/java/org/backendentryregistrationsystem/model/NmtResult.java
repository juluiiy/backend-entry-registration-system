package org.backendentryregistrationsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class NmtResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nmt_results_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private int value;
}
