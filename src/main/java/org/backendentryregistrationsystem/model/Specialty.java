package org.backendentryregistrationsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "specialties")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "specialty_id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "number")
    private int number;
    @Column(name = "number_of_courses")
    private int numberOfCourses;
    @Column(name = "price")
    private double price;
    @Column(name = "total_places")
    private int totalPlaces;
    @Column(name = "budget_places")
    private int budgetPlaces;
    @Column(name = "contract_places")
    private int contractPlaces;
    @Column(name = "form_of_education")
    private String formOfEducation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", referencedColumnName = "faculty_id")
    private Faculty faculty;
}
