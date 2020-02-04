package com.godeltechnologies.adamovichas.cinema.model.search;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCriteria {

    private String key;
    private String operation;
    private Object value;

    private static final String regex = "(\\w+)(:|<|>|=)([\\wА-Яа-я-]+)#";

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }



    public static Deque<SearchCriteria> create(String search){
        Deque<SearchCriteria>criterias = new ArrayDeque<>();
        Pattern pattern = Pattern.compile(regex,Pattern.UNICODE_CHARACTER_CLASS);
        final Matcher matcher = pattern.matcher(search + "#");
        while (matcher.find()){
            criterias.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
        }
        return criterias;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
