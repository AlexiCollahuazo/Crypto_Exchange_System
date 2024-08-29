package com.globant.model.orders;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OrdersBook {
    // revisar si es mejor builder
    private static OrdersBook instance;
    private Map<String, Map<String, BigDecimal>> buyorders;
    private Map<String, Map<String, BigDecimal>> sellorders;

    private OrdersBook()
    {
        buyorders = new HashMap<>();
        sellorders = new HashMap<>();
    }

// Probar si funciona como instancia global.

    public static OrdersBook getInstance() {
        if (instance == null) {
            instance = new OrdersBook();
        }
        return instance;
    }


}
