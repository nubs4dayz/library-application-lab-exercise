package mk.ukim.finki.emt_labs.web.rest;

import mk.ukim.finki.emt_labs.model.Book;
import mk.ukim.finki.emt_labs.model.dto.BookDto;
import mk.ukim.finki.emt_labs.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping({"/", "/books"})
public class BooksRestController {

    private final BookService bookService;

    public BooksRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> listAll(){
        return bookService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return bookService.create(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id,
                                     @RequestBody BookDto bookDto) {
        return bookService.update(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable Long id) {
        return bookService.delete(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/take/{id}")
    public ResponseEntity<Book> takeBook(@PathVariable Long id) {
        return this.bookService.markAsTaken(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
