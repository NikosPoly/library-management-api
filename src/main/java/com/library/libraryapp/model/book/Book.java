package com.library.libraryapp.model.book;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type", // <- Jackson expects this in the JSON, but you don't define it in your Java class
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HistoryBook.class, name = "HistoryBook"),
        @JsonSubTypes.Type(value = ScienceBook.class, name = "ScienceBook"),
        @JsonSubTypes.Type(value = LiteratureBook.class, name = "LiteratureBook")
})
@Document(collection = "books")
public abstract class Book {

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author name is required")
    private String author;

    @Min(value = 1000, message = "Publication year must be a valid year")
    @Max(value = 2100, message = "Publication year must be a valid year")
    private int publicationYear;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "Invalid ISBN format")
    private String isbn;

    private boolean available;

    public Book() {
        this.available = true; // Default: book is available
    }

    public Book(String title, String author, int publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.available = true;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Abstract method: subclasses must implement
    public abstract String getCategory();
}

