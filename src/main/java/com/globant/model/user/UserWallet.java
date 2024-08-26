package com.globant.model.user;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class UserWallet {
    private BigDecimal Balance;
    private Map<String, BigDecimal> mycryptocurrencies;


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

    public void deposit(BigDecimal amount) {
        this.Balance = Balance.add(amount);
    }


    public void addcryptocurrencies(String crypto, BigDecimal amount) {
        mycryptocurrencies.put(crypto, mycryptocurrencies.getOrDefault(crypto, BigDecimal.ZERO).add(amount));
    }


}
