package com.globant.controller.MenuCrypto;

import com.globant.service.Orders.SellOrderService;
import com.globant.view.MenuCryptoView;

public class MenuCryptoController {

    private  final DepositController depositController;
    private  final ShowBalanceController showBalanceController;
    private final ExchangeController exchangeController;
    private final MenuCryptoView view;
    private final BuyOrderController buyOrderController;
    private final SellOrderController sellOrderController;


    public MenuCryptoController(/*ConsoleView*/ MenuCryptoView view) {
        this.view = view;
        this.depositController = new DepositController(view);
        this.showBalanceController = new ShowBalanceController(view);
        this.exchangeController = new ExchangeController(view);
        this.buyOrderController = new BuyOrderController(view);
        this.sellOrderController = new SellOrderController(view);
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
                    buyOrderController.execute();
                    break;

                case 5:
                    // SELL ORDER
                    sellOrderController.execute();
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
