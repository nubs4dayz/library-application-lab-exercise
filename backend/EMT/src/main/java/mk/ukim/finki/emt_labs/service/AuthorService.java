package mk.ukim.finki.emt_labs.service;

import mk.ukim.finki.emt_labs.model.Author;
import mk.ukim.finki.emt_labs.model.dto.AuthorDto;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAll();
    Optional<Author> findById(Long id);
    Optional<Author> create(AuthorDto authorDto);
}
