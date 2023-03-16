package com.example.databaseexam.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "salary_report")
public class SalaryReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employees employees;
    @Column(name = "employee_id", insertable = false, updatable = false)
    private Long employeeId;

    private Integer month;
    private Integer year;
    private int totalWorkingDays;
    private int totalAttendance;
    private Double percentages;
    private Double allocatedSalary;
    private LocalDate salaryGivenTime;
}
