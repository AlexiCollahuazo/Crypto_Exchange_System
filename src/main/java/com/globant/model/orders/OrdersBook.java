package com.globant.model.orders;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OrdersBook {

    private static OrdersBook instance;
    private Map<String, Map<String, BigDecimal>> buyorders;
    private Map<String, Map<String, BigDecimal>> sellorders;

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


}
