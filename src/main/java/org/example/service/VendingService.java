package org.example.service;

import org.example.exceptions.DatabaseOperationException;
import org.example.models.Cell;
import org.example.models.Item;
import org.example.models.VendingMachine;
import org.example.models.abstract_classes.BaseEntity;
import org.example.models.abstract_classes.BaseJunctionEntity;
import org.example.repository.CellRepository;
import org.example.repository.ItemRepository;
import org.example.repository.VendingMachineRepository;

import java.util.ArrayList;
import java.util.List;

public class VendingService implements VendingServiceInterface {

    private final VendingMachineRepository vendingMachineRepository;
    private final CellRepository cellRepository;
    private final ItemRepository itemRepository;
    private final VendingCommandHandler handler;

    public VendingService(
            VendingMachineRepository vendingMachineRepository,
            CellRepository cellRepository,
            ItemRepository itemRepository
    ) {
        this.vendingMachineRepository = vendingMachineRepository;
        this.cellRepository = cellRepository;
        this.itemRepository = itemRepository;

        this.handler = new VendingCommandHandler(this);
    }

    // MENU API

    @Override
    public ServiceMenuResult execute_menu(String cmd) {
        try {
            if (cmd == null || cmd.isBlank()) {
                return new ServiceMenuResult("empty command", null, null);
            }

            String result = handler.handle(cmd);

            ServiceMenuResult selectResult = tryBuildSelectResult(cmd, result);
            if (selectResult != null) return selectResult;

            return new ServiceMenuResult(result, null, null);

        } catch (DatabaseOperationException e) {
            return new ServiceMenuResult("DB error: " + e.getMessage(), null, null);
        } catch (Exception e) {
            return new ServiceMenuResult("Error: " + e.getMessage(), null, null);
        }
    }

    private ServiceMenuResult tryBuildSelectResult(String cmd, String result) {
        String[] parts = cmd.trim().split("\\s+");
        if (parts.length != 2) return null;

        if (!parts[0].equals("select")) return null;

        String table = parts[1];

        String title_list = switch (table) {
            case "vending_machine" -> "id vending machines";
            case "items" -> "id items";
            case "cells" -> "id cells";
            default -> null;
        };

        if (title_list == null) return new ServiceMenuResult("Unknown table: " + table, null, null);

        List<String> list = new ArrayList<>();
        for (String line : result.split("\n")) {
            if (!line.isBlank()) list.add(line);
        }

        return new ServiceMenuResult(result, title_list, list);
    }

    // SELECT

    @Override
    public String select(String table) throws DatabaseOperationException {
        if (table == null || table.isBlank()) return "table is empty";

        return switch (table) {
            case "vending_machine" -> selectVendingMachines();
            case "cells" -> selectCells();
            case "items" -> selectItems();
            default -> "Unknown table: " + table;
        };
    }

    private String selectVendingMachines() throws DatabaseOperationException {
        List<BaseEntity> list = vendingMachineRepository.get_all();
        if (list.isEmpty()) return "Empty vending_machines";

        StringBuilder str = new StringBuilder();
        list.forEach(e -> str.append(e).append("\n")); // lambda

        return str.toString();
    }

    private String selectItems() throws DatabaseOperationException {
        List<BaseEntity> list = itemRepository.get_all();
        if (list.isEmpty()) return "Empty items";

        StringBuilder str = new StringBuilder();
        list.forEach(e -> str.append(e).append("\n")); // lambda

        return str.toString();
    }

    private String selectCells() throws DatabaseOperationException {
        List<BaseJunctionEntity> list = cellRepository.get_all();
        if (list.isEmpty()) return "Empty cells";

        StringBuilder str = new StringBuilder();
        list.forEach(e -> str.append(e).append("\n")); // lambda

        return str.toString();
    }


    // BUY

    @Override
    public String buy(int vendingMachineId, int cellId, int quantity) throws DatabaseOperationException {
        if (quantity <= 0) return "quantity must be > 0";

        BaseJunctionEntity entity = cellRepository.get_by_id(cellId);
        if (entity == null) return "cell not found";

        Cell cell = (Cell) entity;

        if (cell.get_vending_machine_id() != vendingMachineId) {
            return "cell not belongs to vending_machine";
        }

        if (cell.get_quantity() < quantity) {
            return "not enough items (cell quantity: " + cell.get_quantity() + ")";
        }

        cell.set_quantity(cell.get_quantity() - quantity);
        cell.validate();

        cellRepository.update_by_id(cellId, cell);

        return "success buy. remaining: " + cell.get_quantity();
    }

    // INSERT

    @Override
    public String insertVendingMachine(String name, String place, int maxCells, int maxQuantity)
            throws DatabaseOperationException {

        VendingMachine vm = new VendingMachine(0, name, place, maxCells, maxQuantity);
        vm.validate();

        vendingMachineRepository.create(vm);
        return "inserted vending_machine";
    }

    @Override
    public String insertCell(int vendingMachineId, int itemId, int quantity) throws DatabaseOperationException {
        Cell cell = new Cell(0, vendingMachineId, itemId, quantity);
        cell.validate();

        cellRepository.create(cell);
        return "inserted cell";
    }

    @Override
    public String insertItem(String name, int price) throws DatabaseOperationException {
        Item item = new Item(0, name, price);
        item.validate();

        itemRepository.create(item);
        return "inserted item";
    }

    // UPDATE

    @Override
    public String updateVendingMachine(int id, String name, String place, int maxCells, int maxQuantity)
            throws DatabaseOperationException {

        VendingMachine vm = new VendingMachine(id, name, place, maxCells, maxQuantity);
        vm.validate();

        vendingMachineRepository.update_by_id(id, vm);
        return "updated vending_machine";
    }

    @Override
    public String updateCell(int id, int vendingMachineId, int itemId, int quantity)
            throws DatabaseOperationException {

        Cell cell = new Cell(id, vendingMachineId, itemId, quantity);
        cell.validate();

        cellRepository.update_by_id(id, cell);
        return "updated cell";
    }

    @Override
    public String updateItem(int id, String name, int price) throws DatabaseOperationException {
        Item item = new Item(id, name, price);
        item.validate();

        itemRepository.update_by_id(id, item);
        return "updated item";
    }

    // DELETE

    @Override
    public String deleteVendingMachine(int id) throws DatabaseOperationException {
        vendingMachineRepository.delete_by_id(id);
        return "deleted vending_machine";
    }

    @Override
    public String deleteCell(int id) throws DatabaseOperationException {
        cellRepository.delete_by_id(id);
        return "deleted cell";
    }

    @Override
    public String deleteItem(int id) throws DatabaseOperationException {
        itemRepository.delete_by_id(id);
        return "deleted item";
    }
}
