package com.globant.controller.MenuUser;

import com.globant.model.user.User;

public interface UserAuthenticationInterface {

    void registerUser(User user);
    User loginUser(String email, String password);
    boolean VerifyEmail(String email);

}
