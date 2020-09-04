package pl.store.app.menus;

public enum DataTypeMenu {
    CSV(0, "CSV file"), SERIALIZABLE(1, "Serializable file");

    private int id;
    private String description;

    DataTypeMenu(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return id + " - " + description;
    }
}
