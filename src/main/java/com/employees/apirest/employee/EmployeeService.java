package com.employees.apirest.employee;
import com.employees.apirest.department.Department;
import com.employees.apirest.department.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public Employee createEmployee(Employee employee, Integer departmentId) {
        // Buscar el departamento por su ID
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Departamento no encontrado con el ID: " + departmentId));

        // Asignar el departamento al empleado
        employee.setDepartment(department);

        // Guardar el empleado en la base de datos
        return employeeRepository.save(employee);
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    };

    public Employee getPersonById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con el id: " + id));
    }

}
