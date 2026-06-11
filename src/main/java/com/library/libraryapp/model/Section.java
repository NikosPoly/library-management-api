package com.library.libraryapp.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "sections")
public class Section {

    @Id
    private String id;

    @NotBlank(message = "Section name is required")
    private String name;

    @NotBlank(message = "Section description is required")
    private String description;

    private List<String> bookIds;      // Optional, no validation needed here, Book IDs stored in this section

    private List<String> employeeIds;  // Optional, no validation needed here, Employee/User IDs managing the section



    public Section() {
    }

    public Section(String name, String description, List<String> bookIds, List<String> employeeIds) {
        this.name = name;
        this.description = description;
        this.bookIds = bookIds;
        this.employeeIds = employeeIds;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
