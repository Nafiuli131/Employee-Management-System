package com.example.databaseexam.Service;

import com.example.databaseexam.Config.DateUtil;
import com.example.databaseexam.Entity.Employees;
import com.example.databaseexam.Entity.SalaryReport;
import com.example.databaseexam.Exception.CustomiseException;
import com.example.databaseexam.Repository.AttendanceRepositiroy;
import com.example.databaseexam.Repository.EmployeeAttendanceRepository;
import com.example.databaseexam.Repository.EmployeeRepository;
import com.example.databaseexam.Repository.SalaryReportRepository;
import com.example.databaseexam.RequestDto.GenerateSalaryRequest;
import com.example.databaseexam.ResponseDto.SalaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SalaryService {
    @Autowired
    AttendanceRepositiroy attendanceRepositiroy;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeAttendanceRepository employeeAttendanceRepository;
    @Autowired
    SalaryReportRepository salaryReportRepository;

    public SalaryResponse save(GenerateSalaryRequest generateSalaryRequest) {

        SalaryReport checkSalaryReport = salaryReportRepository.checkSalaryReport(generateSalaryRequest.getEmployeeId(),
                generateSalaryRequest.getMonth(), generateSalaryRequest.getYear());
        if (Objects.nonNull(checkSalaryReport)) {
            return convertResponse(checkSalaryReport);
        } else {
            List<LocalDate> dates = DateUtil.getAllDatesOfMonth(generateSalaryRequest.getMonth(), generateSalaryRequest.getYear());
            LocalDate fromDate = dates.get(0);
            LocalDate toDate = dates.get(dates.size() - 1);
            Integer workingDays = attendanceRepositiroy.findWorkingDays(fromDate, toDate);
            Optional<Employees> employeeInformation = employeeRepository.findById(generateSalaryRequest.getEmployeeId());
            if (employeeInformation.isPresent()) {
                Double perDaySalary = employeeInformation.get().getDesignation().getSalary() / workingDays;
                Integer officeDays = employeeAttendanceRepository.findOfficeDays(generateSalaryRequest.getEmployeeId()
                        , fromDate, toDate);
                Double salary = officeDays * perDaySalary;
                SalaryReport salaryReport = new SalaryReport();
                salaryReport.setAllocatedSalary(salary);
                salaryReport.setEmployees(employeeInformation.get());
                salaryReport.setMonth(generateSalaryRequest.getMonth());
                salaryReport.setYear(generateSalaryRequest.getYear());
                salaryReport.setTotalAttendance(officeDays);
                salaryReport.setTotalWorkingDays(workingDays);
                salaryReport.setPercentages(((double) officeDays / workingDays) * 100);
                salaryReportRepository.save(salaryReport);
                return convertResponse(salaryReport);
            } else {
                throw new CustomiseException("EmployeeId not found");
            }
        }

    }

    private SalaryResponse convertResponse(SalaryReport salaryReport) {
        return SalaryResponse.builder()
                .id(salaryReport.getId())
                .month(salaryReport.getMonth())
                .year(salaryReport.getYear())
                .allocatedSalary(salaryReport.getAllocatedSalary())
                .percentages(salaryReport.getPercentages())
                .employeeName(salaryReport.getEmployees().getName())
                .totalWorkingDays(salaryReport.getTotalWorkingDays())
                .totalAttendance(salaryReport.getTotalAttendance())
                .build();
    }
}
