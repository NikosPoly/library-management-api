package com.library.libraryapp.repository;


import com.library.libraryapp.model.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends MongoRepository<Section, String> {
    // Additional query methods can be added here later if needed
}

