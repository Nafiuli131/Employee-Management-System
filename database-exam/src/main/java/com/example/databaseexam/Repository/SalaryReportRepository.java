package com.example.databaseexam.Repository;

import com.example.databaseexam.Entity.SalaryReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalaryReportRepository extends JpaRepository<SalaryReport,Long> {
    @Query("select sl from SalaryReport sl where sl.employeeId = :employeeId and sl.month = :month and sl.year = :year")
    SalaryReport checkSalaryReport(Long employeeId, Integer month, Integer year);
}
