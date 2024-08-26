package com.globant.service;

import com.globant.model.user.Cryptocurrencies;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CryptoBuilder {
    private Map<String, Cryptocurrencies> cryptocurrencieslist = new HashMap<>();

    public CryptoBuilder() {
        GenerateCryptocurrencies();
    }

    private void GenerateCryptocurrencies() {
        cryptocurrencieslist.put("BTC", new Cryptocurrencies.Builder()
                .setType("Bitcoin")
                .setPrice(new BigDecimal("40000"))
                .setAmount(new BigDecimal("112"))
                .build());

        cryptocurrencieslist.put("ETH", new Cryptocurrencies.Builder()
                .setType("Ethereum")
                .setPrice(new BigDecimal("3500"))
                .setAmount(new BigDecimal("450"))
                .build());
    }

    public Cryptocurrencies getCryptocurrencies(String type) {
        return cryptocurrencieslist.get(type);
    }

    /*
    public void updatePrice(String type, BigDecimal newPrice) {
        if (cryptocurrencieslist.containsKey(type)) {
            cryptocurrencieslist.get(type).setPrice(newPrice);
        }
    }
*/

    public void updateAmount(String type, BigDecimal Amount) {
        if (cryptocurrencieslist.containsKey(type)) {
            BigDecimal currentAmount = cryptocurrencieslist.get(type).getAmount();
            cryptocurrencieslist.get(type).setAmount(currentAmount.subtract(Amount));
        }
    }

    public Map<String, Cryptocurrencies> getCryptocurrencieslist() {
        return cryptocurrencieslist;
    }

}
