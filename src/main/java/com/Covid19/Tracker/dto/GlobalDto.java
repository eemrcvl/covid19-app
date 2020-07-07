package com.Covid19.Tracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GlobalDto {
    private LocalDate date;
    private int newConfirmed;
    private int totalConfirmed;
    private int newDeaths;
    private int totalDeaths;
    private int newRecovered;
    private int totalRecovered;
}
