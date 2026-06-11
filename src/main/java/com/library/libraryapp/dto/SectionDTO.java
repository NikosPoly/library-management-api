package com.library.libraryapp.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class SectionDTO {

    private String name;
    private String description;
    private List<String> bookIds;      // optional, no validation here
    private List<String> employeeIds;  // optional, no validation here

    public SectionDTO() {}

    public SectionDTO(String name, String description, List<String> bookIds, List<String> employeeIds) {
        this.name = name;
        this.description = description;
        this.bookIds = bookIds;
        this.employeeIds = employeeIds;
    }

    // Getters and setters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getBookIds() {
        return bookIds;
    }
    public void setBookIds(List<String> bookIds) {
        this.bookIds = bookIds;
    }

    public List<String> getEmployeeIds() {
        return employeeIds;
    }
    public void setEmployeeIds(List<String> employeeIds) {
        this.employeeIds = employeeIds;
    }
}
