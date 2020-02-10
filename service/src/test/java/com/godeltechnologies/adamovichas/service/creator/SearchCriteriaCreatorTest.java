package com.godeltechnologies.adamovichas.service.creator;

import com.godeltechnologies.adamovichas.cinema.dao.config.DaoConfig;
import com.godeltechnologies.adamovichas.cinema.model.search.SearchCriteria;
import com.godeltechnologies.adamovichas.service.configuration.ServiceConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Deque;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfig.class, DaoConfig.class})
public class SearchCriteriaCreatorTest {

    @Autowired
    private ISearchCriteriaCreator criteriaCreator;

    @Test
    public void createFilmCriteriaTest(){
        String search = "id>1/name=Вл/releaseDate=2000-10-10/";
        final Deque<SearchCriteria> filmCriteria = criteriaCreator.createFilmCriteria(search);
        assertEquals(filmCriteria.size(),3);
        final SearchCriteria idCriteria = filmCriteria.pollFirst();
        assertEquals(idCriteria.getKey(),"id");
        assertEquals(idCriteria.getOperation(),">");
        assertEquals(idCriteria.getValue(),"1");
        final SearchCriteria nameCriteria = filmCriteria.pollFirst();
        assertEquals(nameCriteria.getKey(),"name");
        assertEquals(nameCriteria.getOperation(),"=");
        assertEquals(nameCriteria.getValue(),"Вл");
        final SearchCriteria dateCriteria = filmCriteria.pollFirst();
        assertEquals(dateCriteria.getKey(),"releaseDate");
        assertEquals(dateCriteria.getOperation(),"=");
        assertEquals(dateCriteria.getValue(), LocalDate.parse("2000-10-10"));
    }

    @Test
    public void createFilmCriteriaThrowDateTimeParseExceptionTest(){
        String search = "releaseDate=2000/";
        assertThrows(DateTimeParseException.class,()->criteriaCreator.createFilmCriteria(search));
    }
}
