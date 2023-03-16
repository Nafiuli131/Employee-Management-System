package com.example.databaseexam.Controller;

import com.example.databaseexam.RequestDto.EmployeeAttendanceRequest;
import com.example.databaseexam.RequestDto.EmplyeeAttendanceInformation;
import com.example.databaseexam.ResponseDto.EmployeeAttendanceResponse;
import com.example.databaseexam.Service.EmployeeAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employeeAttendance")
public class EmployeeAttendanceController {
    @Autowired
    EmployeeAttendanceService employeeAttendanceService;

    @PostMapping
    public ResponseEntity<EmployeeAttendanceResponse> save(@RequestBody EmployeeAttendanceRequest employeeAttendanceRequest){
        return ResponseEntity.ok(employeeAttendanceService.save(employeeAttendanceRequest));
    }

    @PostMapping("/getEmployeeInfo")
    public ResponseEntity<List<EmployeeAttendanceResponse>> getAll(@RequestBody EmplyeeAttendanceInformation emplyeeAttendanceInformation){
        return ResponseEntity.ok(employeeAttendanceService.getAll(emplyeeAttendanceInformation));
    }


}
