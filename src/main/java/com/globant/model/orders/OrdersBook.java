package com.globant.model.orders;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OrdersBook {

    private static OrdersBook instance;
    private Map<String, Order> buyorders;
    private Map<String, Order> sellorders;

    private OrdersBook()
    {
        buyorders = new HashMap<>();
        sellorders = new HashMap<>();
    }


    public static OrdersBook getInstance() {
        if (instance == null) {
            instance = new OrdersBook();
        }
        return instance;
    }

    public void placeBuyOrder(String cryptoType, BigDecimal price) {
        int orderId = generateOrderId();// revisar esto
        Order order = new Order(orderId, cryptoType, price);
        buyorders.put("BUY ORDER", order);

    }

    public Map<String, Order> getBuyOrders() {
        return buyorders;
    }


    private int generateOrderId() {
        return buyorders.size() + sellorders.size() + 1;
    }



}
