package com.godeltechnologies.adamovichas.cinema.dao;

import com.godeltechnologies.adamovichas.cinema.model.search.SearchCriteria;
import com.godeltechnologies.adamovichas.cinema.model.view.FilmView;

import java.util.Deque;
import java.util.List;

public interface IFilmDao {

    List<FilmView> getFilmViewsOnPageByFilters(Deque<SearchCriteria> criterias, int currentPage, int PageSize);

    List<FilmView> getFilmViewsOnPag(int currentPage, int pageSize);

    Long getCountFilmViews();

    Long getCountFilmViewsByFilters(Deque<SearchCriteria> criterias);
}
