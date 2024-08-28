package com.globant.service;

import com.globant.model.user.User;
import com.globant.model.user.UserAuthenticationInterface;

import java.util.HashMap;
import java.util.Map;

public class UserAuthenticationService implements UserAuthenticationInterface {
    private Map<String, User> users;

    public UserAuthenticationService() {
        users = new HashMap<>();
    }

    @Override
    public void registerUser(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public User loginUser(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
@Override
    public boolean VerifyEmail(String email) {
        return users.containsKey(email);
    }
}
