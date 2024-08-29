package com.globant.controller.MenuCrypto;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.service.user.UnknownUserException;
import com.globant.service.user.UserSingleton;
import com.globant.service.user.UserWalletService;
import com.globant.view.ConsoleView;

import java.math.BigDecimal;

public class DepositController implements ControllerExecuteInterface {
    private final ConsoleView view;
    private final UserWalletService wallet;

    public DepositController( ConsoleView view) {
        this.view = view;
        this.wallet = new UserWalletService(UserSingleton.getInstance());
    }

    public void execute() {
        BigDecimal Amount = view.DepositView();
        try
        {
            wallet.deposit(Amount);
            view.showSuccessMessage("Your new wallet balance is: " + wallet.getBalance());

        } catch (UnknownUserException e) {
            view.showError("Deposit error");

        }

    }
}
