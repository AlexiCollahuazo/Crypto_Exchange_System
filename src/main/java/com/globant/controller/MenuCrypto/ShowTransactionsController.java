package com.globant.controller.MenuCrypto;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.model.Transactions.GlobalTransactions;
import com.globant.model.Transactions.UserTransactions;
import com.globant.model.user.UserSingleton;
import com.globant.view.MenuCryptoView;

import java.util.Map;

public class ShowTransactionsController implements ControllerExecuteInterface {
    private UserSingleton userSingleton;
    private MenuCryptoView view;

    public ShowTransactionsController(MenuCryptoView view) {
        this.userSingleton = UserSingleton.getInstance();
        this.view = view;

    }


    public void execute() {
        GlobalTransactions transactions = userSingleton.getCurrentUser().getTransactions();
        Map<Integer, UserTransactions> transactionMap = transactions.getTransactions();

        if (transactionMap.isEmpty()) {
            view.showError("No transaction has been made");

        } else {
            view.showSuccessMessage("*******Your Transactions********");
            transactionMap.forEach((id, transaction) -> view.showMessage(transaction.toString()));
        }
    }



}
