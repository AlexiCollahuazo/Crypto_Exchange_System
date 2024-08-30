package com.globant.service.Orders;

import com.globant.model.orders.Order;
import com.globant.model.orders.OrdersBook;
import com.globant.service.user.UserWalletService;

import java.math.BigDecimal;
import java.util.Map;

public class SellOrderService {


    private OrdersBook ordersBook;

    public SellOrderService() {
        this.ordersBook = OrdersBook.getInstance();
    }

    public void placeSellOrder(int userId, String Type, BigDecimal amount, BigDecimal maxPrice, UserWalletService wallet) {
        BigDecimal totalCost = amount.multiply(maxPrice);
        if (wallet.getBalance().compareTo(totalCost) >= 0) {
            wallet.substractCrypto(Type,amount);// restando bitcoin, verificar esto.
            ordersBook.placeBuyOrder(userId,Type,amount, maxPrice);
        } else {
            throw new IllegalArgumentException("You don't have enough money to place the sell order.");
        }
    }


    public Map<String, Order> getSellOrders() {
        return ordersBook.getSellOrders();
    }


}
