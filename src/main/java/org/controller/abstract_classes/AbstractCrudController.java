package org.controller.abstract_classes;

import org.rest_service.abstract_classes.AbstractCrudService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractCrudController<T, ID> {

    protected abstract AbstractCrudService<T, ID> getService();

    @PostMapping
    public T create(@RequestBody T entity) {
        return getService().create(entity);
    }

    @GetMapping
    public List<T> getAll() {
        return getService().getAll();
    }

    @GetMapping("/{id}")
    public T getById(@PathVariable ID id) {
        return getService().getById(id);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable ID id, @RequestBody T entity) {
        return getService().update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        getService().delete(id);
    }
}
