package pl.store.app.menus;

public enum EmployeeMenu {
    ADD(0, "Add new products to store"), BROWSE(1, "Browse products in the store"),
    DELETE(2, "Delete products from the store"), CLOSE(3, "Exit");

    private int id;
    private String description;

    EmployeeMenu(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return id + " - " + description;
    }
}
