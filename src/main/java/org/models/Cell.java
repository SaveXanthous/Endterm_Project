package org.models;

import org.exceptions.InvalidInputException;
import org.models.abstract_classes.BaseJunctionEntity;

public class Cell extends BaseJunctionEntity {

    private int vending_machine_id;
    private int item_id;
    private int quantity;

    private Cell(Builder builder) {
        super(builder.id);
        this.vending_machine_id = builder.vending_machine_id;
        this.item_id = builder.item_id;
        this.quantity = builder.quantity;
    }

    public Cell(int id, int vending_machine_id, int item_id, int quantity) {
        super(id);
        this.vending_machine_id = vending_machine_id;
        this.item_id = item_id;
        this.quantity = quantity;
    }

    public int get_vending_machine_id() {
        return vending_machine_id;
    }public int get_item_id() {
        return item_id;
    }public int get_quantity() {
        return quantity;
    }

    public void set_vending_machine_id(int vending_machine_id) {
        this.vending_machine_id = vending_machine_id;
    }public void set_item_id(int item_id) {
        this.item_id = item_id;
    }public void set_quantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String get_entity_type() {
        return "CELL";
    }

    @Override
    public void validate() throws InvalidInputException {
        if (this.quantity < 0) {
            throw new InvalidInputException("quantity must be >= 0");
        }
    }

    @Override
    public String toString() {
        return this.id + " : " +
                this.vending_machine_id + " " +
                this.item_id + " " +
                this.quantity;
    }


    public static class Builder {

        private int id;
        private int vending_machine_id;
        private int item_id;
        private int quantity;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }public Builder setVendingMachineId(int vending_machine_id) {
            this.vending_machine_id = vending_machine_id;
            return this;
        }public Builder setItemId(int item_id) {
            this.item_id = item_id;
            return this;
        }public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }public Cell build() throws InvalidInputException {
            Cell cell = new Cell(this);
            cell.validate();
            return cell;
        }
    }
}
