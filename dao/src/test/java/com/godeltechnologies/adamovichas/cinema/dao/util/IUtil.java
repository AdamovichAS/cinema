package com.godeltechnologies.adamovichas.cinema.dao.util;

import com.godeltechnologies.adamovichas.cinema.model.dto.DirectorDto;
import com.godeltechnologies.adamovichas.cinema.model.dto.FilmDto;
import com.godeltechnologies.adamovichas.cinema.model.dto.GenreDto;

public interface IUtil {

    GenreDto createTestGenre();
    DirectorDto createTestDirector();
    FilmDto createTestFilm();
}
