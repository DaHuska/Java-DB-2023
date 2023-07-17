package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        // printAllBooksAfterYear(2000);
        // printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        // printAllAuthorsAndNumberOfTheirBooks();
        // printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");
        // printAllBooksByAgeRestriction("miNor");
        // printBooksByEditionAndCopies(EditionType.GOLD, 5000);
        // printBooksByPriceRange(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
        // printBooksByReleaseDateNotFromYear(1998);
        // printBooksByReleaseDateBefore(LocalDate.of(1989, 12, 30));
        // printBooksByTitleContainingStringPattern("sK");
        // printBooksByAuthorsWithLastNameStartingWith();
        // printBooksCountWithTitleLongerThan();
        // printBookCopiesCountByAuthor("Randy", "Graham");
        Book book = bookService.findBookByTitleWithLimitedInfo("Things Fall Apart");

        System.out.println(book.toString());

        // printAuthorsWithFirstNameEndingWith("dy");

    }

    private void printBookCopiesCountByAuthor(String firstName, String lastName) {
        System.out.println(bookService.findBookCopiesCountByAuthor(firstName, lastName));
    }

    private void printBooksCountWithTitleLongerThan() {
        System.out.println(bookService.findBooksCountWithTitleLongerThan(40));
    }

    private void printBooksByAuthorsWithLastNameStartingWith() {
        bookService
                .findByAuthorLastNameStartingWithPattern("Ric")
                .forEach(b -> System.out.printf("%s (%s %s)%n", b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName()));
    }

    private void printBooksByTitleContainingStringPattern(String pattern) {
        bookService
                .findByTitleWhichContainsPattern(pattern)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void printAuthorsWithFirstNameEndingWith(String pattern) {
        authorService
                .findByFirstNameEndingWith(pattern)
                .forEach(a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));
    }

    private void printBooksByReleaseDateBefore(LocalDate localDate) {
        bookService
                .findByReleaseDateBefore(localDate)
                .forEach(b -> System.out.printf("%s %s %.2f%n", b.getTitle(), b.getEditionType(), b.getPrice()));
    }

    private void printBooksByReleaseDateNotFromYear(Integer year) {
        bookService
                .findByReleaseDateNot(year)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void printBooksByPriceRange(BigDecimal lowerPrice, BigDecimal higherPrice) {
        bookService
                .findByPriceRange(lowerPrice, higherPrice)
                .forEach(b -> System.out.printf("%s - $%.2f%n", b.getTitle(), b.getPrice()));
    }

    private void printBooksByEditionAndCopies(EditionType editionType, Integer copies) {
        bookService
                .findByEditionAndCopies(editionType, copies)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void printAllBooksByAgeRestriction(String restriction) {
        bookService.
                findByAgeRestriction(AgeRestriction.valueOf(AgeRestriction.class, restriction.toUpperCase()))
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
