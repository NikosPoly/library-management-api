package com.library.libraryapp.service;

import com.library.libraryapp.dto.LoanDTO;
import com.library.libraryapp.model.Loan;
import com.library.libraryapp.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(String id) {
        return loanRepository.findById(id);
    }

    public Loan createLoan(Loan loan) {
        System.out.println("Created Loan " + loan);
        return loanRepository.save(loan);
    }


    public Loan updateLoan(String id, Loan updatedLoan) {
        return loanRepository.findById(id)
                .map(existingLoan -> {
                    System.out.println("Before update: " + existingLoan);

                    existingLoan.setBookId(updatedLoan.getBookId());
                    existingLoan.setCustomerId(updatedLoan.getCustomerId());
                    existingLoan.setLoanDate(updatedLoan.getLoanDate());
                    existingLoan.setDueDate(updatedLoan.getDueDate());
                    existingLoan.setReturnDate(updatedLoan.getReturnDate());
                    existingLoan.setReturned(updatedLoan.isReturned());
                    return loanRepository.save(existingLoan);
                })
                .orElse(null);
    }

    public Loan updateLoan(String id, LoanDTO patchDTO) {
        boolean isValid = true;

        // Validate String fields
        if (patchDTO.getBookId() != null && patchDTO.getBookId().isBlank()) {
            isValid = false;
        }

        if (patchDTO.getCustomerId() != null && patchDTO.getCustomerId().isBlank()) {
            isValid = false;
        }


        // returned is Boolean wrapper — no blank check needed, null means "not updated"
        // But if you want to validate something, you can add a check here.

        if (!isValid) {
            System.out.println("One or more fields in the patch are invalid.");
            return null;
        }

        return loanRepository.findById(id)
                .map(existingLoan -> {
                    if (patchDTO.getBookId() != null) {
                        existingLoan.setBookId(patchDTO.getBookId());
                    }

                    if (patchDTO.getCustomerId() != null) {
                        existingLoan.setCustomerId(patchDTO.getCustomerId());
                    }

                    if (patchDTO.getLoanDate() != null) {
                        existingLoan.setLoanDate(patchDTO.getLoanDate());
                    }

                    if (patchDTO.getDueDate() != null) {
                        existingLoan.setDueDate(patchDTO.getDueDate());
                    }

                    if (patchDTO.getReturnDate() != null) {
                        existingLoan.setReturnDate(patchDTO.getReturnDate());
                    }

                    if (patchDTO.isReturned() != null) {
                        existingLoan.setReturned(patchDTO.isReturned());
                    }

                    return loanRepository.save(existingLoan);
                })
                .orElse(null);
    }





    /*public Loan updateLoan(String id, Map<String, Object> updates) {
        return loanRepository.findById(id)
                .map(existingLoan -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "bookId" -> existingLoan.setBookId((String) value);
                            case "customerId" -> existingLoan.setCustomerId((String) value);
                            case "loanDate" -> existingLoan.setLoanDate(LocalDate.parse((String) value));
                            case "dueDate" -> existingLoan.setDueDate(LocalDate.parse((String) value));
                            case "returnDate" -> existingLoan.setReturnDate(LocalDate.parse((String) value));
                            case "returned" -> existingLoan.setReturned((Boolean) value);
                            // You can ignore unknown keys or log them
                        }
                    });

                    return loanRepository.save(existingLoan);
                })
                .orElse(null);
    }*/




    public void deleteLoan(String id) {
        if (loanRepository.existsById(id)) {
            loanRepository.deleteById(id);
        } else {
            throw new RuntimeException("Loan with ID " + id + " not found.");
        }
    }

}
