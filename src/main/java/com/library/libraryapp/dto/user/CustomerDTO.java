package com.library.libraryapp.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDTO extends UserDTO {


    private String customerId;
    private String gender;
    private String phoneNumber;
    private String address;

    public CustomerDTO() {
        super();
    }

    public CustomerDTO(String name, String email, String customerId, String gender, String phoneNumber, String address) {
        super(name, email);
        this.customerId = customerId;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getters and setters

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
