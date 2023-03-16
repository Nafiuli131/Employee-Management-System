package com.example.databaseexam.ResponseDto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeAttendanceResponse {
    private Long id;
    private Long employeeId;
    private Long attendanceId;
    private boolean isPresent;
    private LocalDate date;
}
