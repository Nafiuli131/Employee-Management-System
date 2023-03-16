package com.example.databaseexam.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenerateSalaryRequest {
    private Long employeeId;
    private Integer month;
    private Integer year;
}
