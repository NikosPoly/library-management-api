package com.library.libraryapp.service;

import com.library.libraryapp.dto.user.EmployeeDTO;
import com.library.libraryapp.model.user.Employee;
import com.library.libraryapp.model.user.Role;
import com.library.libraryapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(String id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(String id, Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setName(updatedEmployee.getName());
                    existingEmployee.setEmail(updatedEmployee.getEmail());
                    existingEmployee.setEmployeeId(updatedEmployee.getEmployeeId());
                    existingEmployee.setSection(updatedEmployee.getSection());
                    existingEmployee.setRole(updatedEmployee.getRole());
                    // add other fields if you have
                    return employeeRepository.save(existingEmployee);
                })
                .orElse(null);
    }

    public Employee updateEmployee(String id, EmployeeDTO patchDTO) {
        boolean isValid = true;

        if (patchDTO.getName() != null && patchDTO.getName().isBlank()) {
            isValid = false;
        }

        if (patchDTO.getEmail() != null && !patchDTO.getEmail().contains("@")) {
            isValid = false;
        }

        if (patchDTO.getEmployeeId() != null && patchDTO.getEmployeeId().isBlank()) {
            isValid = false;
        }

        if (patchDTO.getSection() != null && patchDTO.getSection().isBlank()) {
            isValid = false;
        }

        // No need to validate `role`, since invalid enums will fail during request parsing

        if (!isValid) {
            System.out.println("Invalid patch data: one or more fields are malformed.");
            return null;
        }

        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    if (patchDTO.getName() != null) {
                        existingEmployee.setName(patchDTO.getName());
                    }

                    if (patchDTO.getEmail() != null) {
                        existingEmployee.setEmail(patchDTO.getEmail());
                    }

                    if (patchDTO.getEmployeeId() != null) {
                        existingEmployee.setEmployeeId(patchDTO.getEmployeeId());
                    }

                    if (patchDTO.getSection() != null) {
                        existingEmployee.setSection(patchDTO.getSection());
                    }

                    if (patchDTO.getRole() != null) {
                        existingEmployee.setRole(patchDTO.getRole());
                    }

                    return employeeRepository.save(existingEmployee);
                })
                .orElse(null);
    }




    /*public Employee updateEmployee(String id, Map<String, Object> updates) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "name" -> existingEmployee.setName((String) value);
                            case "email" -> existingEmployee.setEmail((String) value);
                            case "employeeId" -> existingEmployee.setEmployeeId((String) value);
                            case "section" -> existingEmployee.setSection((String) value);
                            case "role" -> existingEmployee.setRole(Role.valueOf((String) value));
                            // add more cases for other fields if needed
                        }
                    });
                    return employeeRepository.save(existingEmployee);
                })
                .orElse(null);
    }*/


    public void deleteEmployee(String id) {
        if(employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        }else{
            throw new RuntimeException("Employee with ID " + id + " not found.");
        }
    }
}
