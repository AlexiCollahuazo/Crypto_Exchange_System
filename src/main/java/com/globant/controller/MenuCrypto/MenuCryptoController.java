package com.globant.controller.MenuCrypto;

import com.globant.view.ConsoleView;

public class MenuCryptoController {

    private  final ConsoleView view;
    private  final DepositController depositController;
    private  final ShowBalanceController showBalanceController;
    private final ExchangeController exchangeController;

    public MenuCryptoController(ConsoleView view) {
        this.view = view;
        this.depositController = new DepositController(view);
        this.showBalanceController = new ShowBalanceController(view);
        this.exchangeController = new ExchangeController(view);
    }

    public void run() {
        while (true) {
            int choice = view.MenuCryptoChoice();
            switch (choice) {
                case 1:
                    depositController.execute();
                    break;
                case 2:
                    showBalanceController.execute();
                    break;
                case 3:
                    // EXCHANGE
                    exchangeController.execute();

                    break;

                case 4:
                    // BUY ORDER

                    break;

                case 5:
                    // SELL ORDER

                    break;
                case 6:
                    // VIEW TRANSACTIONS

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
