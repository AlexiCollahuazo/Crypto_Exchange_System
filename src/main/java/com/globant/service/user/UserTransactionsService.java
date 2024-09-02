package com.globant.service.user;

import com.globant.model.Transactions.UserTransactions;
import com.globant.model.orders.Order;
import com.globant.model.orders.OrdersBook;
import com.globant.model.user.User;

import java.math.BigDecimal;

public class UserTransactionsService {
    public UserTransactionsService() {
    }
// Method responsible for adding transactions to the User who made a match with an BuyOrder
    public void addTransactionsBuy(String typeCryptoBuy, BigDecimal amountBuyOrder, BigDecimal Total
            , String typeOrderBuy, User buyer) {

        UserTransactions buyTransaction = new UserTransactions(typeCryptoBuy,
                amountBuyOrder, Total, typeOrderBuy);
        buyer.getTransactions().addTransactions(buyTransaction);
    }
    // Method responsible for adding transactions to the User who made a match with an SellOrder
    public void addTransactionsSell(String typeCryptoSell, BigDecimal amountSellOrder, BigDecimal Total
            , String typeOrderSell, User seller) {

        UserTransactions sellTransaction = new UserTransactions(typeCryptoSell,
                amountSellOrder, Total, typeOrderSell);
        seller.getTransactions().addTransactions(sellTransaction);

    }



}
