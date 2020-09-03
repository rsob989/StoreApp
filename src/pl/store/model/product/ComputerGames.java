package pl.store.model.product;

public class ComputerGames extends Product{

    private final static double TAX = 0.23;
    public static final String PRODUCT_TYPE = "COMPUTER GAMES";
    private int minimumAge;
    private String platform;

    public ComputerGames(int id, String name, double priceWithoutTax, int minimumAge, String platform) {
        super(id, name, priceWithoutTax);
        this.minimumAge = minimumAge;
        this.platform = platform;
    }

    @Override
    public double priceWithTax() {
        return getPriceWithoutTax() + (getPriceWithoutTax()*TAX);
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return super.toString() + " ,price with tax: " + priceWithTax() + " euro, minimal age " + minimumAge
                + ", platform: " + platform;
    }

    @Override
    public String toCsv() {
        return PRODUCT_TYPE + " ; " + getId() + " ; " + getName() + " ; " + getPriceWithoutTax() + " ; " +
                minimumAge + " ; " + platform;
    }
}
