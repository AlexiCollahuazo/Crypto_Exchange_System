package com.globant;

import com.globant.controller.MenuUserController;
import com.globant.service.UserAuthenticationService;
import com.globant.view.ConsoleView;

public class CryptoApplication {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        UserAuthenticationService userAuthenticationService = new UserAuthenticationService();
        MenuUserController controller  = new MenuUserController(view, userAuthenticationService);
       controller.run();

    }
}
