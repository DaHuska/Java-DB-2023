package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findByAgeRestriction(AgeRestriction restriction);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice);

    @Query(value = "FROM Book AS b " +
            "WHERE YEAR(b.releaseDate) != :year")
    List<Book> findByReleaseDateNot(Integer year);

    List<Book> findByReleaseDateBefore(LocalDate releaseDate);

    @Query(value = "FROM Book AS b " +
            "WHERE b.title LIKE CONCAT('%', :pattern, '%')")
    List<Book> findByTitleContainingGivenString(String pattern);

    List<Book> findByAuthorLastNameStartingWith(String pattern);

    @Query(value = "SELECT COUNT(*) FROM Book AS b " +
            "WHERE LENGTH(b.title) > :length")
    Integer findBooksCountWithTitlesLongerThan(Integer length);

    @Query(value = "SELECT SUM(b.copies) " +
            "FROM Book AS b " +
            "WHERE author.firstName = :firstName AND author.lastName = :lastName")
    Integer findBookCopiesCountByAuthor(String firstName, String lastName);

    @Query(value = "SELECT b.title, b.editionType, b.ageRestriction, b.price " +
            "FROM Book AS b " +
            "WHERE b.title = :title")
    Book findByTitleAndPrintOnlyWantedInfo(String title);
}
