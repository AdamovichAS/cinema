package com.godeltechnologies.adamovichas.cinema.model.request;

import static java.util.Objects.nonNull;

public class SearchRequest {


    private Integer currentPage;
    private String search;

    public Integer getCurrentPage() {
        if(currentPage ==null){
            return 1;
        }
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getSearch() {
        if(nonNull(search) && search.equals("")){
            return null;
        }
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
