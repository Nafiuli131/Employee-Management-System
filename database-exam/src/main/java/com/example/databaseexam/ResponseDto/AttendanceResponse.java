package com.example.databaseexam.ResponseDto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceResponse {
    private Long id;

    private LocalDate date;
    private boolean holiday;
}
