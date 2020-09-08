package pl.store.model;

import pl.store.exceptions.UserWithSuchAnUsernameAlreadyExistsException;
import pl.store.model.product.Product;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopProducts implements Serializable {

    public int productId = 0;

    private Map<Integer, Product> shopProducts = new HashMap<>();

    public Map<Integer, Product> getShopProducts() {
        return shopProducts;
    }

    public void setShopProducts(Map<Integer, Product> shopProducts) {
        this.shopProducts = shopProducts;
    }

    public void addProduct (Product product){
        product.setId(productId);
        if(shopProducts.containsKey(product.getId()))
            throw new UserWithSuchAnUsernameAlreadyExistsException("There is a user with such an username in the database!");
        else
            shopProducts.put(product.getId(), product);
        productId++;
    }

    public boolean deleteOneProduct(int number){
        boolean isDeleted = false;
        if(shopProducts.containsKey(number)){
            shopProducts.remove(number);
            isDeleted = true;
        }
        return isDeleted;
    }

    public boolean deleteProducts(List<Product> products){
        boolean isDeleted = false;
        for (Product product : products) {
            shopProducts.remove(product.getId());
            isDeleted = true;
        }
        return isDeleted;
    }

}
