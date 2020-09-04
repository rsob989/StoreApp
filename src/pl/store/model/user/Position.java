package pl.store.model.user;

import pl.store.exceptions.NoSuchAnOptionException;

public enum Position {
    SALES_ASSISTANT(0, "Sales assistant"), STORE_MANAGER(1, "Store manager"),
    ACCOUNTANT(2, "Accountant");

    private int id;
    private String description;

    Position(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return id + " - " + description;
    }
}
