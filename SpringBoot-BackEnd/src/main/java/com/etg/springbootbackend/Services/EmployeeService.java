package com.etg.springbootbackend.Services;

import antlr.ASTNULLType;
import com.etg.springbootbackend.exception.ResourceNotFoundException;
import com.etg.springbootbackend.model.Employee;
import com.etg.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<Employee> getOneEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("This user does not exist"));

        return ResponseEntity.ok(employee);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public ResponseEntity<Employee> updateEmployee(Long id, Employee employee) {
        Employee employee1 = employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee with id "+id+" not found")
        );

        employee1.setEmailId(employee.getEmailId());
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());

        Employee updatedEmployee = employeeRepository.save(employee1);
        return ResponseEntity.ok(updatedEmployee);

    }

    public ResponseEntity<Map<String,Boolean>> deleteEmployee(Long id) {
       Employee employee = employeeRepository.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("This employee does not exist")
       );

       employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
       return ResponseEntity.ok(response);

    }
}
