package com.globant.view;

import com.globant.service.cryptocurrencies.CryptoService;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCryptoView {
    private final Scanner scanner = new Scanner(System.in);
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final int INVALID_CHOICE = -1;

    public int MenuCryptoChoice() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("****************Welcome to Exchange Crypto********** :)");
        System.out.println("Choose one of the following options:");
        System.out.println("1. Deposit ");
        System.out.println("2. View Wallet Balance ");
        System.out.println("3. Exchange");
        System.out.println("4. Put buy order");
        System.out.println("5. Put sell order");
        System.out.println("6. See transactions");
        System.out.println("7. Logout");
        System.out.println("8. Quit");
        System.out.print("Enter your choice: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;

        } catch (InputMismatchException e) {
            scanner.nextLine();
            return INVALID_CHOICE;
        }
    }

    public BigDecimal DepositView(String message) {
        try {
            System.out.print(message);
            BigDecimal money = scanner.nextBigDecimal();
            scanner.nextLine();
            return money;

        } catch (InputMismatchException e) {
            scanner.nextLine();
           showError("Invalid Option, They have to be numbers");
            return DepositView(message);
        }
    }

    public String CryptoTypeView(String message)
    {
            CryptoService cryptoService = CryptoService.getInstance(); // Receive the instance of the cryptos
            System.out.println(message);
            cryptoService.Infocryptos();// Show the prices
            String type = scanner.nextLine();
            if (type.isEmpty()){return CryptoTypeView(message);}
            return  type;
    }

    public BigDecimal ExchangeAmountView(String message)
    {
        try {
            System.out.println(message);
            BigDecimal amount = scanner.nextBigDecimal();
            scanner.nextLine();
            return amount;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            showError("Error, try again");
            return  ExchangeAmountView(message);
        }
    }

    public BigDecimal getdataBigdecimal(String message){
       try {
           System.out.println(message);
           BigDecimal amount = scanner.nextBigDecimal();
           scanner.nextLine();
           return amount;
       }
       catch (InputMismatchException e)
       {
           scanner.nextLine();
           showError("Wrong number");
           return  getdataBigdecimal(message);
       }

    }
    public void showLogoutMessage(String Message) {
        System.out.println(ANSI_RED + Message + ANSI_RESET);
    }

    public void showError(String errorMessage) {
        System.out.println(ANSI_RED + errorMessage + ANSI_RESET);
    }

    public void showSuccessMessage(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }

    public void showMessage(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }

    public void close() {
        scanner.close();
    }
}
