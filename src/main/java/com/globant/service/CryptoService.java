package com.globant.service;

import com.globant.model.user.Cryptocurrencies;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CryptoService {
    private static CryptoService instance;
    private  final Map<String, Cryptocurrencies> cryptocurrencieslist;

    public CryptoService() {
        this.cryptocurrencieslist = new HashMap<>();
        GenerateCryptocurrencies();
    }

    public static CryptoService getInstance() {
        if (instance == null) {
            instance = new CryptoService();
        }
        return instance;
    }

    public void GenerateCryptocurrencies() {
        cryptocurrencieslist.put("BITCOIN", new Cryptocurrencies.Builder()
                .setType("BTC")
                .setPrice(new BigDecimal("4000"))
                .setAmount(new BigDecimal("2"))
                .build());

        cryptocurrencieslist.put("ETHEREUM", new Cryptocurrencies.Builder()
                .setType("ETH")
                .setPrice(new BigDecimal("3500"))
                .setAmount(new BigDecimal("450"))
                .build());
    }



    public Cryptocurrencies getCryptocurrencies(String name) {
        return cryptocurrencieslist.get(name.toUpperCase());
    }

    public Cryptocurrencies getCryptocurrencies(BigDecimal price) {
        return cryptocurrencieslist.get(price);
    }

    public boolean withdrawCryptos(String type, BigDecimal Amount) {

        Cryptocurrencies crypto = cryptocurrencieslist.get(type.toUpperCase());
        if (crypto != null && crypto.getAmount().compareTo(Amount) >= 0) {
            crypto.setAmount(crypto.getAmount().subtract(Amount));
            return  true;
        }
        return false;
    }

    public Map<String, Cryptocurrencies> getCryptocurrencieslist() {
        return cryptocurrencieslist;
    }


    public void Infocryptos(){
        cryptocurrencieslist.forEach((type, crypto) -> {
            System.out.println(type + ", Price: " + crypto.getPrice() );
        });
    }






}
