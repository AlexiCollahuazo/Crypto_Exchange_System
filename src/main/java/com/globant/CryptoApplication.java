package com.globant;

import com.globant.controller.MenuUser.MenuUserController;
import com.globant.service.user.UserAuthenticationService;
import com.globant.view.ConsoleView;

public class CryptoApplication {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        UserAuthenticationService userAuthenticationService = new UserAuthenticationService();
        MenuUserController controller  = new MenuUserController(view, userAuthenticationService);
       controller.run();

    }
}
