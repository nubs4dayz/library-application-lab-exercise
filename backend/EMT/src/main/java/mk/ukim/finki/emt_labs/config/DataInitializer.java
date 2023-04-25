package mk.ukim.finki.emt_labs.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt_labs.model.dto.AuthorDto;
import mk.ukim.finki.emt_labs.model.dto.BookDto;
import mk.ukim.finki.emt_labs.model.dto.CountryDto;
import mk.ukim.finki.emt_labs.model.enumerations.Category;
import mk.ukim.finki.emt_labs.service.AuthorService;
import mk.ukim.finki.emt_labs.service.BookService;
import mk.ukim.finki.emt_labs.service.CountryService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final AuthorService authorService;
    private final CountryService countryService;
    private final BookService bookService;

    public DataInitializer(AuthorService authorService, CountryService countryService, BookService bookService) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.bookService = bookService;
    }

    @PostConstruct
    public void initData(){
        countryService.create(new CountryDto("England", "Europe"));
        countryService.create(new CountryDto("France", "Europe"));
        countryService.create(new CountryDto("USA", "North America"));

        authorService.create(new AuthorDto("Charles", "Dickens", 1L));
        authorService.create(new AuthorDto("Albert", "Camus", 2L));
        authorService.create(new AuthorDto("Mark", "Twain", 3L));

        bookService.create(new BookDto("Moby Dick", Category.FANTASY, 1L, 20));
        bookService.create(new BookDto("Crime and Punishment", Category.BIOGRAPHY, 2L, 10));
        bookService.create(new BookDto("Les Miserables", Category.DRAMA, 3L, 5));
        bookService.create(new BookDto("Anna Karenina", Category.THRILLER, 3L, 40));
        bookService.create(new BookDto("War and Peace", Category.CLASSICS, 2L, 17));
        bookService.create(new BookDto("Divine Comedy", Category.FANTASY, 1L, 32));
        bookService.create(new BookDto("The Bible", Category.DRAMA, 3L, 34));
        bookService.create(new BookDto("The Raven", Category.HISTORY, 2L, 54));
        bookService.create(new BookDto("The Call of the Wild", Category.NOVEL, 1L, 43));
        bookService.create(new BookDto("Oliver Twist", Category.NOVEL, 3L, 13));
    }
}
