package com.godeltechnologies.adamovichas.service.impl;

import com.godeltechnologies.adamovichas.cinema.dao.impl.FilmDao;
import com.godeltechnologies.adamovichas.cinema.model.dto.Page;
import com.godeltechnologies.adamovichas.cinema.model.search.SearchCriteria;
import com.godeltechnologies.adamovichas.cinema.model.view.FilmView;
import com.godeltechnologies.adamovichas.service.creator.SearchCriteriaCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FilmServiceTest {

    @Mock
    private FilmDao filmDao;

    @InjectMocks
    private FilmService filmService;

    private final int CURRENT_PAGE = 1;
    private final int PAGE_SIZE = 5;
    private final Long COUNT_FILMS = 1L;
    private final Long FILM_MAX_PAGES = 1L;

    @BeforeEach
    public void initMock() {
        MockitoAnnotations.initMocks(this);
    }

    private List<FilmView> createView(){
        List<FilmView> views = new ArrayList<>();
        views.add(new FilmView());
        return views;
    }

    @Test
    public void getFilmsOnPageTest(){
        final List<FilmView> views = createView();
        when(filmDao.getFilmViewsOnPag(CURRENT_PAGE,PAGE_SIZE)).thenReturn(views);
        when(filmDao.getCountFilmViews()).thenReturn(COUNT_FILMS);
        final Page<FilmView> filmsOnPage = filmService.getFilmsOnPage(CURRENT_PAGE);
        assertEquals(filmsOnPage.getCurrentPage(),CURRENT_PAGE);
        assertEquals(filmsOnPage.getMaxPages(),FILM_MAX_PAGES);
        assertEquals(filmsOnPage.getViews().size(),views.size());
    }

    @Test
    public void getFilmsOnPageBySearchThrowDateTimeParseException(){
        String search = "id=1/releaseDate=2000/";
        final Page<FilmView> filmsOnPage = filmService.getFilmsOnPage(CURRENT_PAGE, search);
        assertNotNull(filmsOnPage.getException());
    }

//    @Test
//    public void getFilmsOnPageBySearch(){
//        String search = "id=1/";
//        Deque<SearchCriteria> filmCriteria = SearchCriteriaCreator.createFilmCriteria(search);
//        when(filmDao.getFilmViewsOnPageByFilters((Deque<SearchCriteria>) anyCollection(),eq(CURRENT_PAGE),eq(PAGE_SIZE))).thenReturn(new ArrayList<>());
//        when(filmDao.getCountFilmViewsByFilters(filmCriteria)).thenReturn(COUNT_FILMS);
//        final Page<FilmView> filmsOnPage = filmService.getFilmsOnPage(eq(CURRENT_PAGE), search);
//        Mockito.verify(filmDao,times(1)).getCountFilmViewsByFilters(filmCriteria);
//    }
}
