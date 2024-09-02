package com.globant.model.orders;

import java.math.BigDecimal;

public class Order {
    private final String ordertype;
    private final int userId;
    private final String cryptotype;
    private final BigDecimal amount;
    private final BigDecimal MaxOrMinprice;


    public Order(String ordertype,int userId, String cryptotype,BigDecimal amount, BigDecimal maxOrMinprice) {

        this.ordertype = ordertype;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public BigDecimal getMaxOrMinprice() {

        return MaxOrMinprice;
    }


    

}
