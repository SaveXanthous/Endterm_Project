package org.example.rest_repository;

import org.example.models.VendingMachine;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VendingMachineRepository {

    private final Connection connection;

    public VendingMachineRepository(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public VendingMachine create(VendingMachine vm) {

        String sql = "INSERT INTO vending_machines(name, place, max_cells, max_quantity) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt =
                     connection.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS
                     )) {

            stmt.setString(1, vm.get_name());
            stmt.setString(2, vm.get_place());
            stmt.setInt(3, vm.get_max_cells());
            stmt.setInt(4, vm.get_max_cells());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    vm.set_id(rs.getInt(1));
                }
            }

            return vm;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // READ BY ID
    public VendingMachine getById(Integer id) {

        String sql = "SELECT * FROM vending_machines WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new VendingMachine(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("place"),
                            rs.getInt("max_cells"),
                            rs.getInt("max_quantity")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    // READ ALL
    public List<VendingMachine> getAll() {

        List<VendingMachine> list = new ArrayList<>();

        String sql = "SELECT * FROM vending_machines";

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                list.add(new VendingMachine(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("place"),
                        rs.getInt("max_cells"),
                        rs.getInt("max_quantity")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public VendingMachine update(
            Integer id,
            VendingMachine vm
    ) {

        String sql = " UPDATE vending_machines SET name=?,place=?, max_cells=?, max_quantity=? WHERE id=? ";

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql)) {

            stmt.setString(1, vm.get_name());
            stmt.setString(2, vm.get_place());
            stmt.setInt(3, vm.get_max_cells());
            stmt.setInt(4, vm.get_max_quantity());
            stmt.setInt(5, id);

            stmt.executeUpdate();

            return getById(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id) {

        String sql =
                "DELETE FROM vending_machines WHERE id=?";

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
