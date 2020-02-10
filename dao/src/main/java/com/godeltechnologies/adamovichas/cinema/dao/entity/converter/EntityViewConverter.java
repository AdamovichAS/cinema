package com.godeltechnologies.adamovichas.cinema.dao.entity.converter;

import com.godeltechnologies.adamovichas.cinema.dao.entity.FilmEntity;
import com.godeltechnologies.adamovichas.cinema.model.view.FilmView;

public abstract class EntityViewConverter {


    public static FilmView getView(FilmEntity entity){
        final FilmView view = new FilmView();
        view.setDirectorId(entity.getDirectorId());
        view.setDirectorFirstName(entity.getDirector().getFirstName());
        view.setDirectorLastName(entity.getDirector().getLastName());
        view.setDirectorBirthDate(entity.getDirector().getBirthDate());
        view.setFilmName(entity.getName());
        view.setFilmGenre(entity.getGenre().getName());
        view.setFilmReleaseDate(entity.getReleaseDate());
        return view;
    }
}
