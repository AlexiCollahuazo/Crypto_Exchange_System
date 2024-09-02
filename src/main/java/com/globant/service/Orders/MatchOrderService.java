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
            //Search the orders to match
            for (Map.Entry<Integer, Order> buyEntry : buyOrders.entrySet()) {
                Order buyOrder = buyEntry.getValue();
                for (Map.Entry<Integer, Order> sellEntry : sellOrders.entrySet()) {
                    Order sellOrder = sellEntry.getValue();

                    //Obtain the user to whom the order belongs
                    User buyer = user.getUserById(buyOrder.getUserId());
                    User seller = user.getUserById(sellOrder.getUserId());
                    String buyerName = buyer.getName();
                    String sellerName = seller.getName();

                    // Creating variables
                    String typeCrypto = buyOrder.getCryptotype();
                    BigDecimal amount = buyOrder.getAmount();
                    BigDecimal Total = buyOrder.getMaxOrMinprice().min(sellOrder.getMaxOrMinprice());
                    //Creating BUY Order Variables
                    String typeOrderBuy = buyOrder.getOrdertype();
                    String typeCryptoBuy = buyOrder.getCryptotype();
                    BigDecimal buyerBalance = buyer.getWallet().getBalance();
                    //Creating SELL Order Variables
                    String typeOrderSell = sellOrder.getOrdertype();
                    BigDecimal sellerCryptoBalance = seller.getWallet().getMycryptocurrencies(typeCrypto);

                    /* when making a match the crypto amount doesn't become negative
                     and  the balance doesn't become negative*/
                    if (buyerBalance.compareTo(Total) >= 0 && sellerCryptoBalance.compareTo(amount) >= 0) {

                    //Ensures that orders comply with the proposed conditions
                    boolean check = CheckingOrders(buyOrder, sellOrder);
                    if (check) {
                        //Wallet is updated through WalletService methods
                        wallet.UpdateCryptoWallet(buyer, seller, typeCrypto, amount);
                        view.showSuccessMessage("Matching orders of: " + amount + " " + typeCryptoBuy + " per " + Total);
                        wallet.UpdateMoneyWallet(buyer, seller, Total);
                        //Transactions are created through UserTransactionService methods
                        transactions.addTransactionsBuy(typeCrypto, amount, Total, typeOrderBuy, buyer);
                        transactions.addTransactionsSell(typeCrypto, amount, Total, typeOrderSell, seller);
                        // Orders are removed
                        ordersBook.removeBuyOrder(buyEntry.getKey());
                        ordersBook.removeSellOrder(sellEntry.getKey());
                        return;
                    }
                    }else{
                        view.showError("An error occurred due to insufficient funds from the buyer or seller");
                    }
                }
            }

        } catch (Exception e)
        {
            view.showError("Error in the match ");

        }
    }
// Here are the conditions we are going to use
    private boolean CheckingOrders(Order buyOrder, Order sellOrder) {
        return buyOrder.getCryptotype().equals(sellOrder.getCryptotype())
                && buyOrder.getAmount().compareTo(sellOrder.getAmount()) == 0
                && buyOrder.getMaxOrMinprice().compareTo(sellOrder.getMaxOrMinprice()) >= 0;
    }
}
