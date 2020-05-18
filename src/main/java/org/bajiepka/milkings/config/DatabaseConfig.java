package org.bajiepka.milkings.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() throws URISyntaxException {

        //  Heroku way
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String userInfo = dbUri.getUserInfo();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(String.format("jdbc:postgresql://%s:%s%s",
                dbUri.getHost(),
                dbUri.getPort(),
                dbUri.getPath()));
        dataSource.setUsername(userInfo.split(":")[0]);
        dataSource.setPassword(userInfo.split(":")[1]);
        return dataSource;
    }
}
