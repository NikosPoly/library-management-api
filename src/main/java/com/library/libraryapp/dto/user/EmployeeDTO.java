package com.library.libraryapp.dto.user;

import com.library.libraryapp.model.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeDTO extends UserDTO {


    private String employeeId;
    private String section;
    private Role role;

    public EmployeeDTO() {
        super();
    }

    public EmployeeDTO(String name, String email, String employeeId, String section, Role role) {
        super(name, email);
        this.employeeId = employeeId;
        this.section = section;
        this.role = role;
    }

    // Getters and setters
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
