package com.godeltechnologies.adamovichas.cinema.web.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

public abstract class SearchRequestValidation {

    private static final Logger log = LoggerFactory.getLogger(SearchRequestValidation.class);
    private static final String searchRequestRegEx = "^((\\w+)(=|<|>)([\\wА-Яа-яёЁ :!?.,-]+)/)+$";

    public static String validateSearch(String searchRequest){
        if(nonNull(searchRequest) && !searchRequest.matches(searchRequestRegEx)){
            log.error("Search is incorrect: {}, at: {}", searchRequest, LocalDateTime.now());
            return "Поиск поддерживает операции >,<,=, условие необходимо закрыть символом /.";
        }
        return null;
    }
}
