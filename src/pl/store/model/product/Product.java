package pl.store.model.product;

import pl.store.model.ToCsv;

import java.io.Serializable;

public abstract class Product implements ToCsv, Serializable {

    private int id;
    private String name;
    private double priceWithoutTax;
    private double priceWithTax;

    public Product(String name, double priceWithoutTax) {
        this.name = name;
        this.priceWithoutTax = priceWithoutTax;
    }

    public double getPriceWithTax() {
        return priceWithTax;
    }

    public void setPriceWithTax(double priceWithTax) {
        this.priceWithTax = priceWithTax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract double priceWithTax();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceWithoutTax() {
        return priceWithoutTax;
    }

    public void setPriceWithoutTax(double priceWithoutTax) {
        this.priceWithoutTax = priceWithoutTax;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
