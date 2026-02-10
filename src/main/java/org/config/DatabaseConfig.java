package org.config;

import org.rest_utils.DatabaseConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

@Configuration
public class DatabaseConfig {
    @Bean
    public Connection connection() {
        return DatabaseConnection.getConnection();
    }
}
