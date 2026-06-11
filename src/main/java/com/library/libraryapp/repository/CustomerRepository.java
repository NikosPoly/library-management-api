package com.library.libraryapp.repository;


import com.library.libraryapp.model.user.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    // Add custom query methods here if needed
}
