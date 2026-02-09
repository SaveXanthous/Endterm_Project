package org.example.service;

import org.example.models.Item;
import org.example.rest_repository.ItemRepository;
import org.example.service.abstract_classes.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService extends AbstractCrudService<Item, Integer> {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Item create(Item entity) {
        return repository.create(entity);
    }

    @Override
    public List<Item> getAll() {
        return repository.getAll();
    }

    @Override
    public Item getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public Item update(Integer id, Item entity) {
        return repository.update(id, entity);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
