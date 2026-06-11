package com.library.libraryapp.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class Customer extends User {

    @NotBlank(message = "Customer ID is required")
    private String customerId;  // Custom ID specific to the customer
    @NotBlank(message = "Gender is required")
    private String gender;      // "male", "female", etc.
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;
    @NotBlank(message = "Address is required")
    private String address;

    public Customer() {
        super();
    }

    public Customer(String name, String email,String customerId, String gender, String phoneNumber, String address) {

        super(name, email);  // Assuming User has (name, email) constructor
        this.customerId = customerId;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

    // Getters and Setters

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
