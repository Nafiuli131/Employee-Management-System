package com.example.databaseexam.ResponseDto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryResponse {
    private Long id;
    private String employeeName;
    private Integer month;
    private Integer year;
    private int totalWorkingDays;
    private int totalAttendance;
    private Double percentages;
    private Double allocatedSalary;
}
