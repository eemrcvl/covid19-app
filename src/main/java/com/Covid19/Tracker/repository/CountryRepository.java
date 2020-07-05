package com.Covid19.Tracker.repository;

import com.Covid19.Tracker.base.BaseRepository;
import com.Covid19.Tracker.model.Country;

import java.util.Date;

public interface CountryRepository extends BaseRepository<Country> {
    Country getByCountryCode(String countryCode);
    String getByCountryName(String name);
    Date getByDateBetween(Date startDate, Date endDate);
    int getByTotalConfirmed(int number);
}
