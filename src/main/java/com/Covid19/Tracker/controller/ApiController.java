package com.Covid19.Tracker.controller;

import com.Covid19.Tracker.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1")
public class ApiController {
    private static final String APIUrl = "https://api.covid19api.com/summary";
    private final CountryService countryService;

    public ApiController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/data")
    public void getDataFromURL() throws IOException {
        countryService.saveToDatabase();
    }
}
