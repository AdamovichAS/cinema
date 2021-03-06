package com.godeltechnologies.adamovichas.cinema.web.validation;


import static java.util.Objects.nonNull;

public abstract class SearchRequestValidation {

    private static final String searchRequestRegEx = "^((\\w+)(=|<|>)([\\wА-Яа-яёЁ :!?.,-]+)/)+$";

    public static String validateSearch(String searchRequest){
        if(nonNull(searchRequest) && !searchRequest.matches(searchRequestRegEx)){
            return "Поиск поддерживает операции >,<,=, условие необходимо закрыть символом /.";
        }
        return null;
    }
}
