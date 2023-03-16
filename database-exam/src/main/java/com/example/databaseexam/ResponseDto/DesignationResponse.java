package com.example.databaseexam.ResponseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DesignationResponse {
    private Long id;
    private String designationName;
    private Double salary;
}
