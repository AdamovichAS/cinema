package com.godeltechnologies.adamovichas.cinema.dao.util;

import com.godeltechnologies.adamovichas.cinema.model.dto.DirectorDto;
import com.godeltechnologies.adamovichas.cinema.model.dto.FilmDto;
import com.godeltechnologies.adamovichas.cinema.model.dto.GenreDto;

import java.sql.Time;

public class Util implements IUtil {
    @Override
    public GenreDto createTestGenre() {
        final GenreDto genreDto = new GenreDto();
        genreDto.setName("Test");
        return genreDto;
    }

    @Override
    public DirectorDto createTestDirector() {
        final DirectorDto directorDto = new DirectorDto();
        directorDto.setFirstName("Test");
        directorDto.setLastName("Test");
        directorDto.setBirthDate(Time.valueOf("1999-10-10"));
        return directorDto;
    }

    @Override
    public FilmDto createTestFilm() {
        final FilmDto filmDto = new FilmDto();
        filmDto.setName("Test");
        filmDto.setReleaseDate(Time.valueOf("2000-01-01"));
        return filmDto;
    }
}
