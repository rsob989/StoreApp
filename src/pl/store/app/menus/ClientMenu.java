package pl.store.app.menus;

public enum ClientMenu {
    BUY(0, "Buy products"), BROWSE(1, "Browse products"), CLOSE(2, "Exit");
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
