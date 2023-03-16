package com.example.databaseexam.Service;

import com.example.databaseexam.Entity.Designation;
import com.example.databaseexam.Exception.CustomiseException;
import com.example.databaseexam.Repository.DesignationRepository;
import com.example.databaseexam.RequestDto.DesignationRequest;
import com.example.databaseexam.ResponseDto.DesignationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DesignationService {

    @Autowired
    DesignationRepository designationRepository;

    public DesignationResponse save(DesignationRequest designationRequest) {
        Designation designation = convertToDto(designationRequest);
        if (Objects.nonNull(designation.getDesignationName()) && Objects.nonNull(designation.getSalary())) {
            designationRepository.save(designation);
        } else {
            throw new CustomiseException("Data not found");
        }
        return convertToResponse(designation);
    }

    private DesignationResponse convertToResponse(Designation designation) {
        return DesignationResponse.builder()
                .id(designation.getId())
                .designationName(designation.getDesignationName())
                .salary(designation.getSalary())
                .build();
    }

    private Designation convertToDto(DesignationRequest designationRequest) {
        Designation designation = new Designation();
        designation.setDesignationName(designationRequest.getDesignationName());
        designation.setSalary(designationRequest.getSalary());
        return designation;
    }


    public DesignationResponse update(DesignationRequest designationRequest,Long id) {
        DesignationResponse designationResponse = new DesignationResponse();
        Optional<Designation> designation = designationRepository.findById(id);
        if(designation.isPresent()){
            designation.get().setDesignationName(designationRequest.getDesignationName());
            designation.get().setSalary(designationRequest.getSalary());
            designationRepository.save(designation.get());
            return convertToResponse(designation.get());
        }else {
            throw new CustomiseException("Data not found");
        }

    }

    public List<DesignationResponse> getAll() {
        List<DesignationResponse> designationResponses = new ArrayList<>();
        List<Designation> designation = designationRepository.findAll();
        for(Designation designationLoop : designation){
            designationResponses.add(convertToResponse(designationLoop));
        }
        return designationResponses;
    }

    public DesignationResponse getById(Long id) {
        Optional<Designation> designation = designationRepository.findById(id);
        if(designation.isPresent()){
            return convertToResponse(designation.get());
        }else{
            throw new CustomiseException("Data not found");
        }
    }
}
