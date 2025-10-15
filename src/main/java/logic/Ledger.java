package logic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {


    //This will display all the transaction based on deposit or payment
    public static void depositPayment(String depositOrPayment) {
        List<Transaction> transactions = FileManager.readFile();
        List<Transaction> depositsPayment = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (depositOrPayment.equalsIgnoreCase("deposits")) {
                if (transaction.getAmount() > 0) {
                    depositsPayment.add(transaction);
                }
            } else if (depositOrPayment.equalsIgnoreCase("payments")) {
                if (transaction.getAmount() < 0) {
                    depositsPayment.add(transaction);
                }
            }
        }
        Main.printList(depositsPayment);
    }

    //get data to make a deposit or payment
    public static void addTransaction(Scanner scanner, String negativePositive) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.print("Enter transaction description: ");
        String description = scanner.nextLine();

        System.out.print("Enter transaction vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter transaction amount: $");
        double amount = Double.parseDouble(scanner.nextLine());

        if (negativePositive.equalsIgnoreCase("negative")) {
            amount = -amount;
        }

        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        //transactions.add(newTransaction);
        FileManager.writeFile(newTransaction);

    }
}
