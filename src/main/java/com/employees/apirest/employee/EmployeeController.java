package com.employees.apirest.employee;


import com.employees.apirest.department.Department;
import com.employees.apirest.department.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor

public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentRepository departmentRepository;
    // logger para ponerlo en el getallperson y resolver el punto Implementa un middleware para registrar logs de las llamadas que recibe el endpoint GET /employees
    private static final Logger logger = Logger.getLogger(EmployeeController.class.getName());
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        if(employee.getDepartment() == null || employee.getDepartment().getId() == null) {
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
        logger.info("Llamada al endpoint GET /employee recibida.");
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getPersonById(@PathVariable Integer id) {
        Employee person = employeeService.getPersonById(id);
        return ResponseEntity.ok(person);
    }
}
