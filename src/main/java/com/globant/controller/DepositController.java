package com.globant.controller;

import com.globant.model.user.User;
import com.globant.model.user.UserWallet;
import com.globant.service.UnknownUserException;
import com.globant.service.UserSingleton;
import com.globant.view.ConsoleView;

import java.math.BigDecimal;

public class DepositController implements ControllerExecuteInterface {
    private final ConsoleView view;

    public DepositController( ConsoleView view) {
        this.view = view;
    }

    public void execute() {
        BigDecimal Amount = view.DepositView();
        try
        {
            UserWallet wallet = UserSingleton.getInstance().getCurrentWallet();
            wallet.deposit(Amount);
            view.showSuccessMessage("Your new wallet balance is: " + wallet.getBalance());

        } catch (UnknownUserException e) {
            view.showError("Deposit error");

        }

    }
}
