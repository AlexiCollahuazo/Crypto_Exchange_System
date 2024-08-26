package com.globant;

import com.globant.controller.CryptoUserController;
import com.globant.controller.MenuUserController;
import com.globant.service.UserAuthentication;
import com.globant.view.ConsoleView;

public class CryptoApplication {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        UserAuthentication userAuthentication = new UserAuthentication();
        MenuUserController controller  = new MenuUserController(view, userAuthentication);
       controller.run();

    }
}
