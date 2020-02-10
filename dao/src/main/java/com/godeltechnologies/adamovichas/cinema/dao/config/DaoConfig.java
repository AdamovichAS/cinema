package com.godeltechnologies.adamovichas.cinema.dao.config;

import com.godeltechnologies.adamovichas.cinema.dao.IFilmDao;
import com.godeltechnologies.adamovichas.cinema.dao.impl.FilmDao;
import com.godeltechnologies.adamovichas.cinema.dao.repository.FilmRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.godeltechnologies.adamovichas.cinema.dao.repository")
public class DaoConfig {

    @Autowired
    private FilmRepository filmRepository;

    @Bean
    public IFilmDao filmDao(){
        return new FilmDao(filmRepository);
    }
}
