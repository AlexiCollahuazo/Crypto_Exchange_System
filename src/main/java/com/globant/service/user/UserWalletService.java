package com.globant.service.user;

import java.math.BigDecimal;
import java.util.Map;

public class UserWalletService {

    private UserSingleton userSingleton;

    public UserWalletService(UserSingleton userSingleton) {
           this.userSingleton = userSingleton;
    }


    public BigDecimal getBalance() {
        return userSingleton.getCurrentWallet().getBalance();
    }

    public Map<String, BigDecimal> getMycryptocurrencies() {
        return userSingleton.getCurrentWallet().getMycryptocurrencies();
    }

    public BigDecimal getMycryptocurrencies(String name) {
        return userSingleton.getCurrentWallet().getMycryptocurrencies(name);
    }


    public void withdraw(BigDecimal totalAmount) {
        try{
        if (userSingleton.getCurrentWallet().getBalance().compareTo(totalAmount) >= 0) {
            userSingleton.getCurrentWallet().withdraw(totalAmount);
        }

        } catch (Exception e){
            System.out.println("Insufficient funds");
        }
    }

    public void deposit(BigDecimal amount) {
        userSingleton.getCurrentWallet().deposit(amount);
    }


    public void addCrypto(String crypto, BigDecimal amount) {
        try {

            userSingleton.getCurrentWallet().addCrypto(crypto, amount);
        }
        catch (Exception e){
            System.out.println("Error adding crypto");

        }
    }




    public boolean checkbalance(UserWalletService wallet, BigDecimal totalAmount) {
        return wallet.getBalance().compareTo(totalAmount) >= 0;
    }



}
