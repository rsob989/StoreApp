package pl.store.model;

import pl.store.exceptions.NoSuchAnUserException;
import pl.store.exceptions.UserWithSuchAnUsernameAlreadyExistsException;
import pl.store.model.user.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ShopUsers implements Serializable {

    public int userId = 0;

    private Map<String, User> shopUsers = new HashMap<>();

    public Map<String, User> getShopUsers() {
        return shopUsers;
    }

    public void setShopUsers(Map<String, User> shopUsers) {
        this.shopUsers = shopUsers;
    }

    public void addUser (User user){
        user.setId(userId);
        if(shopUsers.containsKey(user.getUsername()))
            throw new UserWithSuchAnUsernameAlreadyExistsException("There is a user with such an username in the database!");
        else
            shopUsers.put(user.getUsername(), user);
        userId++;
    }

    public User loggedUser(String username, String password){
        User user = null;
        try {
            if (shopUsers.get(username).getPassword().equals(password))
                user = shopUsers.get(username);
            else
                throw new NoSuchAnUserException("Password is wrong! Try again.");
        } catch (NullPointerException e){
            throw new NoSuchAnUserException("There isn't an user with a such username and password in the database!");
        }
        return user;
    }
}
