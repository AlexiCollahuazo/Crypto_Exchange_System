package com.globant.model.orders;

import java.util.HashMap;
import java.util.Map;

public class OrdersBook {

    private static OrdersBook instance;
    private final Map<Integer, Order> buyorders;
    private final Map< Integer, Order> sellorders;
    private int IdOrder = 1;

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
    public void placeBuyOrder(Order order) {
      buyorders.put(IdOrder++, order);
    }
    public void placeSellOrder(Order order) {
        sellorders.put(IdOrder++, order);
    }

    public Map<Integer, Order> getBuyOrders() {
        return buyorders;
    }

    public Map<Integer, Order> getSellOrders() {
        return sellorders;
    }

    public void removeBuyOrder(int orderId) {
        buyorders.remove(orderId);
    }

    public void removeSellOrder(int orderId) {
        sellorders.remove(orderId);
    }


}
