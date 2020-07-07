package com.Covid19.Tracker.controller;

import com.Covid19.Tracker.dto.CountryDto;
import com.Covid19.Tracker.dto.DateDto;
import com.Covid19.Tracker.dto.GlobalDto;
import com.Covid19.Tracker.service.CountryService;
import com.Covid19.Tracker.service.GlobalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ApiController {
    private final CountryService countryService;
    private final GlobalService globalService;
    public ApiController(CountryService countryService, GlobalService globalService) {
        this.countryService = countryService;
        this.globalService = globalService;
    }

    @GetMapping("/{countryCode}")
    public ResponseEntity<CountryDto> getStatsByCountryCode(@PathVariable String countryCode){
        return countryService.findCountryByCountryCode(countryCode);
    }
    @GetMapping("/{countryName}")
    public ResponseEntity<CountryDto> getStatsByCountryName(@PathVariable String countryName){
        return countryService.findCountryByName(countryName);
    }
    /*@PostMapping("/date-country")
    public ResponseEntity<CountryDto> getStatsByDateCountry(@RequestBody DateDto dateDto){
        if(dateDto.getEnd().isEmpty())


    }*/
    @PostMapping("/date-global")
    public ResponseEntity<?> getStatsByDateGlobal(@RequestBody DateDto dateDto){
        if(dateDto.getEnd().isEmpty()) {
            return globalService.findByDate(dateDto.getStart());
        } else
            return globalService.findByDate(dateDto.getStart(),dateDto.getEnd());
    }
}
