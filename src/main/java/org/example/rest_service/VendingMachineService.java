package org.example.rest_service;

import org.example.models.VendingMachine;
import org.example.rest_repository.VendingMachineRepository;
import org.example.rest_service.abstract_classes.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendingMachineService
        extends AbstractCrudService<VendingMachine, Integer> {

    private final VendingMachineRepository repository;

    public VendingMachineService(
            VendingMachineRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public VendingMachine create(VendingMachine entity) {
        return repository.create(entity);
    }

    @Override
    public List<VendingMachine> getAll() {
        return repository.getAll();
    }

    @Override
    public VendingMachine getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public VendingMachine update(
            Integer id,
            VendingMachine entity
    ) {
        return repository.update(id, entity);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
