package com.godeltechnologies.adamovichas.service;

import com.godeltechnologies.adamovichas.cinema.model.dto.Page;
import com.godeltechnologies.adamovichas.cinema.model.view.FilmView;

public interface IFilmService {

 Page<FilmView> getFilmsOnPage(int currentPage);

    Page<FilmView> getFilmsOnPage(int currentPage, String search);
}
