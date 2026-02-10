package org.models;

import org.exceptions.*;
import org.exceptions.InvalidInputException;
import org.models.abstract_classes.BaseEntity;

public class Item extends BaseEntity {
    private double price;

    public Item(int id, String name, double price){
        super(id,name);
        this.price = price;
    }

    @Override
    public String get_entity_type(){
        return "ITEM";
    }

    public double get_price(){
        return this.price;}

    public void set_price(double price){
        this.price = price;}

    @Override
    public void validate() throws InvalidInputException {
        if (this.price < 0) {
            throw new InvalidInputException("Price < 0");
        }
    }

    @Override
    public String toString(){
        return this.id + " : " + this.name + " " + this.price;
    }
}
