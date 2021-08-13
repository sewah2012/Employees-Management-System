package com.etg.springbootbackend.controller;

import com.etg.springbootbackend.Services.EmployeeService;
import com.etg.springbootbackend.model.Employee;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController( EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    //get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //getOneEmployee

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getOneEmployee(@PathVariable Long id){
        return employeeService.getOneEmployee(id);
    }

    //add new employeee
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
      return employeeService.createEmployee(employee);
    }

    //update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }

    //delete employee
    @DeleteMapping("/employees/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }


}
