package com.example.databaseexam.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private String name;
    private Long designationId;
    private String familyInformation;
    private String address;
    private String nid;
    private LocalDate joiningDate;
    private String gender;
}
