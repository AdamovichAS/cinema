package com.godeltechnologies.adamovichas.cinema.dao.entity.converter;

import com.godeltechnologies.adamovichas.cinema.dao.entity.DirectorEntity;
import com.godeltechnologies.adamovichas.cinema.dao.entity.FilmEntity;
import com.godeltechnologies.adamovichas.cinema.dao.entity.GenreEntiry;
import com.godeltechnologies.adamovichas.cinema.model.dto.DirectorDto;
import com.godeltechnologies.adamovichas.cinema.model.dto.FilmDto;
import com.godeltechnologies.adamovichas.cinema.model.dto.GenreDto;
import com.godeltechnologies.adamovichas.cinema.model.view.FilmView;

public final class EntityDtoConverter {
    private EntityDtoConverter() {
    }

    public static GenreEntiry getEntity(GenreDto dto){
        final GenreEntiry entity = new GenreEntiry();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    public static DirectorEntity getEntity(DirectorDto dto){
        final DirectorEntity entity = new DirectorEntity();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthDate(dto.getBirthDate());
        return entity;
    }

    public static FilmEntity getEntity(FilmDto dto){
        final FilmEntity entity = new FilmEntity();
        entity.setId(dto.getId());
        entity.setDirectorId(dto.getDirectorId());
        entity.setGenreId(dto.getGenreId());
        entity.setName(dto.getName());
        entity.setReleaseDate(dto.getReleaseDate());
        return entity;
    }

    public static FilmView getView(FilmEntity entity){
        final FilmView view = new FilmView();
        view.setDirectorFirstName(entity.getDirector().getFirstName());
        view.setDirectorLastName(entity.getDirector().getLastName());
        view.setDirectorBirthDate(entity.getDirector().getBirthDate());
        view.setFilmName(entity.getName());
        view.setFilmGenre(entity.getGenre().getName());
        view.setFilmReleaseDate(entity.getReleaseDate());
        return view;
    }
}
