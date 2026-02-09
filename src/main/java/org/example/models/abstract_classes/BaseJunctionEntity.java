package org.example.models.abstract_classes;

import org.example.models.interfaces.Id;

public abstract  class BaseJunctionEntity implements Id{
    protected int id;

    public BaseJunctionEntity(int id) {
        this.id = id;
    }

    public abstract String get_entity_type();
    public abstract void validate();

    public void set_id(int id){
        this.id = id;}

    public int get_id(){
        return this.id;}

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
