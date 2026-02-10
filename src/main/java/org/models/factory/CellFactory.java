package org.models.factory;

import org.models.Cell;
import org.models.abstract_classes.BaseJunctionEntity;

public class CellFactory {

    public static BaseJunctionEntity createCell(int id, int vendingMachineId, int itemId, int quantity) {
        return new Cell(id, vendingMachineId, itemId, quantity);
    }
}
