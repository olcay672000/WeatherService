package org.csystem.app.service.rest.weather.configuration.datasourceproperties;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackupDataSourcePropertiesConfig {
    @Bean("spring.datasource.weatherinfo.backup")
    @ConfigurationProperties("spring.datasource.weatherinfo.backup")
    public DataSourceProperties weatherInfoBackupDataProperties()
    {
        return new DataSourceProperties();
    }
}