package logic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bank App Name");
        while (true) {
            FileManager.headerLogo("src/main/resources/headers/accounting-ledger-app-ascii.txt");
            menu(scanner);
        }
    }

    //main menu
    public static void menu(Scanner scanner) {
        System.out.println("D) Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.print("--> ");

        char choice = scanner.nextLine().toUpperCase().charAt(0);
        switch (choice) {
            case 'D':
                AddTransactions.addTransaction(scanner, "positive");
                break;
            case 'P':
                AddTransactions.addTransaction(scanner, "negative");
                break;
            case 'L':
                ledgerMenu(scanner);
                break;
            case 'X':
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice, Please try again\n");
        }
        System.out.println("\n");
    }

    //goes to ledger menu
    public static void ledgerMenu(Scanner scanner) {
        FileManager.headerLogo("src/main/resources/headers/ledger-menu-ascii.txt");
        System.out.println("\n\nA) All - Displays all entries");
        System.out.println("D) Show all Deposits");
        System.out.println("P) Show all Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");

        char choice = scanner.nextLine().toUpperCase().charAt(0);
        switch (choice) {
            case 'A':
                FileManager.headerLogo("src/main/resources/headers/all-transactions-ascii.txt");
                List<Transaction> transactions = FileManager.readFile();
                AddTransactions.printList(transactions);
                ledgerMenu(scanner);
                break;
            case 'D':
                //Show all positive transactions
                FileManager.headerLogo("src/main/resources/headers/deposits-ascii.txt");
                AddTransactions.printList(AddTransactions.depositPayment("deposits"));
                ledgerMenu(scanner);
                break;
            case 'P':
                FileManager.headerLogo("src/main/resources/headers/payments-ascii.txt");
                AddTransactions.printList(AddTransactions.depositPayment("payments"));
                ledgerMenu(scanner);
                break;
            case 'R':
                reportsMenu(scanner);
                ledgerMenu(scanner);
                break;
            case 'H':
                menu(scanner);
                break;
            default:
                System.out.println("Invalid Choice, Please try again\n\n");
                ledgerMenu(scanner);
        }
    }

    //Reports menu
    public static void reportsMenu(Scanner scanner) {
        FileManager.headerLogo("src/main/resources/headers/reports-menu-ascii.txt");
        System.out.println("1) Month to Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year to Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("0) Back");
        System.out.print("--> ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                //Sends to month to date
                FileManager.headerLogo("src/main/resources/headers/current-month-report-ascii.txt");
                List<Transaction> mtd = Reports.mtd();
                AddTransactions.printList(mtd);
                reportsMenu(scanner);
                break;
            case 2:
                //send to previous month
                List<Transaction> previousMonth = Reports.previousMonth();
                AddTransactions.printList(previousMonth);
                reportsMenu(scanner);
                break;
            case 3:
                //send to year to date
                List<Transaction> ytd = Reports.ytd();
                AddTransactions.printList(ytd);
                reportsMenu(scanner);
                break;
            case 4:
                //send to previous year
                List<Transaction> lastYear = Reports.lastYear();
                AddTransactions.printList(lastYear);
                reportsMenu(scanner);

                break;
            case 5:
                //send to search by vendor
                System.out.print("Enter vendor: ");
                String vendor = scanner.nextLine();
                System.out.println("\n");
                AddTransactions.printList(Reports.vendorSearch(vendor));
                reportsMenu(scanner);
                break;
            case 0:
                ledgerMenu(scanner);
            default:
                System.out.println("Invalid Choice, Please try again\n");
                reportsMenu(scanner);
                break;
        }

    }


}
