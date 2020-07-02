package com.Covid19.Tracker.repository;

import com.Covid19.Tracker.base.BaseRepository;
import com.Covid19.Tracker.model.Country;

import java.util.Date;

public interface CountryRepository extends BaseRepository<Country> {
    String findByCountryCode(String countryCode);
    String findByCountryName(String name);
    Date findByDateBetween(Date startDate, Date endDate);
    int findByTotalConfirmed(int number);
}
