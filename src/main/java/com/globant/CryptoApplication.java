package com.globant;

import com.globant.controller.MenuUser.MenuUserController;
import com.globant.service.user.UserAuthenticationService;
import com.globant.view.MenuCryptoView;
import com.globant.view.MenuUserView;

public class CryptoApplication {
    public static void main(String[] args) {
        MenuUserView view = new MenuUserView();
        MenuCryptoView viewCrypto = new MenuCryptoView();
        UserAuthenticationService userAuthenticationService = new UserAuthenticationService();
        MenuUserController controller  = new MenuUserController(view, userAuthenticationService);
       controller.run();
       view.close();
       viewCrypto.close();

    }
}
