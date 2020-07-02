package com.Covid19.Tracker.model;

import com.Covid19.Tracker.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country extends BaseEntity {
    @Id
    @NonNull
    private String countryCode;
    @NonNull
    private String countryName;
    @NonNull
    private String slug;
    @NonNull
    private int newConfirmed;
    @NonNull
    private int totalConfirmed;
    @NonNull
    private int newDeaths;
    @NonNull
    private int totalDeaths;
    @NonNull
    private int newRecovered;
    @NonNull
    private int totalRecovered;
    @NonNull
    private String date;

}
