package com.globant;

import com.globant.controller.MenuUser.MenuUserController;
import com.globant.controller.MenuUser.UserAuthenticationService;
import com.globant.service.cryptocurrencies.CryptoService;
import com.globant.service.cryptocurrencies.Cryptocurrencyfluctuation;
import com.globant.view.MenuCryptoView;
import com.globant.view.MenuUserView;

public class CryptoApplication {
    public static void main(String[] args) {
        MenuUserView view = new MenuUserView();
        MenuCryptoView viewCrypto = new MenuCryptoView();
        UserAuthenticationService userAuthenticationService = new UserAuthenticationService();
       // It makes crypto prices fluctuate, remove this to disable
        Cryptocurrencyfluctuation cryptocurrencyfluctuation = new Cryptocurrencyfluctuation();
        cryptocurrencyfluctuation.FluctuationTime();
        //
        MenuUserController controller  = new MenuUserController(view, userAuthenticationService);
       controller.run();
       view.close();
       viewCrypto.close();

    }
}
