package com.godeltechnologies.adamovichas.service.creator;

import com.godeltechnologies.adamovichas.cinema.model.search.SearchCriteria;


import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCriteriaCreator implements ISearchCriteriaCreator {


    private final String SearchRegEx = "(\\w+)(=|<|>)([\\wА-Яа-яёЁ!?., :-]+)/";


    @Override
    public Deque<SearchCriteria> createFilmCriteria(String search) {
        Deque<SearchCriteria> criterias = new ArrayDeque<>();
        Pattern pattern = Pattern.compile(SearchRegEx, Pattern.UNICODE_CHARACTER_CLASS);
        final Matcher matcher = pattern.matcher(search);
        while (matcher.find()) {
            final String key = matcher.group(1);
            final String operation = matcher.group(2).trim();
            final String value = matcher.group(3).trim();
            if (key.toLowerCase().contains("date")) {
                criterias.add(new SearchCriteria(key, operation, LocalDate.parse(value)));
            } else {
                criterias.add(new SearchCriteria(key, operation, value));
            }
        }
        return criterias;
    }
}
