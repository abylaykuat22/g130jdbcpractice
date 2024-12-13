package kz.bitlab.g130springjdbc2.db;

import jakarta.annotation.PostConstruct;
import kz.bitlab.g130springjdbc2.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class DBConnector {

    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=g130",
                "kuatabylay",
                "postgres"
        );
    }

}
