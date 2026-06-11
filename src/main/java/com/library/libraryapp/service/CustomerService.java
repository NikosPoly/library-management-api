package com.library.libraryapp.service;

import com.library.libraryapp.dto.user.CustomerDTO;
import com.library.libraryapp.model.user.Customer;
import com.library.libraryapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(String id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(updatedCustomer.getName());
                    existingCustomer.setEmail(updatedCustomer.getEmail());
                    existingCustomer.setCustomerId(updatedCustomer.getCustomerId());
                    existingCustomer.setGender(updatedCustomer.getGender());
                    existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
                    existingCustomer.setAddress(updatedCustomer.getAddress());
                    // add more fields if you have
                    return customerRepository.save(existingCustomer);
                })
                .orElse(null);
    }

    public Customer updateCustomer(String id, CustomerDTO patchDTO) {
        boolean isValid = true;

        // Validate all provided fields before applying any changes
        if (patchDTO.getName() != null && patchDTO.getName().isBlank()) {
            isValid = false;
        }

        if (patchDTO.getEmail() != null && !patchDTO.getEmail().contains("@")) {
            isValid = false;
        }

        if (patchDTO.getCustomerId() != null && patchDTO.getCustomerId().isBlank()) {
            isValid = false;
        }

        if (patchDTO.getGender() != null && patchDTO.getGender().isBlank()) {
            isValid = false;
        }

        if (patchDTO.getPhoneNumber() != null && !patchDTO.getPhoneNumber().matches("\\d{10}")) {
            isValid = false;
        }

        if (patchDTO.getAddress() != null && patchDTO.getAddress().isBlank()) {
            isValid = false;
        }

        if (!isValid) {
            System.out.println("One or more fields in the patch are invalid.");
            return null;
        }

        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    if (patchDTO.getName() != null) {
                        existingCustomer.setName(patchDTO.getName());
                    }

                    if (patchDTO.getEmail() != null) {
                        existingCustomer.setEmail(patchDTO.getEmail());
                    }

                    if (patchDTO.getCustomerId() != null) {
                        existingCustomer.setCustomerId(patchDTO.getCustomerId());
                    }

                    if (patchDTO.getGender() != null) {
                        existingCustomer.setGender(patchDTO.getGender());
                    }

                    if (patchDTO.getPhoneNumber() != null) {
                        existingCustomer.setPhoneNumber(patchDTO.getPhoneNumber());
                    }

                    if (patchDTO.getAddress() != null) {
                        existingCustomer.setAddress(patchDTO.getAddress());
                    }

                    return customerRepository.save(existingCustomer);
                })
                .orElse(null);
    }




    /*public Customer updateCustomer(String id, Map<String, Object> updates) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "name" -> existingCustomer.setName((String) value);
                            case "email" -> existingCustomer.setEmail((String) value);
                            case "customerId" -> existingCustomer.setCustomerId((String) value);
                            case "gender" -> existingCustomer.setGender((String) value);
                            case "phoneNumber" -> existingCustomer.setPhoneNumber((String) value);
                            case "address" -> existingCustomer.setAddress((String) value);
                            // add more cases for other fields as needed
                        }
                    });
                    return customerRepository.save(existingCustomer);
                })
                .orElse(null);
    }*/


    public void deleteCustomer(String id) {
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
        }else{
            throw new RuntimeException("Customer with ID " + id + " not found.");
        }

    }
}
