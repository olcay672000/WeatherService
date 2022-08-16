package org.csystem.app.service.rest.weather.configuration.jpa;

import org.csystem.app.weather.repository.data.entity.WeatherInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.csystem.app.weather.repository.data",
        entityManagerFactoryRef = "weatherInfoEntityManagerFactory", transactionManagerRef = "weatherInfoTransactionManager")
public class JpaConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean weatherInfoEntityManagerFactory(
            @Qualifier("weatherInfoDataSource")DataSource dataSource, EntityManagerFactoryBuilder builder)
    {
        return builder.dataSource(dataSource).packages(WeatherInfo.class).build();
    }

    @Bean
    public PlatformTransactionManager weatherInfoTransactionManager(
            @Qualifier("weatherInfoEntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean)
    {
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean.getObject()));
    }
}