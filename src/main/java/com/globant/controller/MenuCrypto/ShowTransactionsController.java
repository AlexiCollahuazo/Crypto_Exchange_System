package com.globant.controller.MenuCrypto;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.model.Transactions.GlobalTransactions;
import com.globant.model.Transactions.UserTransactions;
import com.globant.model.user.UserSingleton;
import com.globant.view.MenuCryptoView;

import java.util.Map;

public class ShowTransactionsController implements ControllerExecuteInterface {
    private final UserSingleton userSingleton;
    private final MenuCryptoView view;
   //Calls the instance where users are stored
    public ShowTransactionsController(MenuCryptoView view) {
        this.userSingleton = UserSingleton.getInstance();
        this.view = view;

    }

    public void execute() {
       // Calls the user who is currently logged in
        GlobalTransactions transactions = userSingleton.getCurrentUser().getTransactions();

        Map<Integer, UserTransactions> transactionMap = transactions.getTransactions();

        if (transactionMap.isEmpty()) {
            view.showError("No transaction has been made");

        } else {
            view.showSuccessMessage("*******Your Transactions********");
            transactionMap.forEach((id, transaction) -> view.showMessage(transaction.toString()));//calls the ToString method
        }
    }



}
