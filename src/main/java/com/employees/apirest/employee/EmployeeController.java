package com.employees.apirest.employee;


import com.employees.apirest.department.Department;
import com.employees.apirest.department.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentRepository departmentRepository;


   /*    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, @RequestParam Integer departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con el id: " + departmentId));

        employee.setDepartment(department);
        Employee newEmployee = employeeService.createEmployee(employee, departmentId); // Pasar employee y departmentId al servicio
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }*/

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        System.out.println(employee);
        if(employee.getDepartment() == null || employee.getDepartment().getId() == null) {
            System.out.println("OK!!!!!!");
            throw new RuntimeException("El ID del departamento es requerido.");
        }

        Integer departmentId = employee.getDepartment().getId();

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con el id: " + departmentId));

        employee.setDepartment(department);
        Employee newEmployee = employeeService.createEmployee(employee, departmentId);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllPersons() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getPersonById(@PathVariable Integer id) {
        Employee person = employeeService.getPersonById(id);
        return ResponseEntity.ok(person);
    }
}
