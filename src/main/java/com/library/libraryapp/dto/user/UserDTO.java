package com.library.libraryapp.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public abstract class UserDTO {


    private String name;
    private String email;

    public UserDTO() {}

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
