package pl.store.app.menus;

public enum AddMenu {
    COMPUTER_GAMES(0, "Add computer games"), BOOKS(1, "Add books"), BACK(2, "Back to main menu");
    private int id;
    private String description;

    AddMenu(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return id + " - " + description;
    }
}
