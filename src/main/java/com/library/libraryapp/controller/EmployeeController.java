package com.library.libraryapp.controller;

import com.library.libraryapp.dto.user.EmployeeDTO;
import com.library.libraryapp.model.user.Employee;
import com.library.libraryapp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee( @Valid  @RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(createdEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @Valid @RequestBody Employee updatedEmployee) {
        Employee employee = employeeService.updateEmployee(id, updatedEmployee);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    /*@PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        Employee updatedEmployee = employeeService.updateEmployee(id, updates);
        return updatedEmployee != null
                ? ResponseEntity.ok(updatedEmployee)
                : ResponseEntity.notFound().build();
    }*/

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id,@RequestBody EmployeeDTO patchDTO) {
        Employee updatedEmployee = employeeService.updateEmployee(id, patchDTO);
        return updatedEmployee != null
                ? ResponseEntity.ok(updatedEmployee)
                : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
