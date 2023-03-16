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
public class EmplyeeAttendanceInformation {
    private Long employeeId;
    private LocalDate toDate;
    private LocalDate fromDate;
}
