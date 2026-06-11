package com.library.libraryapp.model.book;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.TypeAlias;


@TypeAlias("ScienceBook")
public class ScienceBook extends Book {

    @NotBlank(message = "Field of study is required")
    private String fieldOfStudy; // e.g. Physics, Biology, Chemistry

    private boolean isTextbook = false; // true if it is a textbook

    @Min(value = 1, message = "Edition number must be at least 1")
    private int editionNumber;



    public ScienceBook() {
        super();
    }

    public ScienceBook(String title, String author, int publicationYear, String isbn,
                       String fieldOfStudy, boolean isTextbook, int editionNumber) {
        super(title, author, publicationYear, isbn);
        this.fieldOfStudy = fieldOfStudy;
        this.isTextbook = isTextbook;
        this.editionNumber = editionNumber;
    }

    @JsonIgnore
    @Override
    public String getCategory() {
        return "Science";
    }

    // Getters and setters
    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public boolean isTextbook() {
        return isTextbook;
    }

    public void setTextbook(boolean isTextbook) {
        this.isTextbook = isTextbook;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }
}
