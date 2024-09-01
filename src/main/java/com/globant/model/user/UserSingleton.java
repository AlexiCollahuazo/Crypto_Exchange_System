package com.globant.model.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserSingleton {
    private static UserSingleton instance;
    private User currentUser;
   private final Map<Integer, User> users = new HashMap<>();
    private UserWallet currentWallet;

    public UserSingleton() {

    }
// Syncronized se puso para cuando se comparten los datos.
    public static synchronized UserSingleton getInstance() {
        if (instance == null) {
            instance = new UserSingleton();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }


    public void addUser(User user) {
        users.put(user.getID(), user);
    }

    public User getUserById(int userId) {
        return users.get(userId);
    }


    public UserWallet getCurrentWallet() {
        return currentWallet;
    }


    public void setCurrentUser(User Userprime) {
        this.currentUser = Userprime;
        if (Userprime != null) {
            this.currentWallet = Userprime.getWallet();
        } else {
            this.currentWallet = null;
        }
    }


}
