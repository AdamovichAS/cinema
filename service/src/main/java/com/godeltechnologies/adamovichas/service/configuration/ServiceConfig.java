package com.godeltechnologies.adamovichas.service.configuration;

import com.godeltechnologies.adamovichas.cinema.dao.IFilmDao;
import com.godeltechnologies.adamovichas.service.IFilmService;
import com.godeltechnologies.adamovichas.service.creator.ISearchCriteriaCreator;
import com.godeltechnologies.adamovichas.service.creator.SearchCriteriaCreator;
import com.godeltechnologies.adamovichas.service.impl.FilmService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public ISearchCriteriaCreator searchCriteriaCreator(){
        return new SearchCriteriaCreator();
    }

    @Bean
    public IFilmService filmService(IFilmDao filmDao,ISearchCriteriaCreator searchCriteriaCreator){
        return new FilmService(filmDao,searchCriteriaCreator);
    }
}
