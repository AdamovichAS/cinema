package com.godeltechnologies.adamovichas.cinema.web.controller;

import com.godeltechnologies.adamovichas.cinema.model.dto.Page;
import com.godeltechnologies.adamovichas.cinema.model.view.FilmView;
import com.godeltechnologies.adamovichas.cinema.model.request.SearchRequest;
import com.godeltechnologies.adamovichas.service.IFilmService;
import com.godeltechnologies.adamovichas.service.validation.SearchRequestValidation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping(value = "/main")
public class SearchController {

    private final IFilmService filmService;

    public SearchController(IFilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(value = "")
    public ModelAndView doGet(SearchRequest request) {
        final ModelAndView modelAndView = new ModelAndView("main");
        Integer currentPage = request.getCurrentPage();
        final String search = request.getSearch();
        final String exceptionMessage = SearchRequestValidation.validateSearch(search);
        Page<FilmView> page;
        if (nonNull(search)) {
            if(exceptionMessage == null){
                page = filmService.getFilmsOnPage(currentPage, search);
            }else {
                page = new Page<>(exceptionMessage);
            }
        } else {
            page = filmService.getFilmsOnPage(currentPage);
        }
        page.setSearchRequest(search);
        modelAndView.addObject("page", page);
        return modelAndView;
    }


}
