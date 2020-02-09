package com.godeltechnologies.adamovichas.service.configuration;

import com.godeltechnologies.adamovichas.cinema.dao.IFilmDao;
import com.godeltechnologies.adamovichas.service.IFilmService;
import com.godeltechnologies.adamovichas.service.impl.FilmService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class ServiceConfig {

    @Bean
    public IFilmService filmService(IFilmDao filmDao){
        return new FilmService(filmDao);
    }
}
