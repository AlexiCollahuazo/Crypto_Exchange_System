package com.globant.controller.MenuUser;

import com.globant.model.user.User;
import com.globant.model.user.UserSingleton;

import java.util.HashMap;
import java.util.Map;

public class UserAuthenticationService implements UserAuthenticationInterface {
    private final Map<String, User> users;

    public UserAuthenticationService() {
        users = new HashMap<>();
    }

    // Add the user to UserSingleton
    @Override
    public void registerUser(User user) {
        users.put(user.getEmail(), user);
        UserSingleton.getInstance().addUser(user);
    }

   // Checks the username and password, if they do not exist then null is returned
    @Override
    public User loginUser(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            UserSingleton.getInstance().setCurrentUser(user);
            return user;
        }
        return null;
    }

    // Validates the user's email provided with saved user emails
@Override
    public boolean VerifyEmail(String email) {
        return users.containsKey(email);
    }

 //   Method to avoid white spaces
    public boolean CheckEmpty(String[] details) {
        for (String detail : details) {
            if (detail.isEmpty()) {
                return true;
            }
        }
        return  false;
    }

}

