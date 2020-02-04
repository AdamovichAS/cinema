package com.godeltechnologies.adamovichas.cinema.model.dto;

import com.godeltechnologies.adamovichas.cinema.model.view.FilmView;

import java.util.List;

public class Page<T> {

    private int pageSize;
    private int currentPage;
    private Long maxPages;
    private List<T> views;

    public Page(int pageSize, int currentPage, Long maxPages, List<T> views) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.maxPages = maxPages;
        this.views = views;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Long getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(Long maxPages) {
        this.maxPages = maxPages;
    }

    public List<T> getViews() {
        return views;
    }

    public void setViews(List<T> views) {
        this.views = views;
    }
}
