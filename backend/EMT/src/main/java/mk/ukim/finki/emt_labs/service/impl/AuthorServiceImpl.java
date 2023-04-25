package mk.ukim.finki.emt_labs.service.impl;

import mk.ukim.finki.emt_labs.model.Author;
import mk.ukim.finki.emt_labs.model.Country;
import mk.ukim.finki.emt_labs.model.dto.AuthorDto;
import mk.ukim.finki.emt_labs.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emt_labs.repository.AuthorRepository;
import mk.ukim.finki.emt_labs.repository.CountryRepository;
import mk.ukim.finki.emt_labs.service.AuthorService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> create(AuthorDto authorDto) {
        Optional<Country> country = countryRepository.findById(authorDto.getCountry());
        if(country.isEmpty())
            throw new InvalidCountryIdException();

        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country.get());
        authorRepository.save(author);
        return Optional.of(author);
    }
}
