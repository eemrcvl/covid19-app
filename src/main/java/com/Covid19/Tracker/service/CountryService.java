package com.Covid19.Tracker.service;

import com.Covid19.Tracker.dto.CountryDto;
import com.Covid19.Tracker.model.Country;
import com.Covid19.Tracker.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }
    public ResponseEntity<CountryDto> findCountryByCountryCode(String countryCode) {
        Country country = countryRepository.getByCountryCode(countryCode);
        CountryDto countryDto = modelMapper.map(country, CountryDto.class);
        return new ResponseEntity<CountryDto>(countryDto, HttpStatus.OK);
    }
    public ResponseEntity<CountryDto> findCountryByName(String name){
        Country country = countryRepository.getByCountryName(name);
        CountryDto countryDto = modelMapper.map(country,CountryDto.class);
        return new ResponseEntity<CountryDto>(countryDto,HttpStatus.OK);
    }
    /*public ResponseEntity<CountryDto> findByDate(String date){

    }*/
    //TODO: find between date, after postgresql impl.


}
