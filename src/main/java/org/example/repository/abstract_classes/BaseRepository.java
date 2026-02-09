package org.example.repository.abstract_classes;

import org.example.exceptions.DatabaseOperationException;
import org.example.models.abstract_classes.BaseEntity;

import java.sql.Connection;

import java.util.List;

public abstract class BaseRepository {
    protected Connection connection;

    public BaseRepository(Connection connection){
        this.connection = connection;
    }

    public abstract void create(BaseEntity new_entity) throws DatabaseOperationException;
    public abstract BaseEntity get_by_id(int id) throws DatabaseOperationException;
    public abstract void update_by_id(int id, BaseEntity new_entity) throws DatabaseOperationException;
    public abstract void delete_by_id(int id) throws DatabaseOperationException;
    public abstract List<BaseEntity> get_all() throws DatabaseOperationException;
}