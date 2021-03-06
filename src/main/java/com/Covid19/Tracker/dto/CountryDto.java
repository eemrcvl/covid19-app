package com.Covid19.Tracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CountryDto {
    private String countryCode;
    private String countryName;
    private int newConfirmed;
    private int totalConfirmed;
    private int newDeaths;
    private int totalDeaths;
    private int newRecovered;
    private int totalRecovered;
    private LocalDate date;
}
