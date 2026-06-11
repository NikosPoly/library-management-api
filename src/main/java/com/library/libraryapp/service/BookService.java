package com.library.libraryapp.service;

import com.library.libraryapp.dto.book.BookDTO;
import com.library.libraryapp.dto.book.HistoryBookDTO;
import com.library.libraryapp.dto.book.LiteratureBookDTO;
import com.library.libraryapp.dto.book.ScienceBookDTO;
import com.library.libraryapp.model.book.Book;
import com.library.libraryapp.model.book.HistoryBook;
import com.library.libraryapp.model.book.LiteratureBook;
import com.library.libraryapp.model.book.ScienceBook;
import com.library.libraryapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Need to include partial update for fields
    public Book updateBook(String id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    // Update common Book fields
                    existingBook.setTitle(updatedBook.getTitle());
                    existingBook.setAuthor(updatedBook.getAuthor());
                    existingBook.setPublicationYear(updatedBook.getPublicationYear());
                    existingBook.setIsbn(updatedBook.getIsbn());
                    existingBook.setAvailable(updatedBook.isAvailable());

                    // Update HistoryBook specific fields
                    if (existingBook instanceof HistoryBook existingHistory && updatedBook instanceof HistoryBook updatedHistory) {
                        existingHistory.setHistoricalPeriod(updatedHistory.getHistoricalPeriod());
                        existingHistory.setRegionFocus(updatedHistory.getRegionFocus());
                    }

                    // Update LiteratureBook specific fields
                    if (existingBook instanceof LiteratureBook existingLit && updatedBook instanceof LiteratureBook updatedLit) {
                        existingLit.setGenre(updatedLit.getGenre());
                        existingLit.setForKids(updatedLit.isForKids());
                        existingLit.setTargetAudience(updatedLit.getTargetAudience());
                    }

                    // Update ScienceBook specific fields
                    if (existingBook instanceof ScienceBook existingSci && updatedBook instanceof ScienceBook updatedSci) {
                        existingSci.setFieldOfStudy(updatedSci.getFieldOfStudy());
                        existingSci.setTextbook(updatedSci.isTextbook());
                        existingSci.setEditionNumber(updatedSci.getEditionNumber());
                    }

                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book with ID " + id + " not found."));

    }

    public Book updateBook(String id, BookDTO patchDTO) {
        boolean isValid = true;

        // Validate all provided fields before applying any changes
        if (patchDTO.getTitle() != null && patchDTO.getTitle().isBlank()) {
            isValid = false;
        }

        if (patchDTO.getAuthor() != null && patchDTO.getAuthor().isBlank()) {
            isValid = false;
        }

        if (patchDTO.getPublicationYear() != null && patchDTO.getPublicationYear() <= 0) {
            isValid = false;
        }

        if (patchDTO.getIsbn() != null && patchDTO.getIsbn().isBlank()) {
            isValid = false;
        }

        // No validation for isAvailable since it's Boolean (nullable)

        // Subtype validation
        if (patchDTO instanceof HistoryBookDTO historyDTO) {
            if (historyDTO.getHistoricalPeriod() != null && historyDTO.getHistoricalPeriod().isBlank()) {
                isValid = false;
            }
            if (historyDTO.getRegionFocus() != null && historyDTO.getRegionFocus().isBlank()) {
                isValid = false;
            }
        } else if (patchDTO instanceof LiteratureBookDTO litDTO) {
            if (litDTO.getGenre() != null && litDTO.getGenre().isBlank()) {
                isValid = false;
            }
            // forKids is Boolean, no validation needed
            if (litDTO.getTargetAudience() != null && litDTO.getTargetAudience().isBlank()) {
                isValid = false;
            }
        } else if (patchDTO instanceof ScienceBookDTO sciDTO) {
            if (sciDTO.getFieldOfStudy() != null && sciDTO.getFieldOfStudy().isBlank()) {
                isValid = false;
            }
            // textbook Boolean no validation needed
            if (sciDTO.getEditionNumber() != null && sciDTO.getEditionNumber() <= 0) {
                isValid = false;
            }
        }

        if (!isValid) {
            System.out.println("One or more fields in the patch are invalid.");
            return null;
        }

        // Apply updates only after validation passes
        return bookRepository.findById(id)
                .map(existingBook -> {
                    if (patchDTO.getTitle() != null) {
                        existingBook.setTitle(patchDTO.getTitle());
                    }
                    if (patchDTO.getAuthor() != null) {
                        existingBook.setAuthor(patchDTO.getAuthor());
                    }
                    if (patchDTO.getPublicationYear() != null) {
                        existingBook.setPublicationYear(patchDTO.getPublicationYear());
                    }
                    if (patchDTO.getIsbn() != null) {
                        existingBook.setIsbn(patchDTO.getIsbn());
                    }
                    if (patchDTO.isAvailable() != null) {
                        existingBook.setAvailable(patchDTO.isAvailable());
                    }

                    if (existingBook instanceof HistoryBook historyBook && patchDTO instanceof HistoryBookDTO historyDTO) {
                        if (historyDTO.getHistoricalPeriod() != null) {
                            historyBook.setHistoricalPeriod(historyDTO.getHistoricalPeriod());
                        }
                        if (historyDTO.getRegionFocus() != null) {
                            historyBook.setRegionFocus(historyDTO.getRegionFocus());
                        }
                    } else if (existingBook instanceof LiteratureBook litBook && patchDTO instanceof LiteratureBookDTO litDTO) {
                        if (litDTO.getGenre() != null) {
                            litBook.setGenre(litDTO.getGenre());
                        }
                        if (litDTO.isForKids() != null) {
                            litBook.setForKids(litDTO.isForKids());
                        }
                        if (litDTO.getTargetAudience() != null) {
                            litBook.setTargetAudience(litDTO.getTargetAudience());
                        }
                    } else if (existingBook instanceof ScienceBook sciBook && patchDTO instanceof ScienceBookDTO sciDTO) {
                        if (sciDTO.getFieldOfStudy() != null) {
                            sciBook.setFieldOfStudy(sciDTO.getFieldOfStudy());
                        }
                        if (sciDTO.isTextbook() != null) {
                            sciBook.setTextbook(sciDTO.isTextbook());
                        }
                        if (sciDTO.getEditionNumber() != null) {
                            sciBook.setEditionNumber(sciDTO.getEditionNumber());
                        }
                    }

                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book with ID " + id + " not found."));
    }




    /*public Book updateBook(String id, Map<String, Object> updates) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            // Common Book fields
                            case "title" -> existingBook.setTitle((String) value);
                            case "author" -> existingBook.setAuthor((String) value);
                            case "publicationYear" -> existingBook.setPublicationYear((Integer) value);
                            case "isbn" -> existingBook.setIsbn((String) value);
                            case "available" -> existingBook.setAvailable((Boolean) value);

                            // HistoryBook specific fields
                            case "historicalPeriod" -> {
                                if (existingBook instanceof HistoryBook historyBook) {
                                    historyBook.setHistoricalPeriod((String) value);
                                }
                            }
                            case "regionFocus" -> {
                                if (existingBook instanceof HistoryBook historyBook) {
                                    historyBook.setRegionFocus((String) value);
                                }
                            }

                            // LiteratureBook specific fields
                            case "genre" -> {
                                if (existingBook instanceof LiteratureBook litBook) {
                                    litBook.setGenre((String) value);
                                }
                            }
                            case "forKids" -> {
                                if (existingBook instanceof LiteratureBook litBook) {
                                    litBook.setForKids((Boolean) value);
                                }
                            }
                            case "targetAudience" -> {
                                if (existingBook instanceof LiteratureBook litBook) {
                                    litBook.setTargetAudience((String) value);
                                }
                            }

                            // ScienceBook specific fields
                            case "fieldOfStudy" -> {
                                if (existingBook instanceof ScienceBook sciBook) {
                                    sciBook.setFieldOfStudy((String) value);
                                }
                            }
                            case "textbook" -> {
                                if (existingBook instanceof ScienceBook sciBook) {
                                    sciBook.setTextbook((Boolean) value);
                                }
                            }
                            case "editionNumber" -> {
                                if (existingBook instanceof ScienceBook sciBook) {
                                    sciBook.setEditionNumber((Integer) value);
                                }
                            }
                            // Add more fields or subclasses if needed
                        }
                    });
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book with ID " + id + " not found."));
    }*/





    public void deleteBook(String id) {
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
        }else{
            throw new RuntimeException("Book with ID " + id + " not found.");
        }
    }
}
