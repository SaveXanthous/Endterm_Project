package org.example.repository.abstract_classes;

import org.example.exceptions.DatabaseOperationException;
import org.example.models.abstract_classes.BaseJunctionEntity;

import java.sql.Connection;
import java.util.List;

public abstract class BaseJunctionRepository {
    protected Connection connection;

    public BaseJunctionRepository(Connection connection) {
        this.connection = connection;
    }

    public abstract void create(BaseJunctionEntity new_entity) throws DatabaseOperationException;
    public abstract BaseJunctionEntity get_by_id(int id) throws DatabaseOperationException;
    public abstract void update_by_id(int id, BaseJunctionEntity new_entity) throws DatabaseOperationException;
    public abstract void delete_by_id(int id) throws DatabaseOperationException;
    public abstract List<BaseJunctionEntity> get_all() throws DatabaseOperationException;
}
