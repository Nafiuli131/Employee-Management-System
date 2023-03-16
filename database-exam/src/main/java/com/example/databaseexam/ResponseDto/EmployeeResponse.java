package com.example.databaseexam.ResponseDto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {
    private Long id;
    private String name;
    private Long designationId;
    private String designationName;
    private Double salary;
    private String familyInformation;
    private String address;
    private String nid;
    private LocalDate joiningDate;
    private String gender;
}
