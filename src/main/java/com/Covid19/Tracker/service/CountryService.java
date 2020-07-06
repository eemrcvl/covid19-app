package com.Covid19.Tracker.service;

import com.Covid19.Tracker.dto.CountryDto;
import com.Covid19.Tracker.model.Country;
import com.Covid19.Tracker.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    @Autowired
    private ModelMapper modelMapper;
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    public ResponseEntity<CountryDto> getByCountryCode(String countryCode) {
        Country country = countryRepository.getByCountryCode(countryCode);
        CountryDto countryDto = modelMapper.map(country, CountryDto.class);
        return new ResponseEntity<CountryDto>(countryDto, HttpStatus.OK);
    }


}
