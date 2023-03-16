package com.example.databaseexam.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designation_id")
    private Designation designation;
    @Column(name = "designation_id", insertable = false, updatable = false)
    private Long designationId;

    private String familyInformation;
    private String address;
    private String nid;
    private LocalDate joiningDate;
    private String gender;



}
