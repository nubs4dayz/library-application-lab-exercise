package mk.ukim.finki.emt_labs.service;

import mk.ukim.finki.emt_labs.model.Country;
import mk.ukim.finki.emt_labs.model.dto.CountryDto;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAll();
    Optional<Country> findById(Long id);
    Optional<Country> create(CountryDto countryDto);
}
