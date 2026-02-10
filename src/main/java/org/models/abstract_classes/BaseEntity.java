package org.models.abstract_classes;

import org.models.interfaces.*;
import org.models.interfaces.Id;
import org.models.interfaces.Name;

public abstract class BaseEntity implements Id, Name {
    protected int id;
    protected String name;

    public BaseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract String get_entity_type();
    public abstract void validate();

    public void set_id(int id){
        this.id = id;}
    public void set_name(String name){
        this.name = name;}

    public int get_id(){
        return this.id;}
    public String get_name(){
        return name;}

    @Override
    public String toString(){
        return this.id + " : " + this.name;
    }
}
