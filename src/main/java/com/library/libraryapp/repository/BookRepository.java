package com.library.libraryapp.repository;



import com.library.libraryapp.model.book.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    // You can add custom query methods here later if needed
}

