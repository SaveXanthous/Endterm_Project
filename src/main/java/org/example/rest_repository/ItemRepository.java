package org.example.rest_repository;

import org.example.models.Item;
import org.springframework.stereotype.Repository;
import org.example.rest_repository.interfaces.SpringCrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository implements SpringCrudRepository<Item, Integer> {

    private final Connection connection;

    public ItemRepository(Connection connection) {
        this.connection = connection;
    }

    public Item create(Item item) {
        String sql =
                "INSERT INTO items(name, price) VALUES(?, ?)";

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql)) {

            stmt.setString(1, item.get_name());
            stmt.setDouble(2, item.get_price());

            stmt.executeUpdate();

            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Item getById(Integer id) {

        String sql = "SELECT * FROM items WHERE id=?";

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Item(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Item> getAll() {

        List<Item> list = new ArrayList<>();

        String sql = "SELECT * FROM items";

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Item update(Integer id, Item item) {

        String sql =
                "UPDATE items SET name=?, price=? WHERE id=?";

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql)) {

            stmt.setString(1, item.get_name());
            stmt.setDouble(2, item.get_price());
            stmt.setInt(3, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return getById(id);
    }

    @Override
    public void delete(Integer id) {

        String sql =
                "DELETE FROM items WHERE id=?";

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
