package com.globant.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuUserView {
    private final Scanner scanner; //= new Scanner(System.in);
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final int INVALID_CHOICE = -1;

    public MenuUserView(){
        this.scanner = new Scanner(System.in);
    }

    public int MenuUserChoice() {
        System.out.println("****************Welcome to Exchange Crypto********** :)");
        System.out.println("Choose one of the following options:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Quit");
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

    public String[] getRegisterView() {
        System.out.print("Please register your name: ");
        String name = scanner.nextLine();
        System.out.print("Please register your email: ");
        String email = scanner.nextLine();
        System.out.print("Please register your password: ");
        String password = scanner.nextLine();
        return new String[]{name, email, password};
    }

    public String[] getLoginView() {
        System.out.print("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();
        return new String[]{email, password};
    }

    public void showUserId(String message, int userId) {
        System.out.println(message + userId);
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
