package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class JazzyFinance {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> transactions = new ArrayList<>();
    static final String FILE_NAME = "transactions.csv";

    public static void main(String[] args) {  // Fixed: added 'public static'
        homeScreen();
    }

    public static void homeScreen() {
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("Hello, what transaction would you like to make today?");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("---------------------------------------");
            System.out.println("Make a selection (D, P, L, OR X):");

            String choice = scanner.nextLine().strip().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();  // Fixed: moved method call inside switch
                    break;
                case "P":
                    addPayment();  // Fixed: moved method call inside switch
                    break;
                case "L":
                    ledgerScreen();
                    break;
                case "X":
                    System.out.println("Thank you, see you next time!");
            }
        }

    }
}

