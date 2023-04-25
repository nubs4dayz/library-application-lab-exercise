package mk.ukim.finki.emt_labs.web.rest;

import mk.ukim.finki.emt_labs.model.Author;
import mk.ukim.finki.emt_labs.service.AuthorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/authors")
public class AuthorsRestController {

    private final AuthorService authorService;

    public AuthorsRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    private List<Author> findAll() {
        return authorService.listAll();
    }
}
