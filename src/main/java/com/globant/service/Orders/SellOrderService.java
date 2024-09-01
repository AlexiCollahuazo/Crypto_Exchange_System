package com.globant.service.Orders;

import com.globant.model.orders.Order;
import com.globant.model.orders.OrdersBook;
import com.globant.service.user.UserWalletService;

import java.math.BigDecimal;
import java.util.Map;

public class SellOrderService {


    private OrdersBook ordersBook;
    private MatchOrderService matchorders1;

    public SellOrderService() {
        this.ordersBook = OrdersBook.getInstance();
    }

    public void placeSellOrder(int userId, String Type, BigDecimal amount, BigDecimal minPrice, UserWalletService wallet) {
            Order order = new Order("Sell",userId,Type,amount, minPrice);
            ordersBook.placeSellOrder(order);
    }


    public Map<Integer, Order> getSellOrders() {
        return ordersBook.getSellOrders();
    }


}
