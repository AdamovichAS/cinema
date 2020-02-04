package com.godeltechnologies.adamovichas.cinema.dao.repository;

import com.godeltechnologies.adamovichas.cinema.dao.entity.GenreEntiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends JpaRepository<GenreEntiry,Long> {
    GenreEntiry getById(@Param("id") Long id);
}
