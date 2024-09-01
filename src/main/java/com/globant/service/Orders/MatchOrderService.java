package com.globant.service.Orders;

import com.globant.model.Transactions.UserTransactions;
import com.globant.model.orders.Order;
import com.globant.model.orders.OrdersBook;
import com.globant.model.user.User;
import com.globant.model.user.UserSingleton;
import com.globant.service.user.UserTransactionsService;
import com.globant.service.user.UserWalletService;
import com.globant.view.MenuCryptoView;

import java.math.BigDecimal;
import java.util.Map;

public class MatchOrderService {

    private final MenuCryptoView view;

    private final BuyOrderService buyOrderService;
    private final SellOrderService sellOrderService;
    private final OrdersBook ordersBook;
    private final UserSingleton user;
    private final UserTransactionsService transactions;
    private final UserWalletService wallet;


    public MatchOrderService(MenuCryptoView view) {
        this.view = view;
        this.buyOrderService = new BuyOrderService();
        this.sellOrderService = new SellOrderService();
        this.user = UserSingleton.getInstance();
        this.ordersBook = OrdersBook.getInstance();
        this.transactions = new UserTransactionsService();
        this.wallet = new UserWalletService(user);
    }
    public void MatchOrders() {
        try
        {
            Map< Integer, Order> buyOrders = buyOrderService.getBuyOrders();
            Map< Integer, Order> sellOrders = sellOrderService.getSellOrders();

            for (Map.Entry<Integer, Order> buyEntry : buyOrders.entrySet()) {
                Order buyOrder = buyEntry.getValue();
                for (Map.Entry<Integer, Order> sellEntry : sellOrders.entrySet()) {
                    Order sellOrder = sellEntry.getValue();

                    User buyer = user.getUserById(buyOrder.getUserId());
                    User seller =user.getUserById(sellOrder.getUserId());
                    BigDecimal Total = buyOrder.getMaxOrMinprice().min(sellOrder.getMaxOrMinprice());
                    String typeOrderBuy = buyOrder.getOrdertype();
                    String typeCryptoBuy = buyOrder.getCryptotype();

                    String typeOrderSell = sellOrder.getOrdertype();

                    String typeCrypto = buyOrder.getCryptotype();
                    BigDecimal amount = buyOrder.getAmount();

                    if (buyer.getWallet().getBalance().compareTo(Total) < 0) {
                        view.showError("Match found but buyer does not have enough money");
                        return; // Revisar esto o mejorar, s epuede poner en un metodo
                    }

                    BigDecimal sellerCryptoBalance = seller.getWallet().getMycryptocurrencies(sellOrder.getCryptotype());
                    if (sellerCryptoBalance.compareTo(sellOrder.getAmount()) < 0) {
                        view.showError("The seller is missing " + sellOrder.getCryptotype());
                        return; // Revisar esto o mejorar
                    }





                    boolean check = CheckingOrders(buyOrder, sellOrder);
                    if (check) {

                        wallet.UpdateCryptoWallet(buyer,seller,typeCrypto,amount);
                        view.showSuccessMessage("Matching orders of: " +amount + " " + typeCryptoBuy + " per " + Total);
                        wallet.UpdateMoneyWallet(buyer,seller,Total);

                        transactions.addTransactionsBuy(typeCrypto, amount,Total,typeOrderBuy,buyer);
                        transactions.addTransactionsSell( typeCrypto, amount,Total,typeOrderSell,seller);

                        ordersBook.removeBuyOrder(buyEntry.getKey());
                        ordersBook.removeSellOrder(sellEntry.getKey());
                        return;
                    }
                }
            }

        } catch (Exception e)
        {
            view.showError("Error in the match ");

        }
    }


// Aqui van las condiciones que vamos a utilizar
    private boolean CheckingOrders(Order buyOrder, Order sellOrder) {
        return buyOrder.getCryptotype().equals(sellOrder.getCryptotype())
                && buyOrder.getAmount().compareTo(sellOrder.getAmount()) == 0
                && buyOrder.getMaxOrMinprice().compareTo(sellOrder.getMaxOrMinprice()) >= 0;
    }
}
