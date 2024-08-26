package com.globant.controller;

import com.globant.model.user.User;
import com.globant.view.ConsoleView;

import java.math.BigDecimal;

public class CryptoUserController {

    private  ConsoleView view;
    private  DepositController depositController;
    private  ShowBalanceController showBalanceController;


    public CryptoUserController(ConsoleView view) {
        this.view = view;
        this.depositController = new DepositController(view);
        this.showBalanceController = new ShowBalanceController(view);
    }

    public void run() {
        while (true) {
            int choice = view.CryptoUserChoice();
            switch (choice) {
                case 1:
                    depositController.execute();
                    break;
                case 2:
                    showBalanceController.execute();
                    break;
                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    view.showLogoutMessage("GoodBye :,(");
                    return;
                case 8:
                    System.exit(0);
                    break;
                default:
                    view.showError("Wrong Option");
            }
        }
    }



}
