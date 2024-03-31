package com.employees.apirest.employee;

import com.employees.apirest.department.Department;
import com.employees.apirest.employee.Employee;
import com.employees.apirest.employee.EmployeeRepository;
import com.employees.apirest.department.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void createEmployee() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(1);
        Department department = new Department();
        department.setId(1);
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        when(employeeRepository.save(employee)).thenReturn(employee);

        // Act
        Employee savedEmployee = employeeService.createEmployee(employee, 1);

        // Assert
        assertNotNull(savedEmployee);
        assertEquals(1, savedEmployee.getId());
        assertEquals(1, savedEmployee.getDepartment().getId());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void getAllEmployees() {
        // Arrange
        // Define la lista de empleados que esperas que se devuelva desde el repositorio
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee());
        expectedEmployees.add(new Employee());
        when(employeeRepository.findAll()).thenReturn(expectedEmployees);

        // Act
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        // Assert
        assertEquals(expectedEmployees.size(), actualEmployees.size());
        assertEquals(expectedEmployees, actualEmployees);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void getPersonById() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(1);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        // Act
        Employee foundEmployee = employeeService.getPersonById(1);

        // Assert
        assertNotNull(foundEmployee);
        assertEquals(1, foundEmployee.getId());
        verify(employeeRepository, times(1)).findById(1);
    }

}
