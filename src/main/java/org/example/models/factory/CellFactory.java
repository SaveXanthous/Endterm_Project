package org.example.models.factory;

import org.example.models.Cell;
import org.example.models.abstract_classes.BaseJunctionEntity;

public class CellFactory {

    public static BaseJunctionEntity createCell(int id, int vendingMachineId, int itemId, int quantity) {
        return new Cell(id, vendingMachineId, itemId, quantity);
    }
}
