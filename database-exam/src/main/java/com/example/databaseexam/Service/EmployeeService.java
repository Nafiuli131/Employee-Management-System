package com.example.databaseexam.Service;

import com.example.databaseexam.Entity.Designation;
import com.example.databaseexam.Entity.Employees;
import com.example.databaseexam.Exception.CustomiseException;
import com.example.databaseexam.Repository.DesignationRepository;
import com.example.databaseexam.Repository.EmployeeRepository;
import com.example.databaseexam.RequestDto.EmployeeRequest;
import com.example.databaseexam.ResponseDto.EmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DesignationRepository designationRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DesignationRepository designationRepository) {
        this.employeeRepository = employeeRepository;
        this.designationRepository = designationRepository;
    }

    public EmployeeResponse save(EmployeeRequest employeeRequest) {
        Employees employees = converToEntity(employeeRequest);
        return convertToResponse(employees);
    }

    private EmployeeResponse convertToResponse(Employees employees) {
        return  EmployeeResponse.builder()
                .id(employees.getId())
                .name(employees.getName())
                .designationId(employees.getDesignation().getId())
                .designationName(employees.getDesignation().getDesignationName())
                .salary(employees.getDesignation().getSalary())
                .familyInformation(employees.getFamilyInformation())
                .address(employees.getAddress())
                .nid(employees.getNid())
                .joiningDate(employees.getJoiningDate())
                .gender(employees.getGender())
                .build();
    }

    private Employees converToEntity(EmployeeRequest employeeRequest) {
        Employees employees = new Employees();
        employees.setAddress(employeeRequest.getAddress());
        Optional<Designation> designation = designationRepository.findById(employeeRequest.getDesignationId());
        if(designation.isPresent()){
            employees.setDesignation(designation.get());
        }else{
            throw new CustomiseException("Data not found");
        }
        employees.setGender(employeeRequest.getGender());
        employees.setName(employeeRequest.getName());
        employees.setNid(employeeRequest.getNid());
        employees.setFamilyInformation(employeeRequest.getFamilyInformation());
        employees.setJoiningDate(employeeRequest.getJoiningDate());
        employeeRepository.save(employees);
        return employees;
    }

    public EmployeeResponse update(EmployeeRequest employeeRequest, Long id) {
        Optional<Employees> employees = employeeRepository.findById(id);
        if(employees.isPresent()){
            employees.get().setAddress(employeeRequest.getAddress());
            Optional<Designation> designation = designationRepository.findById(employeeRequest.getDesignationId());
            if(designation.isPresent()){
                employees.get().setDesignation(designation.get());
            }else{
                throw new CustomiseException("Data not found");
            }
            employees.get().setGender(employeeRequest.getGender());
            employees.get().setName(employeeRequest.getName());
            employees.get().setNid(employeeRequest.getNid());
            employees.get().setFamilyInformation(employeeRequest.getFamilyInformation());
            employees.get().setJoiningDate(employeeRequest.getJoiningDate());
            employeeRepository.save(employees.get());
            return convertToResponse(employees.get());
        }else {
            throw new CustomiseException("Data not found");
        }
    }



    public List<EmployeeResponse> getAll() {
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
            List<Employees> employees = employeeRepository.findAll();
        for(Employees employeesLoop : employees){
            employeeResponses.add(convertToResponse(employeesLoop));
        }
        return employeeResponses;
    }

    public EmployeeResponse getById(Long id) {
        Optional<Employees> employees = employeeRepository.findById(id);
        if(employees.isPresent()){
            return convertToResponse(employees.get());
        }else{
            throw new CustomiseException("Data not found");
        }
    }
}
