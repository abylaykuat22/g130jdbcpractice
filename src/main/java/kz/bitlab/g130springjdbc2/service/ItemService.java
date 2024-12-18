package kz.bitlab.g130springjdbc2.service;

import kz.bitlab.g130springjdbc2.entity.Brand;
import kz.bitlab.g130springjdbc2.entity.Item;
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
public class ItemService {

    private final Connection connection;

    public void addItem(Item item) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO items(price, name, brand_id) VALUES(?, ?, ?)"
            );
            statement.setDouble(1, item.getPrice());
            statement.setString(2, item.getName());
            statement.setLong(3, item.getBrand().getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT i.*, b.name AS brand_name, b.code FROM items i JOIN brands b ON i.brand_id = b.id"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                try {
                    Item item = new Item();
                    item.setId(resultSet.getLong("id"));
                    item.setName(resultSet.getString("name"));
                    item.setPrice(resultSet.getDouble("price"));
                    Brand brand = new Brand();
                    brand.setId(resultSet.getLong("brand_id"));
                    brand.setName(resultSet.getString("brand_name"));
                    brand.setCode(resultSet.getString("code"));
                    item.setBrand(brand);
                    items.add(item);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item getItemById(Long id) {
        Item item = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM items i " +
                            "JOIN brands b ON i.brand_id = b.id " +
                            "WHERE id = ?"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                item = new Item();
                item.setId(resultSet.getLong("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                Brand brand = new Brand();
                brand.setId(resultSet.getLong("brand_id"));
                brand.setName(resultSet.getString("brand_name"));
                brand.setCode(resultSet.getString("code"));
                item.setBrand(brand);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public void editItem(Item item) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE items SET name = ?, code = ?, brand_id = ? " +
                            "WHERE id = ?"
            );
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setLong(3, item.getBrand().getId());
            statement.setLong(4, item.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteItemById(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM items WHERE id = ?"
            );
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
