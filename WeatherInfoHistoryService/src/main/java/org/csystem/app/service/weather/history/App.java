package org.csystem.app.service.weather.history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "org.csystem")
@EntityScan(basePackages = "org.csystem")
@EnableJpaRepositories(basePackages = "org.csystem")
public class App {
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}
}
