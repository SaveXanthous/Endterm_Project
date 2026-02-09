package org.example.repository;

import org.example.exceptions.DatabaseOperationException;
import org.example.models.Cell;
import org.example.models.abstract_classes.BaseJunctionEntity;
import org.example.repository.abstract_classes.BaseJunctionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class CellRepository extends BaseJunctionRepository {

    public CellRepository(Connection connection) {
        super(connection);
    }

    @Override
    public void create(BaseJunctionEntity new_entity) throws DatabaseOperationException {
        Cell cell = (Cell) new_entity;

        String sql = "INSERT INTO cells(vending_machine_id, item_id, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cell.get_vending_machine_id());
            stmt.setInt(2, cell.get_item_id());
            stmt.setInt(3, cell.get_quantity());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("create() CellRepository problem", e);
        }
    }

    @Override
    public BaseJunctionEntity get_by_id(int id) throws DatabaseOperationException {
        String sql = "SELECT id, vending_machine_id, item_id, quantity FROM cells WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int vending_machine_id = rs.getInt("vending_machine_id");
                    int item_id = rs.getInt("item_id");
                    int quantity = rs.getInt("quantity");

                    return new Cell(id, vending_machine_id, item_id, quantity);
                }
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("get_by_id() CellRepository problem", e);
        }

        return null;
    }

    @Override
    public void update_by_id(int id, BaseJunctionEntity new_entity) throws DatabaseOperationException {
        Cell cell = (Cell) new_entity;

        String sql = "UPDATE cells SET vending_machine_id = ?, item_id = ?, quantity = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cell.get_vending_machine_id());
            stmt.setInt(2, cell.get_item_id());
            stmt.setInt(3, cell.get_quantity());
            stmt.setInt(4, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("update_by_id() CellRepository problem", e);
        }
    }

    @Override
    public void delete_by_id(int id) throws DatabaseOperationException {
        String sql = "DELETE FROM cells WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("delete_by_id() CellRepository problem", e);
        }
    }

    @Override
    public List<BaseJunctionEntity> get_all() throws DatabaseOperationException {
        String sql = "SELECT id, vending_machine_id, item_id, quantity FROM cells ORDER BY id";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<BaseJunctionEntity> cells = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                int vending_machine_id = rs.getInt("vending_machine_id");
                int item_id = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");

                cells.add(new Cell(id, vending_machine_id, item_id, quantity));
            }

            return cells;

        } catch (SQLException e) {
            throw new DatabaseOperationException("get_all() CellRepository problem", e);
        }
    }
}
