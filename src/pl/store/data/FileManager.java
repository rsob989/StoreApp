package pl.store.data;

import pl.store.model.ShopProducts;
import pl.store.model.ShopUsers;

import java.io.File;

public interface FileManager {

    void save(ShopUsers shopUsers, ShopProducts shopProducts);

    ShopUsers loadUsers();

    ShopProducts loadProducts();

}
