package com.example.databaseexam.Repository;

import com.example.databaseexam.Entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceRepositiroy extends JpaRepository<Attendance,Long> {
    Optional<Attendance> findByDate(LocalDate date);

    @Query("select count(at) from Attendance at where at.date between :fromDate and :toDate and at.holiday = false")
    Integer findWorkingDays(LocalDate fromDate, LocalDate toDate);
}
