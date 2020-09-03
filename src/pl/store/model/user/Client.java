package pl.store.model.user;

import pl.store.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {

    public static final String USER_TYPE = "CLIENT";
    private Address address;
    private int age;
    private List<Product> boughtProducts = new ArrayList<>();

    public Client(int id, String username, String password, String firstName, String lastName, Address address, int age) {
        super(id, username, password, firstName, lastName);
        this.address = address;
        this.age = age;
    }

    public List<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + age + " " + "years old. Shipping address: " + address.toString();
    }

    @Override
    public String toCsv() {
        return USER_TYPE + " ; " + getId() + " ; " + getUsername() + " ; " + getPassword()  + " ; " + getFirstName() +
                " ; " + getLastName()  + " ; " + address.toCsv()  + " ; " + age;
    }


}
