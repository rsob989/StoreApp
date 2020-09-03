package pl.store.app.menus;

import pl.store.exceptions.NoSuchAnOptionException;

public enum StartMenu {
    CREATE_CLIENT(0, "Create new client"),
    CREATE_EMPLOYEE(1, "Create new employee"),
    LOGIN(2, "Client/Employee login"),
    CLOSE(3, "Exit");

    private int id;
    private String description;

    StartMenu(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return id + " - " + description;
    }
}
