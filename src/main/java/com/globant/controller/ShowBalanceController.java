package com.globant.controller;

import com.globant.model.user.UserWallet;
import com.globant.service.UnknownUserException;
import com.globant.service.UserSingleton;
import com.globant.view.ConsoleView;

import java.math.BigDecimal;

public class ShowBalanceController implements ControllerExecuteInterface {
    private ConsoleView view;

    public ShowBalanceController(ConsoleView view) {
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
