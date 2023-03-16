package com.example.databaseexam.Repository;

import com.example.databaseexam.Entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance,Long> {
    @Query("select ea from EmployeeAttendance ea where ea.employeeId = :id and ea.date between :fromDate and :toDate")
    List<EmployeeAttendance> findByEmployeeId(Long id, LocalDate fromDate,LocalDate toDate);

    @Query("select count(ea) from EmployeeAttendance ea where ea.employeeId = :employeeId and ea.date between :fromDate " +
            "and :toDate " +
            "and ea.isPresent = true")
    Integer findOfficeDays(Long employeeId, LocalDate fromDate, LocalDate toDate);
}
