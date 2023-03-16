package com.example.databaseexam.Service;

import com.example.databaseexam.Entity.Attendance;
import com.example.databaseexam.Entity.EmployeeAttendance;
import com.example.databaseexam.Entity.Employees;
import com.example.databaseexam.Exception.CustomiseException;
import com.example.databaseexam.Repository.AttendanceRepositiroy;
import com.example.databaseexam.Repository.EmployeeAttendanceRepository;
import com.example.databaseexam.Repository.EmployeeRepository;
import com.example.databaseexam.RequestDto.EmployeeAttendanceRequest;
import com.example.databaseexam.RequestDto.EmplyeeAttendanceInformation;
import com.example.databaseexam.ResponseDto.EmployeeAttendanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeeAttendanceService {
    @Autowired
    EmployeeAttendanceRepository employeeAttendanceRepository;
    @Autowired
    AttendanceRepositiroy attendanceRepositiroy;

    @Autowired
    EmployeeRepository employeeRepository;


    public EmployeeAttendanceResponse save(EmployeeAttendanceRequest employeeAttendanceRequest) {
        List<EmployeeAttendance> employeeAttendances = employeeAttendanceRepository.findAll();
        for(EmployeeAttendance employeeAttendance:employeeAttendances){
            if(employeeAttendance.getEmployeeId().equals(employeeAttendanceRequest.getEmployeeId())
                    && employeeAttendance.getDate().equals(employeeAttendanceRequest.getDate())){
                throw new CustomiseException("Same data exists");
            }
        }
        Optional<Attendance> attendance = attendanceRepositiroy.findByDate(employeeAttendanceRequest.getDate());
        if (attendance.isPresent()) {
            Optional<Employees> employees = employeeRepository.findById(employeeAttendanceRequest.getEmployeeId());
            if (employees.isPresent()) {
                EmployeeAttendance employeeAttendance = new EmployeeAttendance();
                employeeAttendance.setEmployees(employees.get());
                employeeAttendance.setAttendance(attendance.get());
                employeeAttendance.setPresent(employeeAttendanceRequest.isAttendance());
                employeeAttendance.setDate(employeeAttendanceRequest.getDate());
                employeeAttendanceRepository.save(employeeAttendance);
                return convertToResponse(employeeAttendance);
            } else {
                throw new CustomiseException("Data not found");
            }
        } else {
            throw new CustomiseException("Data not found");
        }
    }

    private EmployeeAttendanceResponse convertToResponse(EmployeeAttendance employeeAttendance) {
        return EmployeeAttendanceResponse.builder()
                .id(employeeAttendance.getId())
                .attendanceId(employeeAttendance.getAttendance().getId())
                .employeeId(employeeAttendance.getEmployees().getId())
                .date(employeeAttendance.getDate())
                .isPresent(employeeAttendance.isPresent())
                .build();
    }



    public List<EmployeeAttendanceResponse> getAll(EmplyeeAttendanceInformation emplyeeAttendanceInformation) {
        Optional<Employees> employees = employeeRepository.findById(emplyeeAttendanceInformation.getEmployeeId());
        List<EmployeeAttendanceResponse> employeeAttendanceResponseList = new ArrayList<>();
        if(employees.isPresent()){
            List<EmployeeAttendance> employeeAttendances = employeeAttendanceRepository.findByEmployeeId(
                    emplyeeAttendanceInformation.getEmployeeId(),emplyeeAttendanceInformation.getFromDate(),
                    emplyeeAttendanceInformation.getToDate());
            for(EmployeeAttendance employeeAttendance:employeeAttendances){
                employeeAttendanceResponseList.add(convertToResponse(employeeAttendance));
            }
        }else{
            throw new CustomiseException("Data not found");
        }
        return employeeAttendanceResponseList;
    }


}
