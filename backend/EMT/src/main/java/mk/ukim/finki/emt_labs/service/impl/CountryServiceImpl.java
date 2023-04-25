package mk.ukim.finki.emt_labs.service.impl;

import mk.ukim.finki.emt_labs.model.Country;
import mk.ukim.finki.emt_labs.model.dto.CountryDto;
import mk.ukim.finki.emt_labs.repository.CountryRepository;
import mk.ukim.finki.emt_labs.service.CountryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> create(CountryDto countryDto) {
        Country country = new Country(countryDto.getName(), countryDto.getContinent());
        countryRepository.save(country);
        return Optional.of(country);
    }
}
