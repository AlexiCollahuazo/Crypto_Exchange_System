package com.globant.model.user;
import com.globant.model.Transactions.GlobalTransactions;
import com.globant.model.Transactions.UserTransactions;
public class User {
    private static int incrementalId = 1;
    private final int ID;
    private final String name;
    private final String email;
    private final String password;
    private final UserWallet wallet;
    private final GlobalTransactions transactions;

    public User(String name, String email, String password) {
        this.ID = incrementalId++;
        this.name = name;
        this.email = email;
        this.password = password;
        this.wallet = new UserWallet();
        this.transactions = new GlobalTransactions();
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getID() {
        return ID;
    }

    public UserWallet getWallet(){
        return wallet;
    }

    public GlobalTransactions getTransactions() {
        return transactions;
    }
}





