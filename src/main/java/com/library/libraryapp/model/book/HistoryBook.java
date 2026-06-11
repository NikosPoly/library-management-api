package com.library.libraryapp.model.book;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.TypeAlias;


@TypeAlias("HistoryBook")
public class HistoryBook extends Book {

    @NotBlank(message = "Historical period is required")
    private String historicalPeriod;

    @NotBlank(message = "Region focus is required")
    private String regionFocus;

    public HistoryBook() {
        super();
    }

    @JsonIgnore
    @Override
    public String getCategory() {
        return "History";
    }

    public HistoryBook(String title, String author, int publicationYear, String isbn,
                       String historicalPeriod, String regionFocus) {
        super(title, author, publicationYear, isbn);
        this.historicalPeriod = historicalPeriod;
        this.regionFocus = regionFocus;
    }

    public String getHistoricalPeriod() {
        return historicalPeriod;
    }

    public void setHistoricalPeriod(String historicalPeriod) {
        this.historicalPeriod = historicalPeriod;
    }

    public String getRegionFocus() {
        return regionFocus;
    }

    public void setRegionFocus(String regionFocus) {
        this.regionFocus = regionFocus;
    }

    @Override
    public String toString() {
        return super.toString() + " [HistoryBook: period=" + historicalPeriod + ", region=" + regionFocus + "]";
    }


}
