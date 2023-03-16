package com.example.databaseexam.Controller;

import com.example.databaseexam.RequestDto.DesignationRequest;
import com.example.databaseexam.ResponseDto.DesignationResponse;
import com.example.databaseexam.Service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designation")
public class DesignationController {

    @Autowired
    DesignationService designationService;

    @PostMapping
    public ResponseEntity<DesignationResponse> save(@RequestBody DesignationRequest designationRequest){
        return ResponseEntity.ok(designationService.save(designationRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DesignationResponse> update(@RequestBody DesignationRequest designationRequest
            ,@PathVariable Long id){
        return ResponseEntity.ok(designationService.update(designationRequest,id));
    }
    @GetMapping
    public ResponseEntity<List<DesignationResponse>> getAll(){
        return ResponseEntity.ok(designationService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DesignationResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(designationService.getById(id));
    }

}
