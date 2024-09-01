package com.globant.controller.MenuCrypto;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.model.user.UserWallet;
import com.globant.service.user.UnknownUserException;
import com.globant.model.user.UserSingleton;
import com.globant.view.MenuCryptoView;

public class ShowBalanceController implements ControllerExecuteInterface {

    private final MenuCryptoView view;

    public ShowBalanceController(MenuCryptoView view) {
        this.view = view;
    }

    public void execute() {
        try
        {
            UserWallet wallet = UserSingleton.getInstance().getCurrentWallet();
            view.showMessage("Current Balance: " + wallet.getBalance());
            view.showMessage("Current Cryptocurrencies: " + wallet.getMycryptocurrencies());


        } catch (UnknownUserException e) {
            view.showError(" ERROR TO SEE BALANCE ");

        }

    }
}
