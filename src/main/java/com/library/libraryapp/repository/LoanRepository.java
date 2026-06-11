package com.library.libraryapp.repository;


import com.library.libraryapp.model.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String> {
    // Additional query methods can be added here later if needed
}

