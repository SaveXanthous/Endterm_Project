package org.repository;

import org.exceptions.DatabaseOperationException;

import org.models.VendingMachine;
import org.models.abstract_classes.BaseEntity;

import org.repository.abstract_classes.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineRepository extends BaseRepository {

    public VendingMachineRepository(Connection connection) {
        super(connection);
    }

    @Override
    public void create(BaseEntity new_entity) throws DatabaseOperationException {
        VendingMachine vm = (VendingMachine) new_entity;

        String sql = "INSERT INTO vending_machines(name, place, max_cells, max_quantity) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vm.get_name());
            stmt.setString(2, vm.get_place());
            stmt.setInt(3, vm.get_max_cells());
            stmt.setInt(4, vm.get_max_quantity());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("create() VendingMachineRepository problem", e);
        }
    }

    @Override
    public BaseEntity get_by_id(int id) throws DatabaseOperationException {
        String sql = "SELECT id, name, place, max_cells, max_quantity FROM vending_machines WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String place = rs.getString("place");
                    int max_cells = rs.getInt("max_cells");
                    int max_quantity = rs.getInt("max_quantity");

                    return new VendingMachine(id, name, place, max_cells, max_quantity);
                }
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("get_by_id() VendingMachineRepository problem", e);
        }

        return null;
    }

    @Override
    public void update_by_id(int id, BaseEntity new_entity) throws DatabaseOperationException {
        VendingMachine vm = (VendingMachine) new_entity;

        String sql = """
                UPDATE vending_machines
                SET name = ?, place = ?, max_cells = ?, max_quantity = ?
                WHERE id = ?;
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vm.get_name());
            stmt.setString(2, vm.get_place());
            stmt.setInt(3, vm.get_max_cells());
            stmt.setInt(4, vm.get_max_quantity());
            stmt.setInt(5, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("update_by_id() VendingMachineRepository problem", e);
        }
    }

    @Override
    public void delete_by_id(int id) throws DatabaseOperationException {
        String sql = "DELETE FROM vending_machines WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("delete_by_id() VendingMachineRepository problem", e);
        }
    }

    @Override
    public List<BaseEntity> get_all() throws DatabaseOperationException {
        String sql = "SELECT id, name, place, max_cells, max_quantity FROM vending_machines ORDER BY id";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<BaseEntity> machines = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String place = rs.getString("place");
                int max_cells = rs.getInt("max_cells");
                int max_quantity = rs.getInt("max_quantity");

                machines.add(new VendingMachine(id, name, place, max_cells, max_quantity));
            }

            return machines;

        } catch (SQLException e) {
            throw new DatabaseOperationException("get_all() VendingMachineRepository problem", e);
        }
    }
}
