package pl.store.model.user;

import pl.store.model.ToCsv;
import pl.store.model.product.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class User implements ToCsv, Serializable {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private List<Product> boughtProducts = new ArrayList<>();
    private List<Product> cart = new ArrayList<>();

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    public List<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return id + " - " + username + " - " + firstName + " " + lastName;
    }
}
