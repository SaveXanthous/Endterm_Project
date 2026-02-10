package org.rest_repository.interfaces;

import java.util.List;

public interface SpringCrudRepository<T, ID> {
    T create(T entity);
    T getById(ID id);
    List<T> getAll();
    T update(ID id, T entity);
    void delete(ID id);
}