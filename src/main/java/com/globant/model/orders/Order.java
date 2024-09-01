package com.globant.model.orders;

import java.math.BigDecimal;

public class Order {
    private String ordertype;
    private int userId;
    private String cryptotype;
    private BigDecimal amount;
    private BigDecimal MaxOrMinprice;


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
