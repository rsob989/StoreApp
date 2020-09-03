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

    public static Position optionFromInt(int choice){
        Position position = null;
        try{
            position = Position.values()[choice];
        } catch (IndexOutOfBoundsException e){
            throw new NoSuchAnOptionException("There is no such an option: " + choice);
        }
        return position;
    }

    @Override
    public String toString() {
        return id + " - " + description;
    }
}
