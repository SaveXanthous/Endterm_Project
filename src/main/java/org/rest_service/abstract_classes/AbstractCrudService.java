package org.rest_service.abstract_classes;

import java.util.List;

public abstract class AbstractCrudService<T, ID> {

    public abstract T create(T entity);
    public abstract List<T> getAll();
    public abstract T getById(ID id);
    public abstract T update(ID id, T entity);
    public abstract void delete(ID id);
}
