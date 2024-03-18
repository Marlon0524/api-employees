package com.employees.apirest.employee;

import com.employees.apirest.department.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Employee {
    @Id
    @GeneratedValue
    private Integer id;
    @Basic
    private String firstName;
    @Basic
    private String lastName;
    @Basic
    private String cargo;
    @Basic
    private String email;
    @Setter
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
