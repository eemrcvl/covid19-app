package com.Covid19.Tracker.controller;

import com.Covid19.Tracker.dto.CountryDto;
import com.Covid19.Tracker.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ApiController {
    private final CountryService countryService;
    public ApiController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{countryCode}")
    public ResponseEntity<CountryDto> getStatsByCountry(@PathVariable String countryCode){
        return countryService.getByCountryCode(countryCode);
    }

}
