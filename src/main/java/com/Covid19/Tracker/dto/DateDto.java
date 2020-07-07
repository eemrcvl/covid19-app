package com.Covid19.Tracker.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class DateDto {
    private String start;
    @Nullable
    private String end; //nullable, if only 1 date is given then it is set to var. start and a query that returns on the given date is returned
}
