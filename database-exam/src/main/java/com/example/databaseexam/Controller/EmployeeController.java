package com.example.databaseexam.Controller;

import com.example.databaseexam.RequestDto.EmployeeRequest;
import com.example.databaseexam.ResponseDto.EmployeeResponse;
import com.example.databaseexam.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> save(@RequestBody EmployeeRequest employeeRequest){
        return ResponseEntity.ok(employeeService.save(employeeRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@RequestBody EmployeeRequest employeeRequest
            ,@PathVariable Long id){
        return ResponseEntity.ok(employeeService.update(employeeRequest,id));
    }
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll(){
        return ResponseEntity.ok(employeeService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getById(id));
    }
}
