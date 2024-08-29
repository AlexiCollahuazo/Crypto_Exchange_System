package com.globant.model.orders;

import java.math.BigDecimal;

public class Order {
    private String ordertype;
    private String cryptotype;
    private BigDecimal amount;
    private BigDecimal MaxOrMinprice;

    public Order(String ordertype, String cryptotype, BigDecimal amount, BigDecimal maxOrMinprice) {
        this.ordertype = ordertype;
        this.cryptotype = cryptotype;
        this.amount = amount;
        this.MaxOrMinprice = maxOrMinprice;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public String getCryptotype() {
        return cryptotype;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getMaxOrMinprice() {

        return MaxOrMinprice;
    }


    

}
