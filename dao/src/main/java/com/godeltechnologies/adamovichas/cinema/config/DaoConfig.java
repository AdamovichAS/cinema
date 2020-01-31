package com.godeltechnologies.adamovichas.cinema.config;

import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(HibernateConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.godeltechnologies.adamovichas.cinema.dao.repository")
public class DaoConfig {
}
