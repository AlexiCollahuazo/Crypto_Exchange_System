package com.globant.service.cryptocurrencies;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Cryptocurrencyfluctuation {
    private  final CryptoService cryptoService = CryptoService.getInstance(); //Call the  instance of cryptocurrencies
//responsible for carrying out how often it fluctuates

    public void FluctuationTime(){
        // Create an instance of the class, the thread is responsible for keeping track of the time
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        // Crypto prices are updated every 20 seconds
        executorService.scheduleAtFixedRate(cryptoService::fluctuatePrices, 0, 20, TimeUnit.SECONDS);
    }
}
