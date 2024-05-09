package org.backendentryregistrationsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "applications")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "application_id")
    private UUID id;
    @Column(name = "is_applied")
    private boolean isApplied;
    @Column(name = "motivation_list")
    private String motivationList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id", referencedColumnName = "specialty_id")
    private Specialty specialty;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "nmt_results_id", referencedColumnName = "nmt_results_id")
    private List<NmtResult> nmtResults;
}
