package mk.ukim.finki.emt_labs.service;

import mk.ukim.finki.emt_labs.model.Book;
import mk.ukim.finki.emt_labs.model.dto.BookDto;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    Optional<Book> findById(Long id);
    Optional<Book> create(BookDto bookDto);
    Optional<Book> update(Long id, BookDto bookDto);
    Optional<Book> delete(Long id);
    Optional<Book> markAsTaken(Long id);
}
