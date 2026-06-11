package com.library.libraryapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class LoanDTO {


    private String bookId;
    private String customerId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Boolean returned;

    public LoanDTO() {}

    public LoanDTO(String bookId, String customerId, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate, boolean returned) {
        this.bookId = bookId;
        this.customerId = customerId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.returned = returned;
    }

    // Getters and setters

    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean isReturned() {
        return returned;
    }
    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
