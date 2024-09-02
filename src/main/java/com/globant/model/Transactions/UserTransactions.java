package com.globant.model.Transactions;

import java.math.BigDecimal;

public class UserTransactions {


    private final String cryptoType;
    private final BigDecimal amount;
    private final BigDecimal price;
    private final String orderType;

    public UserTransactions(String cryptoType, BigDecimal amount, BigDecimal price, String orderType) {
        this.cryptoType = cryptoType;
        this.amount = amount;
        this.price = price;
        this.orderType = orderType;
    }

// method to view user transactions
    @Override
    public String toString() {
        return "Transaction: " +
                " Cryptocurrency type= " + cryptoType  +
                ", Amount= " + amount +
                ", Price= " + price +
                ", orderType= " + orderType;
    }
}
