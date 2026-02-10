package org.models;

import org.exceptions.InvalidInputException;
import org.models.abstract_classes.BaseEntity;


public class VendingMachine extends BaseEntity {
    private String place;
    private int max_cells;
    private int max_quantity;


    public VendingMachine(int id, String name, String place, int max_cells, int max_quantity) {
        super(id, name);
        this.place = place;
        this.max_cells = max_cells;
        this.max_quantity = max_quantity;
    }

    public String get_place() {
        return place;}
    public int get_max_cells() {
        return max_cells;}
    public int get_max_quantity() {
        return max_quantity;}

    public void set_place(String place) {
        this.place = place;}
    public void set_max_cells(int max_cells) {
        this.max_cells = max_cells;}
    public void set_max_quantity(int max_quantity) {
        this.max_quantity = max_quantity;}

    @Override
    public String get_entity_type() {
        return "VENDING_MACHINE";
    }

    @Override
    public void validate() throws InvalidInputException {
        if (name == null || name.isEmpty()) {
            throw new InvalidInputException("Machine name is empty");
        }
        if (this.max_quantity <= 0){
            throw new InvalidInputException("Max quantity must be > 0");
        }
        if (this.max_cells <= 0){
            throw new InvalidInputException("Max cells must be > 0");
        }
        if (place == null || place.trim().isEmpty()) {
            throw new InvalidInputException("Place is empty");
        }
    }
}