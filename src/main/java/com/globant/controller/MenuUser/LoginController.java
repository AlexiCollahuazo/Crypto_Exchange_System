package com.globant.controller.MenuUser;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.controller.MenuCrypto.MenuCryptoController;
import com.globant.model.user.User;
import com.globant.service.user.UserAuthenticationService;
import com.globant.service.user.UnknownUserException;
import com.globant.service.user.UserSingleton;
import com.globant.view.ConsoleView;

public class LoginController implements ControllerExecuteInterface {
    private final ConsoleView view;
    private final UserAuthenticationService userauthn;
    private final MenuCryptoController cryptocontroller;

    public LoginController(ConsoleView view, UserAuthenticationService userauthn) {
        this.view = view;
        this.userauthn = userauthn;
        this.cryptocontroller = new MenuCryptoController(view);
    }

    public void execute() {
        try {
            String[] details = view.getLoginView();
            User user = userauthn.loginUser(details[0], details[1]);
            if (user != null) {
               UserSingleton.getInstance().setCurrentUser(user);
                view.showSuccessMessage("Login successful");
                cryptocontroller.run();
            } else {
                view.showError("Incorrect email or password, please try again");
            }
        } catch (UnknownUserException e) {
            view.showError("Login Error");

        }
    }


}
