package mk.ukim.finki.emt_labs.service.impl;

import mk.ukim.finki.emt_labs.model.Author;
import mk.ukim.finki.emt_labs.model.Book;
import mk.ukim.finki.emt_labs.model.dto.BookDto;
import mk.ukim.finki.emt_labs.model.exceptions.BookNotInStockException;
import mk.ukim.finki.emt_labs.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.emt_labs.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.emt_labs.repository.AuthorRepository;
import mk.ukim.finki.emt_labs.repository.BookRepository;
import mk.ukim.finki.emt_labs.service.BookService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Optional<Author> author = authorRepository.findById(bookDto.getAuthor());
        if (author.isEmpty())
            throw new InvalidAuthorIdException();

        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author.get(), bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty())
            throw new InvalidBookIdException();

        Optional<Author> author = authorRepository.findById(bookDto.getAuthor());
        if (author.isEmpty())
            throw new InvalidAuthorIdException();

        Book updatedBook = book.get();
        updatedBook.setName(bookDto.getName());
        updatedBook.setCategory(bookDto.getCategory());
        updatedBook.setAuthor(author.get());
        updatedBook.setAvailableCopies(bookDto.getAvailableCopies());
        bookRepository.save(updatedBook);
        return Optional.of(updatedBook);
    }

    @Override
    public Optional<Book> delete(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty())
            throw new InvalidBookIdException();

        bookRepository.deleteById(id);
        return book;
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty())
            throw new InvalidBookIdException();

        Book takenBook = book.get();

        if (takenBook.getAvailableCopies() == 0)
            throw new BookNotInStockException();

        takenBook.setAvailableCopies(takenBook.getAvailableCopies() - 1);
        bookRepository.save(takenBook);
        return Optional.of(takenBook);
    }
}
