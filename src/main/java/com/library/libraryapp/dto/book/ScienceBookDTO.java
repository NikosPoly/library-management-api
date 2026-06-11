package com.library.libraryapp.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ScienceBookDTO extends BookDTO {

    private String fieldOfStudy;
    private Boolean isTextbook;
    private Integer editionNumber;

    public ScienceBookDTO() {}

    public ScienceBookDTO(String title, String author, int publicationYear, String isbn, Boolean available,
                          String fieldOfStudy, boolean isTextbook, int editionNumber) {
        super(title, author, publicationYear, isbn, available);
        this.fieldOfStudy = fieldOfStudy;
        this.isTextbook = isTextbook;
        this.editionNumber = editionNumber;
    }

    public String getFieldOfStudy() { return fieldOfStudy; }
    public void setFieldOfStudy(String fieldOfStudy) { this.fieldOfStudy = fieldOfStudy; }

    public Boolean isTextbook() { return isTextbook; }
    public void setTextbook(Boolean isTextbook) { this.isTextbook = isTextbook; }

    public Integer getEditionNumber() { return editionNumber; }
    public void setEditionNumber(int editionNumber) { this.editionNumber = editionNumber; }
}
