package com.globant.model.user;

public class User {
    private static int idCounter = 1;
    private int ID;
    protected String name;
    protected String email;
    private String password;
    private UserWallet wallet;


    public User(String name, String email, String password) {
        this.ID = idCounter++;
        this.name = name;
        this.email = email;
        this.password = password;
        this.wallet = new UserWallet();
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
}





