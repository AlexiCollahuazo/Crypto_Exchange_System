package com.globant.service.user;

import com.globant.model.user.User;
import com.globant.model.user.UserSingleton;
import com.globant.model.user.UserWallet;

import java.math.BigDecimal;
import java.util.Map;

public class UserWalletService {

    private final UserSingleton userSingleton;

    public UserWalletService(UserSingleton userSingleton) {
           this.userSingleton = userSingleton;
    }

    public BigDecimal getBalance() {
        return userSingleton.getCurrentWallet().getBalance();
    }

    public Map<String, BigDecimal> getMycryptocurrencies() {
        return userSingleton.getCurrentWallet().getMycryptocurrencies();
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

    public boolean Checkcryptocurrencies(UserWalletService wallet, String type, BigDecimal Amount) {

        return wallet.getMycryptocurrencies().get(type).compareTo(Amount)>=0;
    }


    public void UpdateMoneyWallet(User buyer, User seller, BigDecimal Total){
        try {
            buyer.getWallet().withdraw(Total);
            seller.getWallet().deposit(Total);
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }

    public void UpdateCryptoWallet(User buyer, User seller,String typeCrypto, BigDecimal amount){
        try {

            seller.getWallet().substractCrypto(typeCrypto, amount);
            buyer.getWallet().addCrypto(typeCrypto, amount);
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }




    public boolean checkbalance(UserWalletService wallet, BigDecimal totalAmount) {
        return wallet.getBalance().compareTo(totalAmount) >= 0;
    }




}
