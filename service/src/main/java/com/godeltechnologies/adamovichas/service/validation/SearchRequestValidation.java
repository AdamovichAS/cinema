package com.godeltechnologies.adamovichas.service.validation;

import com.godeltechnologies.adamovichas.cinema.model.request.SearchRequest;
import org.springframework.beans.factory.annotation.Value;

import static java.util.Objects.nonNull;

public abstract class SearchRequestValidation {
 //   @Value("${validation.search}")
    private static final String searchRequestRegEx = "^((\\w+)(=|<|>)([\\wА-Яа-яёЁ :!?.,-]+)/)+$";

    public static String validateSearch(String searchRequest){
        if(nonNull(searchRequest) && !searchRequest.matches(searchRequestRegEx)){
            return "Поиск поддерживает операции >,<,=, условие необходимо закрыть символом /.";
        }
        return null;
    }
}
