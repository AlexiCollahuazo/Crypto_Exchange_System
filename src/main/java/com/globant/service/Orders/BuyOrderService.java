package com.globant.service.Orders;

import com.globant.model.orders.Order;
import com.globant.model.orders.OrdersBook;
import com.globant.service.user.UserWalletService;

import java.math.BigDecimal;
import java.util.Map;

public class BuyOrderService {

    private OrdersBook ordersBook;

    public BuyOrderService() {
        this.ordersBook = OrdersBook.getInstance();
    }

    public void placeBuyOrder(String Type, BigDecimal amount, BigDecimal maxPrice, UserWalletService wallet) {
        BigDecimal totalCost = amount.multiply(maxPrice);
        if (wallet.getBalance().compareTo(totalCost) >= 0) {
            wallet.withdraw(totalCost);
            wallet.addCrypto(Type, amount);
            ordersBook.placeBuyOrder(Type, maxPrice);
        } else {
            throw new IllegalArgumentException("Insufficient funds to place buy order.");
        }
    }

    public Map<String, Order> getBuyOrders() {
        return ordersBook.getBuyOrders();
    }

}
