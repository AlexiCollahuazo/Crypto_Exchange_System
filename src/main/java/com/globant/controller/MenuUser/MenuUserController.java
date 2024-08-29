package com.globant.controller.MenuUser;

import com.globant.service.user.UserAuthenticationService;
import com.globant.view.ConsoleView;

public class MenuUserController {

    private final ConsoleView view;
   private final RegisterController registerController;
   private final LoginController loginController;
   private boolean active;

    public MenuUserController(ConsoleView view, UserAuthenticationService userauthn) {
        this.view = view;
       this.registerController = new RegisterController(view, userauthn);
       this.loginController = new LoginController(view, userauthn);
       this.active = true;
    }

    public void run() {
        while (active) {
            int choice = view.MenuUserChoice();
            switch (choice) {
                case 1:
                    registerController.execute();
                    break;
                case 2:
                   loginController.execute();
                    break;
                case 3:
                    System.exit(0);
                    active = false;
                    break;
                default:
                    view.showError("Wrong Option");
            }
        }
    }






}
