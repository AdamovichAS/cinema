package com.godeltechnologies.adamovichas.cinema.dao.repository;

import com.godeltechnologies.adamovichas.cinema.dao.entity.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DirectorRepository extends JpaRepository<DirectorEntity,Long> {
    DirectorEntity getById(@Param("id") Long id);
}
