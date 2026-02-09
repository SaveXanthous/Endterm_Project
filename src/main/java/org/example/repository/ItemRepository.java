package org.example.repository;

import org.example.models.abstract_classes.BaseEntity;
import org.example.models.Item;

import org.example.exceptions.DatabaseOperationException;
import org.example.repository.abstract_classes.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ItemRepository extends BaseRepository {
    public ItemRepository(Connection connection){
       super(connection);
    }

    @Override
    public void create(BaseEntity new_entity) throws DatabaseOperationException{
        Item item = (Item) new_entity;
        String sql = "INSERT INTO items(name, price) VALUES(?,?)";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1,item.get_name());
            stmt.setDouble(2,item.get_price());

            stmt.executeUpdate();
        }
        catch (SQLException e){
            throw new DatabaseOperationException("Create() ItemRepository Problem", e);
        }
    }

    @Override
    public BaseEntity get_by_id(int id) throws DatabaseOperationException{
        String sql = "SELECT id, name, price FROM items WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1,id);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");

                    return new Item(id,name,price);
                }
            }

        }
        catch(SQLException e){
            throw new DatabaseOperationException("get_by_id() ItemRepository Problem", e);
        }

        return null;
    }

    @Override
    public void update_by_id(int id, BaseEntity new_entity) throws DatabaseOperationException{
        Item item = (Item) new_entity;
        String sql = "UPDATE items SET name = ?, price = ? WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1,item.get_name());
            stmt.setDouble(2,item.get_price());
            stmt.setInt(3,id);
            stmt.executeUpdate();
        }
        catch(SQLException e){
            throw new DatabaseOperationException("update_by_id() ItemRepository Problem", e);
        }
    }

    @Override
    public void delete_by_id(int id) throws  DatabaseOperationException{
        String sql = "DELETE FROM items WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch(SQLException e){
            throw new DatabaseOperationException("delete_by_id() ItemRepository Problem", e);
        }
    }

    @Override
    public List<BaseEntity> get_all() throws DatabaseOperationException{
        String sql = "SELECT id, name, price FROM items ORDER BY id";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){

            try(ResultSet rs = stmt.executeQuery()){
                List<BaseEntity> items = new ArrayList<>();

                while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");

                    items.add(new Item(id,name,price));
                }

                return items;
            }

        }
        catch(SQLException e){
            throw new DatabaseOperationException("get_all() ItemRepository Problem", e);
        }
    }
}
