package com.globant.controller;

import com.globant.model.user.User;
import com.globant.service.UnknownUserException;
import com.globant.view.ConsoleView;

public class LogoutController implements ControllerExecuteInterface {
    private ConsoleView view;
    private User currentUser;

    public LogoutController(ConsoleView view) {
        this.view = view;
    }

    public void execute() {
        try {
            if (currentUser != null) {
                currentUser = null;
                view.showLogoutMessage("see you soon :,(");
                view.MenuUserChoice();
            } else {
                view.showError("Something went wrong");
            }
        } catch (UnknownUserException e) {
            view.showError("WRONG LOGIN");

        }


    }
}
