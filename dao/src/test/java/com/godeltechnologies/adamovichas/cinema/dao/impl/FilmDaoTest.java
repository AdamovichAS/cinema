package com.godeltechnologies.adamovichas.cinema.dao.impl;

import com.godeltechnologies.adamovichas.cinema.dao.IFilmDao;
import com.godeltechnologies.adamovichas.cinema.dao.config.DaoConfig;
import com.godeltechnologies.adamovichas.cinema.dao.config.HibernateConfig;
import com.godeltechnologies.adamovichas.cinema.model.search.SearchCriteria;
import com.godeltechnologies.adamovichas.cinema.model.view.FilmView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class})
@Transactional()
public class FilmDaoTest {

    @Autowired
    private IFilmDao filmDao;


    private final String DIRECTOR_ID_KEY = "directorId";
    private final String NAME_KEY = "name";
    private final String RELEASE_DATE_KEY = "releaseDate";

    private final String EQUALS_OR_LIKE_OPERATION = "=";
    private final String GREAT_THEN_OR_EQUALS_OPERATION = ">";
    private final String LESS_THAN_OR_EQUALS_OPERATION = "<";

    private final int PAGE_SIZE = 12;
    private final int COUNT_FILMS_IN_DATA_BASE = 12;
    private final int CURRENT_PAGE = 1;


    @Test
    public void getCountFilmViewsTest(){
        final Long countFilmViews = filmDao.getCountFilmViews();
        assertEquals(countFilmViews,COUNT_FILMS_IN_DATA_BASE);
    }

    @Test
    public void getFilmViewsOnPageTest(){
        final List<FilmView> filmViewsOnPag = filmDao.getFilmViewsOnPag(CURRENT_PAGE, PAGE_SIZE);
        assertEquals(filmViewsOnPag.size(),PAGE_SIZE);
    }

    @Test
    public void getCountFilmViewsByCriteriaIllegalArgumentException(){
        Deque<SearchCriteria> criteria = new ArrayDeque<>();
        criteria.add(new SearchCriteria("ids", EQUALS_OR_LIKE_OPERATION,1));
        assertThrows(IllegalArgumentException.class,()->filmDao.getCountFilmViewsByFilters(criteria));
    }

    @Test
    public void getFilmViewsOnPageByDirectorIdEqualsCriteriaTest(){
        final Long DIRECTOR_ID = 1L;
        Deque<SearchCriteria> criteria = new ArrayDeque<>();
        criteria.add(new SearchCriteria(DIRECTOR_ID_KEY, EQUALS_OR_LIKE_OPERATION,DIRECTOR_ID));
        final List<FilmView> filmViews = filmDao.getFilmViewsOnPageByFilters(criteria, CURRENT_PAGE, PAGE_SIZE);
        for (FilmView view : filmViews) {
            assertEquals(view.getDirectorId(),DIRECTOR_ID);
        }
    }

    @Test
    public void getFilmViewsOnPageByNameLikeOrEqualsCriteriaTest(){
        final String NAME = "Борн";
        Deque<SearchCriteria> criteria = new ArrayDeque<>();
        criteria.add(new SearchCriteria(NAME_KEY, EQUALS_OR_LIKE_OPERATION,NAME));
        final List<FilmView> views = filmDao.getFilmViewsOnPageByFilters(criteria,CURRENT_PAGE,PAGE_SIZE);
        for (FilmView view : views) {
            assertTrue(view.getFilmName().contains(NAME));
        }

    }

    @Test
    public void getFilmViewsOnPageByReleaseBetweenTwoDatesTest(){
        final LocalDate FIRST_DATE = LocalDate.parse("2000-10-10");
        final LocalDate SECOND_DATE = LocalDate.parse("2020-10-10");
        Deque<SearchCriteria> criteria = new ArrayDeque<>();
        criteria.add(new SearchCriteria(RELEASE_DATE_KEY,GREAT_THEN_OR_EQUALS_OPERATION,FIRST_DATE));
        criteria.add(new SearchCriteria(RELEASE_DATE_KEY,LESS_THAN_OR_EQUALS_OPERATION,SECOND_DATE));
        List<FilmView> views = filmDao.getFilmViewsOnPageByFilters(criteria, CURRENT_PAGE, PAGE_SIZE);
        for (FilmView view : views) {
            assertTrue(view.getFilmReleaseDate().isAfter(FIRST_DATE));
            assertTrue(view.getFilmReleaseDate().isBefore(SECOND_DATE));
        }
    }
}
