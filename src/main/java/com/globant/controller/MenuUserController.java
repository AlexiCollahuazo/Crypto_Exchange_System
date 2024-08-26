package com.globant.controller;

import com.globant.model.user.User;
import com.globant.service.UserAuthentication;
import com.globant.view.ConsoleView;

import java.util.HashMap;
import java.util.Map;

public class MenuUserController {

    private final ConsoleView view;
    private UserAuthentication userauthn;
   private final RegisterController registerController;
   private final LoginController loginController;
   private boolean active;
//    private Map<Integer, User> users;


    public MenuUserController(ConsoleView view, UserAuthentication userauthn) {
        this.view = view;
       this.registerController = new RegisterController(view, userauthn);
       this.loginController = new LoginController(view, userauthn);
       this.active = true;
      // this.users = new HashMap<>();

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
