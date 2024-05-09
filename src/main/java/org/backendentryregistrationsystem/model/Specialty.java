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
    @Column(name = "courses_quantity")
    private int coursesQuantity;
    @Column(name = "price")
    private double price;
    @Column(name = "total_places")
    private int totalPlaces;
    @Column(name = "budget_places")
    private int budgetPlaces;
    @Column(name = "contract_places")
    private int contractPlaces;
    @Column(name = "education_form")
    private String educationForm;
}
