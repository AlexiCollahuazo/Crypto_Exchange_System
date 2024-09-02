package com.globant.service.cryptocurrencies;

import com.globant.model.cryptocurrencies.Cryptocurrencies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CryptoService {
    private static CryptoService instance;
    private  final Map<String, Cryptocurrencies> cryptocurrencieslist;
    private final Random fluctuate = new Random();

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
// builder for cryptocurrencies
    public void GenerateCryptocurrencies() {
        cryptocurrencieslist.put("BITCOIN", new Cryptocurrencies.Builder()
                .setType("BTC")
                .setPrice(new BigDecimal("57373.75"))//current price of bitcoin
                .setAmount(new BigDecimal("500"))
                .build());

        cryptocurrencieslist.put("ETHEREUM", new Cryptocurrencies.Builder()
                .setType("ETH")
                .setPrice(new BigDecimal("4878.26")) //current price of Ethereum
                .setAmount(new BigDecimal("450"))
                .build());
    }
// More cryptos can be added here, the same process is followed

    public Cryptocurrencies getCryptocurrencies(String name) {
        return cryptocurrencieslist.get(name.toUpperCase());
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
            System.out.println(type + ", Market Price: " + crypto.getPrice() );
        });
    }

    public void fluctuatePrices() {

        try {
            for (Cryptocurrencies crypto : cryptocurrencieslist.values()) {

                // fluctuate.nextdouble gives values between 0 and 1 *0.10 p
                //goes up or down by 3 percent, From 2 to 5 percent is how they usually change, so I put 3
                BigDecimal fluctuation = new BigDecimal((fluctuate.nextDouble() * 0.10) - 0.03);
                BigDecimal actualPrice = crypto.getPrice(); //Get the price of the actual cryptocurrencies
                BigDecimal Price = actualPrice.add(actualPrice.multiply(fluctuation));
                // HALF UP, will round to only 2 decimal places in the bigdecimal.
                Price = Price.setScale(2, RoundingMode.HALF_UP);
                // Update the cryptocurrency price
                crypto.setPrice(Price);
            }
        }catch (Exception e){
            System.out.println("Error al fluctuar");
        }
    }



}
