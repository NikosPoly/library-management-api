package com.library.libraryapp.controller;

import com.library.libraryapp.dto.LoanDTO;
import com.library.libraryapp.model.Loan;
import com.library.libraryapp.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // Create a new loan
    @PostMapping
    public ResponseEntity<Loan> createLoan( @Valid @RequestBody Loan loan) {
        Loan createdLoan = loanService.createLoan(loan);
        return ResponseEntity.ok(createdLoan);
    }

    // Get a loan by ID
    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable String id) {
        return loanService.getLoanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Get all loans
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    // Update a loan
    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable String id, @Valid  @RequestBody Loan updatedLoan) {
        Loan loan = loanService.updateLoan(id, updatedLoan);
        return loan != null ? ResponseEntity.ok(loan) : ResponseEntity.notFound().build();
    }

    /*@PatchMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        Loan updated = loanService.updateLoan(id, updates);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }*/

    @PatchMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable String id, @RequestBody LoanDTO patchDTO) {
        Loan updated = loanService.updateLoan(id, patchDTO);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }


    // Delete a loan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable String id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}
