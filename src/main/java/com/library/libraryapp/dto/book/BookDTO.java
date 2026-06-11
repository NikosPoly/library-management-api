package com.library.libraryapp.dto.book;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ScienceBookDTO.class, name = "ScienceBook"),
        @JsonSubTypes.Type(value = LiteratureBookDTO.class, name = "LiteratureBook"),
        @JsonSubTypes.Type(value = HistoryBookDTO.class, name = "HistoryBook")
})
public abstract class BookDTO {


    private String title;
    private String author;
    private Integer publicationYear;
    private String isbn;
    private Boolean available = true;

    public BookDTO() {}

    public BookDTO(String title, String author, int publicationYear, String isbn, Boolean available) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.available = available;
    }

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Integer getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Boolean isAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}
