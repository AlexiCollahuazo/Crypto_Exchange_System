package com.globant.service.user;

import com.globant.model.user.User;
import com.globant.model.user.UserWallet;

public class UserSingleton {
    private static UserSingleton instance;
    private User currentUser;
    private UserWallet currentWallet;

    private UserSingleton() {

    }

    public static UserSingleton getInstance() {
        if (instance == null) {
            instance = new UserSingleton();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
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
