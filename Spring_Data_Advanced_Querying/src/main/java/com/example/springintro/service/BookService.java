package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> findByAgeRestriction(AgeRestriction restriction);

    List<Book> findByEditionAndCopies(EditionType editionType, Integer copies);

    List<Book> findByPriceRange(BigDecimal lowerPrice, BigDecimal higherPrice);

    List<Book> findByReleaseDateNot(Integer year);

    List<Book> findByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findByTitleWhichContainsPattern(String pattern);

    List<Book> findByAuthorLastNameStartingWithPattern(String pattern);

    Integer findBooksCountWithTitleLongerThan(Integer length);

    Integer findBookCopiesCountByAuthor(String firstName, String lastName);

    Book findBookByTitleWithLimitedInfo(String title);
}
