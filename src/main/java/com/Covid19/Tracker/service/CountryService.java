package com.Covid19.Tracker.service;

import com.Covid19.Tracker.model.Country;
import com.Covid19.Tracker.repository.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    public ResponseEntity<Country> getByCountryCode(String countryCode) {
        Country country = countryRepository.getByCountryCode(countryCode);
        return new ResponseEntity<Country>(country, HttpStatus.OK);
    }

}
