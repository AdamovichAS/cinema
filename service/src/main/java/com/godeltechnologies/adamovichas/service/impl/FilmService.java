package com.godeltechnologies.adamovichas.service.impl;

import com.godeltechnologies.adamovichas.cinema.model.search.SearchCriteria;
import com.godeltechnologies.adamovichas.cinema.dao.IFilmDao;
import com.godeltechnologies.adamovichas.cinema.model.dto.Page;
import com.godeltechnologies.adamovichas.cinema.model.view.FilmView;
import com.godeltechnologies.adamovichas.service.IFilmService;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class FilmService implements IFilmService {

    private static final int PAGE_SIZE = 5;

    private final IFilmDao filmDao;

    public FilmService(IFilmDao filmDao) {
        this.filmDao = filmDao;
    }

    @Override
    public Page<FilmView> getFilmsOnPage(int currentPage) {
        final List<FilmView> filmViewsOnPag = filmDao.getFilmViewsOnPag(currentPage, PAGE_SIZE);
        final Long countFilms = filmDao.getCountFilmViews();
        final Long filmViewsMaxPages = getFilmViewsMaxPages(countFilms);

        return new Page<>(PAGE_SIZE,currentPage,filmViewsMaxPages,filmViewsOnPag);
    }

    @Override
    public Page<FilmView> getFilmsOnPage(int currentPage, String search){
        final Deque<SearchCriteria> criterias = SearchCriteria.create(search);
        final List<FilmView> views = filmDao.getFilmViewsOnPageByFilters(new ArrayDeque<>(criterias), currentPage, PAGE_SIZE);
        final Long countFilms = filmDao.getCountFilmViewsByFilters(criterias);
        final Long filmViewsMaxPages = getFilmViewsMaxPages(countFilms);
        return new Page<>(PAGE_SIZE,currentPage,filmViewsMaxPages,views);
    }


    private Long getFilmViewsMaxPages(Long countFilms){
        Long maxPages = countFilms / PAGE_SIZE;
        if(countFilms % PAGE_SIZE > 0){
            maxPages++;
        }
        return maxPages;
    }
}
