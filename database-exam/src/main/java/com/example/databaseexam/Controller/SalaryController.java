package com.example.databaseexam.Controller;

import com.example.databaseexam.RequestDto.DesignationRequest;
import com.example.databaseexam.RequestDto.GenerateSalaryRequest;
import com.example.databaseexam.ResponseDto.DesignationResponse;
import com.example.databaseexam.ResponseDto.SalaryResponse;
import com.example.databaseexam.Service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/generateSalary")
public class SalaryController {
    @Autowired
    SalaryService salaryService;
    @PostMapping
    public ResponseEntity<SalaryResponse> save(@RequestBody GenerateSalaryRequest generateSalaryRequest){
        return ResponseEntity.ok(salaryService.save(generateSalaryRequest));
    }

}
