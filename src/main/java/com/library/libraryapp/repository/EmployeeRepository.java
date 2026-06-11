package com.library.libraryapp.repository;


import com.library.libraryapp.model.user.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    // Add custom query methods here if needed
}

