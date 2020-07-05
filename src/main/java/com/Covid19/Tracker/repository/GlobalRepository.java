package com.Covid19.Tracker.repository;

import com.Covid19.Tracker.base.BaseRepository;
import com.Covid19.Tracker.model.Global;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface GlobalRepository extends BaseRepository<Global> {
}
