package pl.store.model.product;

public class ComputerGame extends Product{

    private final static double TAX = 0.23;
    public static final String TYPE = "COMPUTER GAMES";
    private int minimumAge;
    private String platform;

    public ComputerGame(String name, double priceWithoutTax, int minimumAge, String platform) {
        super(name, priceWithoutTax);
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
        return super.toString() + " ,price with tax: " + String.format("%.2f",priceWithTax()) + " euro, minimal age " + minimumAge
                + ", platform: " + platform;
    }

    @Override
    public String toCsv() {
        return TYPE + " ; " + getName() + " ; " + getPriceWithoutTax() + " ; " +
                minimumAge + " ; " + platform;
    }
}
