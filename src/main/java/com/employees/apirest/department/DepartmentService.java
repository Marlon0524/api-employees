package com.employees.apirest.department;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;


    public Department createDepartment (Department department){
        departmentRepository.save(department);
        return department;
    };

    public List<Department> getAllDepartments(){ return departmentRepository.findAll();}

    public Department getDepartmentById(Integer id){
        return departmentRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Persona no encontrada con el ID: "+id));
    }
}
