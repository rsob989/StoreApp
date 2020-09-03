package pl.store.data.format;

import pl.store.data.FileManager;
import pl.store.exceptions.ReadDataException;
import pl.store.exceptions.WriteDataException;
import pl.store.model.ShopProducts;
import pl.store.model.ShopUsers;

import java.io.*;

public class SerializableFileManager implements FileManager {

    public final static String USERS_FILE_NAME = "users.o";
    public final static String PRODUCTS_FILE_NAME = "products.o";

    @Override
    public ShopUsers loadUsers() {
        ShopUsers shopUsers = new ShopUsers();
        try (
                FileInputStream fis = new FileInputStream(new File(USERS_FILE_NAME));
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            shopUsers = (ShopUsers)ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            throw new ReadDataException("File cannot be load: " + USERS_FILE_NAME);
        }
        return shopUsers;
    }

    @Override
    public ShopProducts loadProducts() {
        ShopProducts shopProducts = new ShopProducts();
        try (
                FileInputStream fis = new FileInputStream(new File(PRODUCTS_FILE_NAME));
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            shopProducts = (ShopProducts) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            throw new ReadDataException("File cannot be load: " + PRODUCTS_FILE_NAME);
        }
        return shopProducts;
    }

    @Override
    public void save(ShopUsers shopUsers, ShopProducts shopProducts) {
        saveSingleFile(USERS_FILE_NAME, shopUsers);
        saveSingleFile(PRODUCTS_FILE_NAME, shopProducts);
    }

    public void saveSingleFile(String fileName, Object object){
        try (
                FileOutputStream fos = new FileOutputStream(new File(fileName));
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(object);
        } catch (IOException e){
            throw new WriteDataException("File cannot be save: " + fileName);
        }
    }
}
