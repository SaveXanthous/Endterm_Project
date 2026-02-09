package org.example.models.interfaces;

public interface Name {
    String get_name();
    void set_name(String name);

    default boolean hasName() {
        return get_name() != null;
    }
}
