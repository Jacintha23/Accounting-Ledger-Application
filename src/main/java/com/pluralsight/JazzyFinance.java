package com.pluralsight;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class JazzyFinance
{
   // static ArrayList<Ledger> ledgers;
  //static HashMap
    static Scanner scanner = new Scanner(System.in);

    static void main()
    {
        homeScreen();
    }
    public static void homeScreen()
    //List out the greeting and options
    //Be sure to make a scanner function for the users input!
    {
        System.out.println("---------------------------------------");
        System.out.println("Hello, what transaction would you like to make today?");
        System.out.println("---------------------------------------");
        System.out.println("D) Add Deposit "); /* prompt user for the deposit information
                                                and save it to the csv file */
        System.out.println("P) Make Payment (Debit)"); /* prompt user for the debit information
                                                        and save it to the csv file */
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        String choice = scanner.nextLine().toUpperCase().strip();

        System.out.println(choice);
    }

// Map out Arraylist method/function
/*

 */


}
