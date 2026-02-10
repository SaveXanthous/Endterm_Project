package org.example.rest_service;

import org.example.models.Cell;
import org.example.rest_repository.CellRepository;
import org.example.rest_service.abstract_classes.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CellService
        extends AbstractCrudService<Cell, Integer> {

    private final CellRepository repository;

    public CellService(CellRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cell create(Cell entity) {
        return repository.create(entity);
    }

    @Override
    public List<Cell> getAll() {
        return repository.getAll();
    }

    @Override
    public Cell getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public Cell update(Integer id, Cell entity) {
        return repository.update(id, entity);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
