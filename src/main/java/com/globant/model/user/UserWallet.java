package com.globant.model.user;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class UserWallet {
    private BigDecimal Balance;
    private Map<String, BigDecimal> mycryptocurrencies;
    private static UserWallet instance;

    public UserWallet() {
        this.Balance = BigDecimal.ZERO;
        this.mycryptocurrencies = new HashMap<>();
    }

    public BigDecimal getBalance() {
        return Balance;
    }


    public Map<String, BigDecimal> getMycryptocurrencies() {
        return mycryptocurrencies;
    }


    public void withdraw(BigDecimal amount) {
            Balance = Balance.subtract(amount);


    }

    public void deposit(BigDecimal amount) {
            this.Balance = Balance.add(amount);

    }

    public void addCrypto(String crypto, BigDecimal amount) {
        mycryptocurrencies.put(crypto, mycryptocurrencies.getOrDefault(crypto, BigDecimal.ZERO).add(amount));
    }




}
