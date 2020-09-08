package pl.store.model.user;

import pl.store.model.ToCsv;
import java.util.Collection;
import java.util.stream.Collectors;

public class Client extends User {

    public static final String TYPE = "CLIENT";
    public static final int MINIMUM_PRODUCTS_BOUGHT_TO_BE_PREMIUM = 3;
    private Address address;
    private int age;

    public Client(String username, String password, String firstName, String lastName, Address address, int age) {
        super(username, password, firstName, lastName);
        this.address = address;
        this.age = age;
    }

    public static String getTYPE() {
        return TYPE;
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

    private <E extends ToCsv> String listToCsv(Collection<E> collection){
        return collection.stream()
                .map(x->x.toCsv())
                .collect(Collectors.joining(" : "));
    }

    @Override
    public String toCsv() {
        return TYPE + " ; " + getUsername() + " ; " + getPassword()  + " ; " + getFirstName() +
                " ; " + getLastName()  + " ; " + address.toCsv()  + " ; " + age + " : " + listToCsv(getBoughtProducts());
    }


}
