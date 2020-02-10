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

    @Mock
    private SearchCriteriaCreator searchCriteriaCreator;

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
        Mockito.verify(filmDao,times(1)).getFilmViewsOnPag(CURRENT_PAGE,PAGE_SIZE);
        Mockito.verify(filmDao,times(1)).getCountFilmViews();
        assertEquals(filmsOnPage.getCurrentPage(),CURRENT_PAGE);
        assertEquals(filmsOnPage.getMaxPages(),FILM_MAX_PAGES);
        assertEquals(filmsOnPage.getViews().size(),views.size());
    }

    @Test
    public void getFilmsOnPageBySearchThrowDateTimeParseExceptionTest(){
        when(searchCriteriaCreator.createFilmCriteria(anyString())).thenThrow(DateTimeParseException.class);
        final Page<FilmView> filmsOnPage = filmService.getFilmsOnPage(CURRENT_PAGE,"200");
        Mockito.verify(searchCriteriaCreator,times(1)).createFilmCriteria(anyString());
        assertNotNull(filmsOnPage.getException());
    }

    @Test
    public void getFilmsOnPageBySearchThrowIllegalArgumentExceptionTest(){
        String search = "ids=1/";
        Deque<SearchCriteria> criterias = new ArrayDeque<>();
        when(searchCriteriaCreator.createFilmCriteria(search)).thenReturn(criterias);
        when(filmDao.getFilmViewsOnPageByFilters(criterias,CURRENT_PAGE,PAGE_SIZE)).thenThrow(IllegalArgumentException.class);
        final Page<FilmView> filmsOnPage = filmService.getFilmsOnPage(CURRENT_PAGE, search);
        Mockito.verify(searchCriteriaCreator,times(1)).createFilmCriteria(search);
        Mockito.verify(filmDao,times(1)).getFilmViewsOnPageByFilters(criterias,CURRENT_PAGE,PAGE_SIZE);
        assertNotNull(filmsOnPage.getException());

    }

    @Test
    public void getFilmsOnPageBySearch(){
        String search = "id=1/";
        Deque<SearchCriteria> criterias = new ArrayDeque<>();
        List<FilmView>views = new ArrayList<>();
        when(searchCriteriaCreator.createFilmCriteria(search)).thenReturn(criterias);
        when(filmDao.getFilmViewsOnPageByFilters(criterias,CURRENT_PAGE,PAGE_SIZE)).thenReturn(views);
        when(filmDao.getCountFilmViewsByFilters(criterias)).thenReturn(COUNT_FILMS);
        final Page<FilmView> filmsOnPage = filmService.getFilmsOnPage(CURRENT_PAGE, search);
        Mockito.verify(searchCriteriaCreator,times(1)).createFilmCriteria(search);
        Mockito.verify(filmDao,times(1)).getFilmViewsOnPageByFilters(criterias,CURRENT_PAGE,PAGE_SIZE);
        Mockito.verify(filmDao,times(1)).getCountFilmViewsByFilters(criterias);
        assertNull(filmsOnPage.getException());
    }
}
