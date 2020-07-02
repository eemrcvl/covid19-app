package com.Covid19.Tracker.model;

import com.Covid19.Tracker.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Global extends BaseEntity {
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

}
