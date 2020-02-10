package com.godeltechnologies.adamovichas.service.creator;

import com.godeltechnologies.adamovichas.cinema.model.search.SearchCriteria;

import java.util.Deque;

public interface ISearchCriteriaCreator {
    Deque<SearchCriteria> createFilmCriteria(String search);
}
