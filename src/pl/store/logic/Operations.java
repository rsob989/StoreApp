package pl.store.logic;

import pl.store.exceptions.*;
import pl.store.model.ShopProducts;
import pl.store.model.ShopUsers;
import pl.store.model.product.Book;
import pl.store.model.product.ComputerGame;
import pl.store.model.product.Product;
import pl.store.model.user.Client;
import pl.store.model.user.Employee;
import pl.store.model.user.User;

public class Operations {

    public final static int WRONG_LOGIN_TRIAL = 3;


    ShopUsers shopUsers;
    ShopProducts shopProducts;
    ContactWithUser cwu;

    public Operations(ShopUsers shopUsers, ShopProducts shopProducts, ContactWithUser cwu) {
        this.shopUsers = shopUsers;
        this.shopProducts = shopProducts;
        this.cwu = cwu;
    }

    public User login() {
        boolean isOk = false;
        User user = null;
        int counter = 0;
        do {
            if (counter >= WRONG_LOGIN_TRIAL) {
                throw new MaximumNumberOfLoginTrialException("You achieved maximal number of login attempt");
            } else {
                try {
                    cwu.printer("Username: ");
                    String username = cwu.getString();
                    cwu.printer("Password: ");
                    String password = cwu.getString();
                    counter++;
                    user = shopUsers.loggedUser(username, password);
                    isOk = true;
                } catch (NoSuchAnUserException e) {
                    cwu.printer(e.getMessage());
                    cwu.printer("Remaining number of login attempts: " + (WRONG_LOGIN_TRIAL-counter));
                }
            }
        } while (!isOk);
        return user;
    }

    public void createEmployee() {
        Employee employee = cwu.createNewEmployee();
        try {
            shopUsers.addUser(employee);
        } catch (UserWithSuchAnUsernameAlreadyExistsException e) {
            cwu.printer(e.getMessage());
        }
    }

    public void createClient() {
        Client client = cwu.createNewClient();
        try {
            shopUsers.addUser(client);
        } catch (UserWithSuchAnUsernameAlreadyExistsException e) {
            cwu.printer(e.getMessage());
        }
    }

    public void createComputerGames(){
        ComputerGame computerGame = cwu.createComputerGames();
        try {
            shopProducts.addProduct(computerGame);
        } catch (ProductWithSuchAnIdNumberAlreadyExistsException e){
            cwu.printer(e.getMessage());
        }
    }

    public void createBooks(){
        Book book = cwu.createBooks();
        try {
            shopProducts.addProduct(book);
        } catch (ProductWithSuchAnIdNumberAlreadyExistsException e){
            cwu.printer(e.getMessage());
        }
    }

    public void showProducts(){
        if(shopProducts.getShopProducts().isEmpty())
            cwu.printer("There is no products in the store!");
        else{
            for(Product p: shopProducts.getShopProducts().values()){
                cwu.printer(p.toString());
            }
        }
    }

    public void deleteProduct(){
        cwu.printer("Insert an ID number of a product which you want to delete: ");
        int number = cwu.getInt();
        if(shopProducts.deleteProduct(number)){
            cwu.printer("Product successfully removed from the store");
        } else
            cwu.printer("Product cannot be removed from the store. Sorry!");
    }

    public void buyProduct(Client client){
        Product productToBuy = null;
        showProducts();
        try{
            productToBuy = findProduct();
        } catch (NoSuchAProductException e){
            cwu.printer(e.getMessage());
        }
        shopProducts.deleteProduct(productToBuy.getId());
        client.getBoughtProducts().add(productToBuy);
        cwu.printer("You bought: " + productToBuy.toString());
    }

    private Product findProduct() {
        cwu.printer("Insert an ID number of a product which you want to buy: ");
        int number = cwu.getInt();
        Product foundProduct = null;
        if (shopProducts.getShopProducts().containsKey(number)) {
            foundProduct = shopProducts.getShopProducts().get(number);
        } else
            throw new NoSuchAProductException("There is no product with such an ID in store");
        return foundProduct;
    }
}
