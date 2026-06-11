package com.library.libraryapp.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "loans")
public class Loan {

    @Id
    private String id;         // Unique ID for this loan

    @NotBlank(message = "Book ID is required")
    private String bookId;  // ID of the book being loaned

    @NotBlank(message = "Customer ID is required")
    private String customerId;   // ID of the user who borrowed the book

    @NotNull(message = "Loan date is required")
    private LocalDate loanDate;  // Date when the book was loaned

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;   // Date when the book should be returned

    private LocalDate returnDate;  // Status flag if book has been returned

    private boolean returned;  // boolean primitive, no validation needed


    public Loan() {
        // Default constructor needed for Mongo and serialization
    }

    public Loan(String bookId, String customerId, LocalDate loanDate, LocalDate dueDate) {
        this.bookId = bookId;
        this.customerId = customerId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = false;
    }

    // Getters and setters for all fields

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

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

    public boolean isReturned() {
        return returned;
    }
    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    // Optional: method to mark the loan as returned
    public void markAsReturned(LocalDate returnDate) {
        this.returned = true;
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id='" + id + '\'' +
                ", bookId='" + bookId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", returned=" + returned +
                '}';
    }

}
