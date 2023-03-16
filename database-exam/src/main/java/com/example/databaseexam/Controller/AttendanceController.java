package com.example.databaseexam.Controller;

import com.example.databaseexam.RequestDto.AttendanceRequest;
import com.example.databaseexam.ResponseDto.AttendanceResponse;
import com.example.databaseexam.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<List<AttendanceResponse>> save(@RequestBody AttendanceRequest attendanceRequest){
        return ResponseEntity.ok(attendanceService.save(attendanceRequest));
    }
    @PutMapping("/{date}")
    public ResponseEntity<AttendanceResponse> update(@RequestBody  AttendanceRequest attendanceRequest
            , @PathVariable LocalDate date){
        return ResponseEntity.ok(attendanceService.update(attendanceRequest,date));
    }
    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getAll(){
        return ResponseEntity.ok(attendanceService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(attendanceService.getById(id));
    }
}
