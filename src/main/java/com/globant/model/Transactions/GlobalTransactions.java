package com.globant.model.Transactions;

import java.util.HashMap;
import java.util.Map;

public class GlobalTransactions {
    private Map<Integer, UserTransactions> transactions;
    private int transactionsId = 1;

   // All transactions are saved here
    public GlobalTransactions() {
        this.transactions = new HashMap<>();
    }

    public void addTransactions(UserTransactions transaction) {
        transactions.put(transactionsId++, transaction);
    }

    public Map<Integer, UserTransactions> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "GlobalTransactions{" +
                "transactions=" + transactions +
                ", transactionsId=" + transactionsId +
                '}';
    }
}
