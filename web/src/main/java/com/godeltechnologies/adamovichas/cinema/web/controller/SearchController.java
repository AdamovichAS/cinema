package com.godeltechnologies.adamovichas.cinema.web.controller;

import com.godeltechnologies.adamovichas.cinema.model.dto.Page;
import com.godeltechnologies.adamovichas.cinema.model.view.FilmView;
import com.godeltechnologies.adamovichas.service.IFilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping(value = "/main")
public class SearchController {

    private final IFilmService filmService;

    public SearchController(IFilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(value = "")
    public ModelAndView doGet(HttpServletRequest req){
        String currentPage = req.getParameter("currentPage");
        final String search = req.getParameter("search");
        if (currentPage == null) {
            currentPage = "1";
        }
        int numberPage = Integer.parseInt(currentPage);
        final ModelAndView modelAndView = new ModelAndView("main");
        Page<FilmView> filmsOnPage;
        if(nonNull(search)){
            if(!search.equals("")) {
                filmsOnPage = filmService.getFilmsOnPage(numberPage, search);
            }else {
                filmsOnPage = filmService.getFilmsOnPage(numberPage);
            }
        }else {
            filmsOnPage = filmService.getFilmsOnPage(numberPage);
        }
        modelAndView.addObject("page",filmsOnPage);
        modelAndView.addObject("search",search);
        return modelAndView;
    }


}
