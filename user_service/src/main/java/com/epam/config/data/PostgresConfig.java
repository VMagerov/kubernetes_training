package com.epam.config.data;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresConfig {

  @Value("${POSTGRES_DB_HOST:localhost}")
  private String host;

  @Value("${POSTGRES_DB:users}")
  private String databaseName;

  @Value("${POSTGRES_USER:admin}")
  private String user;

  @Value("${POSTGRES_PASSWORD:admin1234}")
  private String password;

  @Bean
  public DataSource getDataSource() {

    return DataSourceBuilder.create()
        .url(String.format("jdbc:postgresql://%s:5432/%s", host, databaseName))
        .driverClassName("org.postgresql.Driver")
        .username(user)
        .password(password)
        .build();
  }
}
