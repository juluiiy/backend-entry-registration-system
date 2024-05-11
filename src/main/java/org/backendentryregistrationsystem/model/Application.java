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
    @Column(name = "motivation_letter")
    private String motivationLetter;

    @Column(name = "user_id")
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "specialty_applications",
            joinColumns = @JoinColumn(name = "application_id", referencedColumnName = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id", referencedColumnName = "specialty_id"))
    private Specialty specialty;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "application_nmt_results",
            joinColumns = @JoinColumn(name = "application_id", referencedColumnName = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "nmt_results_id", referencedColumnName = "nmt_results_id"))
    private List<NmtResult> nmtResults;
}
