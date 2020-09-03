package pl.store.data.format;

import pl.store.data.FileManager;
import pl.store.exceptions.ReadDataException;
import pl.store.exceptions.WriteDataException;
import pl.store.model.ShopProducts;
import pl.store.model.ShopUsers;
import pl.store.model.ToCsv;
import pl.store.model.product.Books;
import pl.store.model.product.ComputerGames;
import pl.store.model.product.Product;
import pl.store.model.user.*;

import java.io.*;
import java.util.Collection;

public class CsvFileManager implements FileManager {

    private final static String USERS_FILE_NAME = "users.csv";
    private final static String PRODUCTS_FILE_NAME = "products.csv";

    @Override
    public void save(ShopUsers shopUsers, ShopProducts shopProducts) {
        saveSingleFile(USERS_FILE_NAME, shopUsers.getShopUsers().values());
        saveSingleFile(PRODUCTS_FILE_NAME, shopProducts.getShopProducts().values());
    }

    @Override
    public ShopUsers loadUsers() {
        ShopUsers shopUsers = new ShopUsers();
        try (
                FileReader fr = new FileReader(new File(USERS_FILE_NAME));
                BufferedReader br = new BufferedReader(fr);
        ) {
            br.lines()
                    .map(x -> x.split(" ; "))
                    .map(this::loadUserFromCsv)
                    .forEach(shopUsers::addUser);
        } catch (IOException e){
            throw new ReadDataException("File cannot be load: " + PRODUCTS_FILE_NAME);
        }
        return shopUsers;
    }

    @Override
    public ShopProducts loadProducts() {
        ShopProducts shopProducts = new ShopProducts();
        try (
                FileReader fr = new FileReader(new File(PRODUCTS_FILE_NAME));
                BufferedReader br = new BufferedReader(fr);
        ) {
            br.lines()
                    .map(x -> x.split(" ; "))
                    .map(this::loadProductFromCsv)
                    .forEach(shopProducts::addProduct);
        } catch (IOException e){
            throw new ReadDataException("File cannot be load: " + PRODUCTS_FILE_NAME);
        }
        return shopProducts;
    }

    private Product loadProductFromCsv(String[] data){
        Product product = null;
        if(data[0].equals(ComputerGames.PRODUCT_TYPE))
            product = loadComputerGameFromCsv(data);
        else if(data[0].equals(Books.PRODUCT_TYPE))
            product = loadBookFromCsv(data);
        return product;
    }

    private ComputerGames loadComputerGameFromCsv(String[] data){
        int id = Integer.valueOf(data[1]);
        String name = data[2];
        double priceWithoutTax = Double.valueOf(data[3]);
        int minimumAge = Integer.valueOf(data[4]);
        String platform = data[5];
        return new ComputerGames(id, name, priceWithoutTax, minimumAge, platform);
    }

    private Books loadBookFromCsv(String[] data){
        int id = Integer.valueOf(data[1]);
        String name = data[2];
        double priceWithoutTax = Double.valueOf(data[3]);
        int pages = Integer.valueOf(data[4]);
        String type = data[5];
        return new Books(id, name, priceWithoutTax, pages, type);
    }

    private User loadUserFromCsv(String[] data) {
        User user = null;
        if(data[0].equals(Client.USER_TYPE))
            user= loadClientFromCsv(data);
        else if(data[0].equals(Employee.USER_TYPE))
            user= loadEmployeeFromCsv(data);
        return user;
    }

    private Employee loadEmployeeFromCsv(String[] data){
        int id = Integer.valueOf(data[1]);
        String username = data[2];
        String password = data[3];
        String firstName = data[4];
        String lastName = data[5];
        int salary = Integer.valueOf(data[6]);
        String pos = data[7];
        Position position = Position.valueOf(pos);
        return new Employee(id, username, password, firstName, lastName, salary, position);
    }

    private Client loadClientFromCsv(String[] data){
        int id = Integer.valueOf(data[1]);
        String username = data[2];
        String password = data[3];
        String firstName = data[4];
        String lastName = data[5];
        String country = data[6];
        String postalCode = data[7];
        String streetName = data[8];
        int houseNumber = Integer.valueOf(data[9]);
        int flatNumber = Integer.valueOf(data[10]);
        int age = Integer.valueOf(data[11]);
        Address address = new Address(country, postalCode, streetName, houseNumber, flatNumber);
        return new Client(id, username, password, firstName, lastName, address, age);
    }

    public <T extends ToCsv> void saveSingleFile(String fileName, Collection<T> collection) {
        try (
                FileWriter fr = new FileWriter(new File(fileName));
                BufferedWriter br = new BufferedWriter(fr);
        ) {
            for (T user : collection) {
                br.write(user.toCsv());
            }
        } catch (IOException e) {
            throw new WriteDataException("File cannot be save: " + fileName);
        }
    }
}
