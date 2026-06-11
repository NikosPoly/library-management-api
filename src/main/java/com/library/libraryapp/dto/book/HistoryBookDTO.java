package com.library.libraryapp.dto.book;

import jakarta.validation.constraints.NotBlank;

public class HistoryBookDTO extends BookDTO {

    private String historicalPeriod;
    private String regionFocus;

    public HistoryBookDTO() {}

    public HistoryBookDTO(String title, String author, int publicationYear, String isbn, boolean available,
                          String historicalPeriod, String regionFocus) {
        super(title, author, publicationYear, isbn, available);
        this.historicalPeriod = historicalPeriod;
        this.regionFocus = regionFocus;
    }

    public String getHistoricalPeriod() { return historicalPeriod; }
    public void setHistoricalPeriod(String historicalPeriod) { this.historicalPeriod = historicalPeriod; }

    public String getRegionFocus() { return regionFocus; }
    public void setRegionFocus(String regionFocus) { this.regionFocus = regionFocus; }
}
