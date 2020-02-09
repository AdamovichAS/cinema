package com.godeltechnologies.adamovichas.cinema.model.request;


import static java.util.Objects.nonNull;

public class SearchRequest {

//    @DecimalMin(value = "0", message = "Page must be greater than 0")
    private Integer currentPage;
//    @Pattern(regexp = "^((id|name|releaseDate|genreId|directorId)(:|<|>|:)([\\wА-Яа-я :!?.,-]+)#)+$",
//            message = "Поиск поддерживает операции >,<,= над полями id,name,releaseDate,genreId,directorId.")
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
