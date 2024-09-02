package com.globant.service.Orders;

import com.globant.model.orders.Order;
import com.globant.model.orders.OrdersBook;
import com.globant.service.user.UserWalletService;

import java.math.BigDecimal;
import java.util.Map;

public class BuyOrderService {

    private final OrdersBook ordersBook;


    public BuyOrderService() {
        this.ordersBook = OrdersBook.getInstance();
    }

// Create the buy order with the data received
    public void placeBuyOrder( int userId,String Type, BigDecimal amount, BigDecimal maxPrice, UserWalletService wallet) {
        BigDecimal Balance = wallet.getBalance();
        if (Balance.compareTo(maxPrice) >= 0) {
            Order order = new Order("Buy",userId,Type,amount, maxPrice);
            ordersBook.placeBuyOrder(order);
        } else {
            throw new IllegalArgumentException("You don't have enough money to place the buy order");
        }
    }

    public Map<Integer, Order> getBuyOrders() {
        return ordersBook.getBuyOrders();
    }

}
