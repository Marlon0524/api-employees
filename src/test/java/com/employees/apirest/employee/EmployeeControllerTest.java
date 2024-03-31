package com.employees.apirest.employee;

import com.employees.apirest.department.Department;
import com.employees.apirest.department.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeController employeeController;
    private List<Employee> employeeList;
    void setUp() {

    }
    @Test
    void createEmployee() {
        // Arrange
        Integer departmentId = 1;
        Department department = new Department();
        department.setId(departmentId);

        Employee employeeRequest = new Employee();
        employeeRequest.setFirstName("John");
        employeeRequest.setLastName("Doe");
        employeeRequest.setEmail("john.doe@example.com");
        employeeRequest.setDepartment(department);

        Employee createdEmployee = new Employee();
        createdEmployee.setId(1);
        createdEmployee.setFirstName("John");
        createdEmployee.setLastName("Doe");
        createdEmployee.setEmail("john.doe@example.com");
        createdEmployee.setDepartment(department);

        // Simulate department lookup
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
        // Simulate employee creation
        when(employeeService.createEmployee(any(Employee.class), eq(departmentId))).thenReturn(createdEmployee);

        // Act
        ResponseEntity<Employee> response = employeeController.createEmployee(employeeRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdEmployee, response.getBody());
    }

}
