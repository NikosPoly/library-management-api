package com.library.libraryapp.dto.book;

import jakarta.validation.constraints.NotBlank;

public class LiteratureBookDTO extends BookDTO {

    private String genre;
    private Boolean forKids;
    private String targetAudience;

    public LiteratureBookDTO() {}

    public LiteratureBookDTO(String title, String author, int publicationYear, String isbn, Boolean available,
                             String genre, boolean forKids, String targetAudience) {
        super(title, author, publicationYear, isbn, available);
        this.genre = genre;
        this.forKids = forKids;
        this.targetAudience = targetAudience;
    }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Boolean isForKids() { return forKids; }
    public void setForKids(Boolean forKids) { this.forKids = forKids; }

    public String getTargetAudience() { return targetAudience; }
    public void setTargetAudience(String targetAudience) { this.targetAudience = targetAudience; }
}
