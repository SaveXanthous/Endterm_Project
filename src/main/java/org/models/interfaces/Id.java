package org.models.interfaces;

public interface Id {
    int get_id();
    void set_id(int id);

    default boolean isValidId() {
        return get_id() > 0;
    }

    static boolean isValidId(int id) {
        return id > 0;
    }
}
