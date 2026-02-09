package org.example.service;

import org.example.exceptions.DatabaseOperationException;

public interface VendingServiceInterface {

    ServiceMenuResult execute_menu(String cmd);

    String select(String table) throws DatabaseOperationException;

    String buy(int vendingMachineId, int cellId, int quantity) throws DatabaseOperationException;

    String insertVendingMachine(String name, String place, int maxCells, int maxQuantity) throws DatabaseOperationException;
    String insertCell(int vendingMachineId, int itemId, int quantity) throws DatabaseOperationException;
    String insertItem(String name, int price) throws DatabaseOperationException;

    String updateVendingMachine(int id, String name, String place, int maxCells, int maxQuantity) throws DatabaseOperationException;
    String updateCell(int id, int vendingMachineId, int itemId, int quantity) throws DatabaseOperationException;
    String updateItem(int id, String name, int price) throws DatabaseOperationException;

    String deleteVendingMachine(int id) throws DatabaseOperationException;
    String deleteCell(int id) throws DatabaseOperationException;
    String deleteItem(int id) throws DatabaseOperationException;
}
