package org.example.rest_repository;

import org.example.models.Cell;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CellRepository {

    private final Connection connection;

    public CellRepository(Connection connection) {
        this.connection = connection;
    }

    public Cell create(Cell cell) {

        String sql = "INSERT INTO cells(vending_machine_id, item_id, quantity) VALUES (?, ?, ?)";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            stmt.setInt(1, cell.get_vending_machine_id());
            stmt.setInt(2, cell.get_item_id());
            stmt.setInt(3, cell.get_quantity());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cell.set_id(rs.getInt(1));
                }
            }

            return cell;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // READ BY ID
    public Cell getById(Integer id) {

        String sql = "SELECT * FROM cells WHERE id=?";

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new Cell(
                            rs.getInt("id"),
                            rs.getInt("vending_machine_id"),
                            rs.getInt("item_id"),
                            rs.getInt("quantity")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Cell> getAll() {

        List<Cell> list = new ArrayList<>();

        String sql = "SELECT * FROM cells";

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                list.add(new Cell(
                        rs.getInt("id"),
                        rs.getInt("vending_machine_id"),
                        rs.getInt("item_id"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public Cell update(Integer id, Cell cell) {

        String sql = """
            UPDATE cells
            SET vending_machine_id=?,
                item_id=?,
                quantity=?
            WHERE id=?
        """;

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql)) {

            stmt.setInt(1, cell.get_vending_machine_id());
            stmt.setInt(2, cell.get_item_id());
            stmt.setInt(3, cell.get_quantity());
            stmt.setInt(4, id);

            stmt.executeUpdate();

            return getById(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id) {

        String sql = "DELETE FROM cells WHERE id=?";

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
