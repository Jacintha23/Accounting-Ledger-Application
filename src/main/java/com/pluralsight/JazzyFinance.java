package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class JazzyFinance {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();
    static final String file = "transactions.csv";

    public static void main(String[] args) {
        loadTransactions();
        homeScreen();
    }

    // Create a Home Screen here, this will display user's available choices
    public static void homeScreen() {
        while (true) {
            System.out.println("Welcome! What would you like to do today?");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    addPayment();
                    break;
                case "L":
                    ledgerScreen();
                    break;
                case "X":
                    System.out.println("Have a nice day, until next time.");
                    return;
                default:
                    System.out.println("Uh oh! This selection is invalid, please try again.");
            }
        }
    }

    // Place the BufferedReader here to read the data from the csv file
    public static void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                Transaction t = new Transaction(
                        LocalDate.parse(parts[0]),
                        LocalTime.parse(parts[1]),
                        parts[2],
                        parts[3],
                        Double.parseDouble(parts[4])
                );

                transactions.add(t);
            }

        } catch (IOException e) {
            System.out.println("This file does not exist, please try again.");
        }
    }

    // ================= SAVE =================
    public static void saveTransaction(Transaction t) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(t.date + "," + t.time + "," + t.purpose + "," + t.vendor + "," + t.amount);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("There was an error with this transaction.");
        }
    }

    // ================= ADD DEPOSIT =================
    public static void addDeposit() {
        System.out.print("Purpose: ");
        String desc = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), desc, vendor, amount);

        transactions.add(t);
        saveTransaction(t);
    }

    // ================= ADD PAYMENT =================
    public static void addPayment() {
        System.out.print("Purpose: ");
        String desc = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        amount = -Math.abs(amount);

        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), desc, vendor, amount);

        transactions.add(t);
        saveTransaction(t);
    }

    // ================= LEDGER =================
    public static void ledgerScreen() {
        while (true) {
            System.out.println("\n--- LEDGER ---");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    displayTransactions(transactions);
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reportsScreen();
                    break;
                case "H":
                    return;
            }
        }
    }

    // ================= DISPLAY =================
    public static void displayTransactions(ArrayList<Transaction> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
    }

    public static void displayDeposits() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            if (transactions.get(i).amount > 0) {
                System.out.println(transactions.get(i));
            }
        }
    }

    public static void displayPayments() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            if (transactions.get(i).amount < 0) {
                System.out.println(transactions.get(i));
            }
        }
    }

    // ================= REPORTS =================
    public static void reportsScreen() {
        while (true) {
            System.out.println("\n--- REPORTS ---");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    monthToDate();
                    break;
                case "2":
                    previousMonth();
                    break;
                case "3":
                    yearToDate();
                    break;
                case "4":
                    previousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    return;
            }
        }
    }

    public static void monthToDate() {
        LocalDate now = LocalDate.now();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);

            if (t.date.getMonth() == now.getMonth() &&
                    t.date.getYear() == now.getYear()) {
                System.out.println(t);
            }
        }
    }

    public static void previousMonth() {
        LocalDate now = LocalDate.now().minusMonths(1);

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);

            if (t.date.getMonth() == now.getMonth() &&
                    t.date.getYear() == now.getYear()) {
                System.out.println(t);
            }
        }
    }

    public static void yearToDate() {
        int year = LocalDate.now().getYear();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);

            if (t.date.getYear() == year) {
                System.out.println(t);
            }
        }
    }

    public static void previousYear() {
        int year = LocalDate.now().getYear() - 1;

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);

            if (t.date.getYear() == year) {
                System.out.println(t);
            }
        }
    }

    public static void searchByVendor() {
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine().toLowerCase();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);

            if (t.vendor.toLowerCase().contains(vendor)) {
                System.out.println(t);
            }
        }
    }
}

// ================= TRANSACTION CLASS =================
class Transaction {
    LocalDate date;
    LocalTime time;
    String purpose;
    String vendor;
    double amount;

    public Transaction(LocalDate date, LocalTime time, String purpose, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.purpose = purpose;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String toString() {
        return date + " | " + time + " | " + purpose + " | " + vendor + " | " + amount;
    }
}