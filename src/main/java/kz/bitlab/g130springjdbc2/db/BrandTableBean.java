package kz.bitlab.g130springjdbc2.db;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import kz.bitlab.g130springjdbc2.entity.Brand;
import kz.bitlab.g130springjdbc2.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class BrandTableBean {

    private final Connection connection;
    private final BrandService brandService;

    @PostConstruct
    public void init() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS brands\n" +
                        "(\n" +
                        "    ID BIGSERIAL PRIMARY KEY ,\n" +
                        "    NAME VARCHAR NOT NULL ,\n" +
                        "    CODE VARCHAR(3) NOT NULL UNIQUE\n" +
                        ");" +
                        "CREATE TABLE IF NOT EXISTS items\n" +
                        "(\n" +
                        "    ID BIGSERIAL PRIMARY KEY ,\n" +
                        "    NAME VARCHAR(200) NOT NULL ,\n" +
                        "    PRICE DOUBLE PRECISION,\n" +
                        "    BRAND_ID BIGINT NOT NULL,\n" +
                        "    FOREIGN KEY (BRAND_ID)\n" +
                        "        REFERENCES brands(ID)\n" +
                        ")"
        );
        statement.execute();
        statement.close();

        Brand brand = new Brand();
        brand.setName("Apple");
        brand.setCode("APL");
        brandService.addBrand(brand);

        Brand brand2 = new Brand();
        brand2.setName("Samsung");
        brand2.setCode("SMG");
        brandService.addBrand(brand2);
    }

    @PreDestroy
    public void destroy() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM items; DELETE FROM brands;"
        );
        statement.executeUpdate();
        statement.close();
    }

}