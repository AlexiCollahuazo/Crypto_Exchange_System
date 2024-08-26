package com.globant.view;

import com.globant.model.user.Cryptocurrencies;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final int INVALID_CHOICE = -1;

    public int MenuUserChoice() {
        System.out.println("****************Welcome to Exchange Crypto********** :)");
        System.out.println("Choose one of the following options:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Quit");
        System.out.print("Enter your choice: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return INVALID_CHOICE;
        }
    }

    public int CryptoUserChoice() {
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
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return INVALID_CHOICE;
        }
    }

    public String[] getRegisterView() {
       final Scanner scanner = new Scanner(System.in);
        System.out.print("Please register your name: ");
        String name = scanner.nextLine();
        System.out.print("Please register your email: ");
        String email = scanner.nextLine();
        System.out.print("Please register your password: ");
        String password = scanner.nextLine();
        return new String[] {name, email, password};
    }

    public BigDecimal DepositView() {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please state the amount you are going to deposit: ");
        BigDecimal money = scanner.nextBigDecimal();
        return money;
    }

    public String[] getLoginView() {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();
        return new String[] {email, password};
    }

    public void showUserId(int userId) {
        System.out.println("USER ID IS: " + userId);
    }

    public void GetCryptosView(Map<String, Cryptocurrencies> cryptocurrencieslist) {
        System.out.println("Precios actuales del mercado:");
        for (Cryptocurrencies crypto : cryptocurrencieslist.values()) {
            System.out.println(crypto.getType() + ": $" + crypto.getPrice() + " USD, AVAILABLE: " + crypto.getAmount());
        }
    }

    public void showLogoutMessage(String Message) {
        System.out.println(ANSI_RED + Message + ANSI_RESET);
    }

    public void showError(String errorMessage) {
        System.out.println(ANSI_RED + errorMessage + ANSI_RESET);
    }

    public void showInfo(String message) {
        System.out.println(ANSI_BLUE + message + ANSI_RESET);
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
