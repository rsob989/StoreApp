package pl.store.model.product;

public class Book extends Product {
    private static final double TAX = 0.07;
    public static final String TYPE = "BOOKS";
    private int pages;
    private String type;

    public Book(String name, double priceWithoutTax, int pages, String type) {
        super(name, priceWithoutTax);
        this.pages = pages;
        this.type = type;
    }

    @Override
    public double priceWithTax() {
        return getPriceWithoutTax() + (getPriceWithoutTax()*TAX);
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + " ,price: " + String.format("%.2f",priceWithTax()) + " euro, number of pages " + pages +
                ", type of the book: " + type;
    }

    public String toCsv(){
        return TYPE + " ; " + getName() + " ; " + getPriceWithoutTax() + " ; "
                + pages + " ; " + type;
    }
}
