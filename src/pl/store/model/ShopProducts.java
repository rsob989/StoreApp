package pl.store.model;

import pl.store.exceptions.UserWithSuchAnUsernameAlreadyExistsException;
import pl.store.model.product.Product;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ShopProducts implements Serializable {

    private Map<Integer, Product> shopProducts = new HashMap<>();

    public Map<Integer, Product> getShopProducts() {
        return shopProducts;
    }

    public void setShopProducts(Map<Integer, Product> shopProducts) {
        this.shopProducts = shopProducts;
    }

    public void addProduct (Product product){
        if(shopProducts.containsKey(product.getId()))
            throw new UserWithSuchAnUsernameAlreadyExistsException("There is a user with such an username in the database!");
        else
            shopProducts.put(product.getId(), product);
    }

    public boolean deleteProduct (int idNumber){
        boolean isDeleted = false;
        if(shopProducts.containsKey(idNumber)){
            shopProducts.remove(idNumber);
            isDeleted = true;
        } else
            isDeleted = false;
        return isDeleted;
    }

}
