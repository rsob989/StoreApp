package pl.store.app.menus;

public enum ClientMenu {
    ADD_TO_THE_CART(0, "Add new product to the cart"), BUY(1, "Buy products"),
    BROWSE(2, "Browse products"), CLOSE(3, "Exit");

    private int id;
    private String description;

    ClientMenu(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return id + " - " + description;
    }
}
