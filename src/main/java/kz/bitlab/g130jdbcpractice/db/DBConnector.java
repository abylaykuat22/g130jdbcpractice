package kz.bitlab.g130jdbcpractice.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DBConnector {

    @Bean
    public Connection connection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=g130",
                "kuatabylay",
                "postgres"
        );
    }
}
