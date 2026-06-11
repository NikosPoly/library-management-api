package com.library.libraryapp.controller;

import com.library.libraryapp.dto.user.CustomerDTO;
import com.library.libraryapp.model.user.Customer;
import com.library.libraryapp.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer( @Valid @RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable String id,
            @Valid @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(id, updatedCustomer);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }


    /*@PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        Customer updatedCustomer = customerService.updateCustomer(id, updates);
        return updatedCustomer != null
                ? ResponseEntity.ok(updatedCustomer)
                : ResponseEntity.notFound().build();
    }*/

    @PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody CustomerDTO patchDTO) {
        Customer updatedCustomer = customerService.updateCustomer(id, patchDTO);
        return updatedCustomer != null
                ? ResponseEntity.ok(updatedCustomer)
                : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
