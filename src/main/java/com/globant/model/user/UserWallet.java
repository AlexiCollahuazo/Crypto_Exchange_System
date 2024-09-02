package com.globant.model.user;

import com.globant.model.cryptocurrencies.Cryptocurrencies;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class UserWallet {
    private BigDecimal Balance;
    private final Map<String, BigDecimal> mycryptocurrencies;

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

    public BigDecimal getMycryptocurrencies(String name) {
        return mycryptocurrencies.getOrDefault(name, BigDecimal.ZERO);
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

    public void substractCrypto(String crypto, BigDecimal amount) {
        mycryptocurrencies.put(crypto, mycryptocurrencies.getOrDefault(crypto, BigDecimal.ZERO).subtract(amount));
    }



}
