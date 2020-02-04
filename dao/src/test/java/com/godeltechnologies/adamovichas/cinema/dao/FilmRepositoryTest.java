package com.godeltechnologies.adamovichas.cinema.dao;

import com.godeltechnologies.adamovichas.cinema.dao.config.DaoConfig;
import com.godeltechnologies.adamovichas.cinema.dao.config.HibernateConfig;
import com.godeltechnologies.adamovichas.cinema.dao.config.SettingsConfig;
import com.godeltechnologies.adamovichas.cinema.model.search.SearchCriteria;
import com.godeltechnologies.adamovichas.cinema.dao.criteria.specification.SearchSpecification;
import com.godeltechnologies.adamovichas.cinema.dao.repository.FilmRepository;
import com.godeltechnologies.adamovichas.cinema.dao.util.IUtil;
import com.godeltechnologies.adamovichas.cinema.dao.util.Util;
import com.godeltechnologies.adamovichas.cinema.dao.entity.FilmEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class, SettingsConfig.class, Util.class})
@Transactional
@Rollback()
public class FilmRepositoryTest {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private IUtil util;

    @Test
    public void greathThenSpec(){
//        final GenreDto testGenre = util.createTestGenre();
//        final DirectorDto testDirector = util.createTestDirector();
//        final FilmDto testFilm = util.createTestFilm();
        final SearchSpecification specification = new SearchSpecification(new SearchCriteria("directorId", "=", "2"));
        final List<FilmEntity> all = filmRepository.findAll(specification);
        for (FilmEntity filmEntity : all) {
            System.out.println(filmEntity.getId());
        }
    }
}
