package pl.store.data.format;

import pl.store.data.FileManager;
import pl.store.exceptions.ReadDataException;
import pl.store.exceptions.WriteDataException;
import pl.store.model.ShopProducts;
import pl.store.model.ShopUsers;
import pl.store.model.ToCsv;
import pl.store.model.product.Book;
import pl.store.model.product.ComputerGame;
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

    public <T extends ToCsv> void saveSingleFile(String fileName, Collection<T> collection) {
        try (
                FileWriter fr = new FileWriter(new File(fileName));
                BufferedWriter br = new BufferedWriter(fr);
        ) {
            for (T col : collection) {
                br.write(col.toCsv());
                br.newLine();
            }
        } catch (IOException e) {
            throw new WriteDataException("File cannot be save: " + fileName);
        }
    }

    @Override
    public ShopUsers loadUsers() {
        ShopUsers shopUsers = new ShopUsers();
        try (
                FileReader fr = new FileReader(new File(USERS_FILE_NAME));
                BufferedReader br = new BufferedReader(fr);
        ) {
            br.lines()
                    .map(x -> x.split(" : "))
                    .map(this::loadSingleUser)
                    .forEach(shopUsers::addUser);
        } catch (IOException e) {
            throw new ReadDataException("File cannot be load: " + PRODUCTS_FILE_NAME);
        }
        return shopUsers;
    }

    private User loadSingleUser(String[] obj) {
        User user = null;
        String[] userData = obj[0].split(" ; ");
        if (userData[0].equals(Client.TYPE)) {
            user = loadProductsBoughtByUser(obj, userData);
        } else if (userData[0].equals(Employee.TYPE)) {
            user = loadEmployeeFromCsv(userData);
        }
        return user;
    }

    private User loadProductsBoughtByUser(String[] obj, String[] userData) {
        User user;
        user = loadClientFromCsv(userData);
        for(int i = 1; i < obj.length; i++){
            String[] productData = obj[i].split(" ; ");
            if(productData[0].equals(ComputerGame.TYPE)){
                user.getBoughtProducts().add(loadComputerGameFromCsv(productData));
            } else if(productData[0].equals(Book.TYPE)){
                user.getBoughtProducts().add(loadBookFromCsv(productData));
            }
        }
        return user;
    }

    private Employee loadEmployeeFromCsv(String[] data) {
        String username = data[1];
        String password = data[2];
        String firstName = data[3];
        String lastName = data[4];
        int salary = Integer.parseInt(data[5]);
        String pos = data[6];
        Position position = Position.valueOf(pos);
        return new Employee(username, password, firstName, lastName, salary, position);
    }

    private Client loadClientFromCsv(String[] data) {
        String username = data[1];
        String password = data[2];
        String firstName = data[3];
        String lastName = data[4];
        String country = data[5];
        String postalCode = data[6];
        String streetName = data[7];
        int houseNumber = Integer.parseInt(data[8]);
        int flatNumber = Integer.parseInt(data[9]);
        int age = Integer.parseInt(data[10]);
        Address address = new Address(country, postalCode, streetName, houseNumber, flatNumber);
        return new Client(username, password, firstName, lastName, address, age);
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
        } catch (IOException e) {
            throw new ReadDataException("File cannot be load: " + PRODUCTS_FILE_NAME);
        }
        return shopProducts;
    }

    private Product loadProductFromCsv(String[] data) {
        Product product = null;
        if (data[0].equals(ComputerGame.TYPE))
            product = loadComputerGameFromCsv(data);
        else if (data[0].equals(Book.TYPE))
            product = loadBookFromCsv(data);
        return product;
    }

    private ComputerGame loadComputerGameFromCsv(String[] data) {
        String name = data[1];
        double priceWithoutTax = Double.parseDouble(data[2]);
        int minimumAge = Integer.parseInt(data[3]);
        String platform = data[4];
        return new ComputerGame(name, priceWithoutTax, minimumAge, platform);
    }

    private Book loadBookFromCsv(String[] data) {
        String name = data[1];
        double priceWithoutTax = Double.parseDouble(data[2]);
        int pages = Integer.parseInt(data[3]);
        String type = data[4];
        return new Book(name, priceWithoutTax, pages, type);
    }


}
