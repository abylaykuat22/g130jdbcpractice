package kz.bitlab.g130springjdbc2.service;

import kz.bitlab.g130springjdbc2.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final Connection connection;

    public void addBrand(Brand brand) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO brands(name, code) " +
                            "VALUES (?, ?)"
            );
            statement.setString(1, brand.getName());
            statement.setString(2, brand.getCode());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Brand> getBrands() throws SQLException {
        List<Brand> brands = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM brands");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Brand brand = new Brand();
                brand.setId(resultSet.getLong("id"));
                brand.setName(resultSet.getString("name"));
                brand.setCode(resultSet.getString("code"));
                brands.add(brand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        }
        return brands;
    }

    public Brand getBrandById(Long id) throws SQLException {
        Brand brand = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM brands WHERE id = ?"
            );
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                brand = new Brand();
                brand.setId(id);
                brand.setName(resultSet.getString("name"));
                brand.setCode(resultSet.getString("code"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        }
        return brand;
    }

    public void updateBrand(Brand brand) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE brands SET name = ?, code = ? WHERE id = ?"
            );
            statement.setString(1, brand.getName());
            statement.setString(2, brand.getCode());
            statement.setLong(3, brand.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) statement.close();
        }
    }
}
