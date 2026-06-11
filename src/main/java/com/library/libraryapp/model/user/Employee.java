package com.library.libraryapp.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee extends User {

    @NotBlank(message = "Employee ID is required")
    private String employeeId;
    @NotBlank(message = "Section is required")
    private String section;
    @NotNull(message = "Role is required")
    private Role role;

    public Employee() {}

    public Employee(String name, String email, String employeeId, String section, Role role) {
        super(name, email);
        this.employeeId = employeeId;
        this.section = section;
        this.role = role;
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public boolean hasAccess(String action) {
        switch (role) {
            case ADMIN:
                return true;  // Admin has full access
            case LIBRARIAN:
                // Librarian can add/remove books and manage loans
                return action.equals("ADD_BOOK") || action.equals("REMOVE_BOOK") || action.equals("MANAGE_LOANS");
            case ASSISTANT:
                // Assistant has basic access only
                return action.equals("VIEW_BOOKS");
            default:
                return false; // No access by default
        }
    }
}
