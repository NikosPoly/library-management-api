package com.library.libraryapp.model.book;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.TypeAlias;


@TypeAlias("LiteratureBook")
public class LiteratureBook extends Book {

    @NotBlank(message = "Genre is required")
    private String genre;  // e.g., Romance, Mystery, Sci-Fi

    private boolean forKids = false;  // true if it's suitable for kids, false otherwise

    @NotBlank(message = "Target audience is required")
    private String targetAudience; // e.g., "Adults", "Young Adults", etc.

    public LiteratureBook() {
        super();
    }

    public LiteratureBook(String title, String author, int publicationYear, String isbn,
                          String genre, boolean forKids, String targetAudience) {
        super(title, author, publicationYear, isbn);
        this.genre = genre;
        this.forKids = forKids;
        this.targetAudience = targetAudience;
    }

    @JsonIgnore
    @Override
    public String getCategory() {
        return "Literature";
    }

    // Getters and Setters
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isForKids() {
        return forKids;
    }

    public void setForKids(boolean forKids) {
        this.forKids = forKids;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }
}
