package com.example.databaseexam.Service;

import com.example.databaseexam.Entity.Attendance;
import com.example.databaseexam.Exception.CustomiseException;
import com.example.databaseexam.Repository.AttendanceRepositiroy;
import com.example.databaseexam.RequestDto.AttendanceRequest;
import com.example.databaseexam.ResponseDto.AttendanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepositiroy attendanceRepositiroy;

    //save a whole month data
    public List<AttendanceResponse> save(AttendanceRequest attendanceRequest) {
        List<LocalDate> dates = createMonthData(attendanceRequest);
        List<AttendanceResponse> attendanceResponses = new ArrayList<>();
        for(LocalDate date : dates){
            AttendanceResponse attendanceResponse = new AttendanceResponse();
            attendanceResponse.setDate(date);
            attendanceResponses.add(attendanceResponse);
        }
        convertAttendanceDate(attendanceResponses);
        return attendanceResponses;
    }

    private void convertAttendanceDate(List<AttendanceResponse> attendanceResponses) {
        List<Attendance> attendances = new ArrayList<>();
        for(AttendanceResponse attendanceResponse: attendanceResponses){
            Attendance attendance = new Attendance();
            attendance.setDate(attendanceResponse.getDate());
            attendances.add(attendance);
        }
        attendanceRepositiroy.saveAll(attendances);
    }

    private List<LocalDate> createMonthData(AttendanceRequest attendanceRequest) {
        List<LocalDate> dates = new ArrayList<>();
        Month month = Month.valueOf(attendanceRequest.getMonthName().toUpperCase());
        LocalDate startDate = LocalDate.of(attendanceRequest.getYear(), month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            dates.add(date);
        }
        return dates;
    }

    public AttendanceResponse update(AttendanceRequest attendanceRequest, LocalDate date) {
        Optional<Attendance> attendance = attendanceRepositiroy.findByDate(date);
        if(attendance.isPresent()){
            attendance.get().setHoliday(attendanceRequest.getHoliday());
            attendanceRepositiroy.save(attendance.get());
            return convertToResponse(attendance.get());
        }else {
            throw new CustomiseException("Data not found");
        }
    }

    private AttendanceResponse convertToResponse(Attendance attendance) {
        return AttendanceResponse.builder()
                .id(attendance.getId())
                .date(attendance.getDate())
                .holiday(attendance.isHoliday())
                .build();
    }

    public List<AttendanceResponse> getAll() {
        List<AttendanceResponse> attendanceResponses = new ArrayList<>();
        List<Attendance> attendances = attendanceRepositiroy.findAll();
        for(Attendance attendance : attendances){
            attendanceResponses.add(convertToResponse(attendance));
        }
        return attendanceResponses;
    }

    public AttendanceResponse getById(Long id) {
        Optional<Attendance> attendance = attendanceRepositiroy.findById(id);
        if(attendance.isPresent()){
            return convertToResponse(attendance.get());
        }else{
            throw new CustomiseException("Data not found");
        }
    }
}
