package com.godeltechnologies.adamovichas.cinema.dao.impl;

import com.godeltechnologies.adamovichas.cinema.dao.entity.converter.EntityDtoConverter;
import com.godeltechnologies.adamovichas.cinema.dao.IFilmDao;
import com.godeltechnologies.adamovichas.cinema.dao.criteria.specification.SearchSpecification;
import com.godeltechnologies.adamovichas.cinema.dao.repository.FilmRepository;
import com.godeltechnologies.adamovichas.cinema.dao.entity.FilmEntity;
import com.godeltechnologies.adamovichas.cinema.model.search.SearchCriteria;
import com.godeltechnologies.adamovichas.cinema.model.view.FilmView;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FilmDao implements IFilmDao {

    private final FilmRepository filmRepository;


    public FilmDao(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }


    @Override
    public List<FilmView> getFilmViewsOnPageByFilters(Deque<SearchCriteria> criterias, int currentPage, int pageSize) {
        Deque<SearchCriteria> criteriaDeque = new ArrayDeque<>(criterias);
        final Specification<FilmEntity> specification = SearchSpecification.getSpecification(criteriaDeque);
        final List<FilmEntity> filmEntities = filmRepository.findAll(specification, PageRequest.of(currentPage - 1, pageSize, Sort.by("id"))).toList();
        return getFilmViews(filmEntities);
    }

    @Override
    public List<FilmView> getFilmViewsOnPag(int currentPage, int pageSize){
        final List<FilmEntity> filmEntities = filmRepository.findAll(PageRequest.of(currentPage - 1, pageSize, Sort.by("id"))).toList();
        return getFilmViews(filmEntities);
    }

    private List<FilmView> getFilmViews(List<FilmEntity> filmEntities) {
        List<FilmView> views= new ArrayList<>();
        for (FilmEntity filmEntity : filmEntities) {
            views.add(EntityDtoConverter.getView(filmEntity));
        }
        return views;
    }

    @Override
    public Long getCountFilmViews(){
        return filmRepository.count();
    }

    @Override
    public Long getCountFilmViewsByFilters(Deque<SearchCriteria> criterias){
        Deque<SearchCriteria> criteriaDeque = new ArrayDeque<>(criterias);
        final Specification specification = SearchSpecification.getSpecification(criteriaDeque);
        return filmRepository.count(specification);
    }
}
