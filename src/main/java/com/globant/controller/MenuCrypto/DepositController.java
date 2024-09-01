package com.globant.controller.MenuCrypto;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.service.user.UnknownUserException;
import com.globant.model.user.UserSingleton;
import com.globant.service.user.UserWalletService;
import com.globant.view.MenuCryptoView;

import java.math.BigDecimal;

public class DepositController implements ControllerExecuteInterface {
    private final UserWalletService wallet;
    private final MenuCryptoView view;
    public DepositController( MenuCryptoView view) {
        this.view = view;
        this.wallet = new UserWalletService(UserSingleton.getInstance());
    }

    public void execute() {
        BigDecimal Amount = view.DepositView();

        if (Amount.compareTo(BigDecimal.ZERO) <= 0) {
            view.showError("The deposit cannot be 0 or less than 0");
            return;
        }
            try {
                wallet.deposit(Amount);
                view.showSuccessMessage("Your new wallet balance is: " + wallet.getBalance());

            } catch (UnknownUserException e) {
                view.showError("Deposit error");

            }


        }
    }


