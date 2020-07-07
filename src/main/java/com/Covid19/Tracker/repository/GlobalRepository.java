package com.Covid19.Tracker.repository;

import com.Covid19.Tracker.base.BaseRepository;
import com.Covid19.Tracker.model.Global;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GlobalRepository extends BaseRepository<Global> {
    Global findByDate(LocalDate date);

    @Query(value = "SELECT g FROM Global g WHERE g.date BETWEEN :start AND :end")
    List<Global> getAllBetweenDates(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
