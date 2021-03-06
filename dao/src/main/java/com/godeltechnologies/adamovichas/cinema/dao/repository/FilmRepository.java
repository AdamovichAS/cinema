package com.godeltechnologies.adamovichas.cinema.dao.repository;

import com.godeltechnologies.adamovichas.cinema.dao.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FilmRepository extends JpaRepository<FilmEntity, Long>, JpaSpecificationExecutor<FilmEntity> {
}
