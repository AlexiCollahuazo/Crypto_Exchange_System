package com.globant.model.user;

public interface UserAuthenticationInterface {

    void registerUser(User user);
    User loginUser(String email, String password);
    boolean VerifyEmail(String email);

}
