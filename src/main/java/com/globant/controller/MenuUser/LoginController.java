package com.globant.controller.MenuUser;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.controller.MenuCrypto.MenuCryptoController;
import com.globant.model.user.User;
import com.globant.service.user.UnknownUserException;
import com.globant.model.user.UserSingleton;
import com.globant.view.MenuUserView;
import com.globant.view.MenuCryptoView;

public class LoginController implements ControllerExecuteInterface {
    private final MenuUserView view;
    private final UserAuthenticationService userauthn;
    private final MenuCryptoController cryptocontroller;
    private  MenuCryptoView viewCrypto = new MenuCryptoView();//

    public LoginController(MenuUserView view, UserAuthenticationService userauthn) {
        this.view = view;
        this.userauthn = userauthn;
        this.cryptocontroller = new MenuCryptoController(viewCrypto); //view
    }

    public void execute() {
        try {
           // receive the data
           String[] details = view.getLoginView();
           // authenticates that the user exists
            User user = userauthn.loginUser(details[0], details[1]);
            if (user != null) {
                view.showSuccessMessage("Login successful");
                cryptocontroller.run(); // activate the other menu
            } else {
                view.showError("Incorrect email or password, please try again");
            }

        } catch (UnknownUserException e) {
            view.showError("Login Error");

        }
    }


}
