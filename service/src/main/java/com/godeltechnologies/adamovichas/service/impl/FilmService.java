package com.godeltechnologies.adamovichas.service.impl;

import com.godeltechnologies.adamovichas.cinema.model.search.SearchCriteria;
import com.godeltechnologies.adamovichas.cinema.dao.IFilmDao;
import com.godeltechnologies.adamovichas.cinema.model.dto.Page;
import com.godeltechnologies.adamovichas.cinema.model.view.FilmView;
import com.godeltechnologies.adamovichas.service.IFilmService;
import com.godeltechnologies.adamovichas.service.creator.ISearchCriteriaCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Deque;
import java.util.List;

public class FilmService implements IFilmService {

    private static final int PAGE_SIZE = 5;

    private final IFilmDao filmDao;
    private final ISearchCriteriaCreator searchCriteriaCreator;

    public FilmService(IFilmDao filmDao, ISearchCriteriaCreator searchCriteriaCreator) {
        this.filmDao = filmDao;
        this.searchCriteriaCreator = searchCriteriaCreator;
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
        Deque<SearchCriteria> criterias;
        try {
            criterias = searchCriteriaCreator.createFilmCriteria(search);
        } catch (DateTimeParseException e) {
            return new Page<>("Поддерживается формат ввода даты YYYY-MM-DD");
        }
        List<FilmView> views;
        try {
            views = filmDao.getFilmViewsOnPageByFilters(criterias, currentPage, PAGE_SIZE);
        } catch (IllegalArgumentException e) {
            return new Page<>("Поддерживается фильтр над полями id, name, releaseDate, genreId, directorId.");
        }
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
