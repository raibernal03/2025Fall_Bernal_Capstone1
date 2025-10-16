package logic;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu(scanner);
        }
    }

    //main menu
    public static void menu(Scanner scanner) {
        FileManager.headerLogo("src/main/resources/headers/accounting-ledger-app-ascii.txt");

        System.out.println("D) Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.print("--> ");
        try {
            char choice = scanner.nextLine().toUpperCase().charAt(0);
            switch (choice) {
                case 'D':
                    Ledger.addTransaction(scanner, "positive");
                    break;
                case 'P':
                    Ledger.addTransaction(scanner, "negative");
                    break;
                case 'L':
                    ledgerMenu(scanner);
                    break;
                case 'X':
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice, Please try again\n");
            }
            System.out.println("\n");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    //goes to ledger menu
    public static void ledgerMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            FileManager.headerLogo("src/main/resources/headers/ledger-menu-ascii.txt");
            System.out.println("\n\nA) All - Displays all entries");
            System.out.println("D) Show all Deposits");
            System.out.println("P) Show all Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("--> ");
            try {
                char choice = scanner.nextLine().toUpperCase().charAt(0);
                switch (choice) {
                    case 'A':
                        FileManager.headerLogo("src/main/resources/headers/all-transactions-ascii.txt");
                        List<Transaction> transactions = FileManager.readFile();
                        printList(transactions);

                        break;
                    case 'D':
                        //Show all positive transactions
                        FileManager.headerLogo("src/main/resources/headers/deposits-ascii.txt");
                        Ledger.depositPayment("deposits");

                        break;
                    case 'P':
                        FileManager.headerLogo("src/main/resources/headers/payments-ascii.txt");
                        Ledger.depositPayment("payments");
                        break;
                    case 'R':
                        reportsMenu(scanner);
                        break;
                    case 'H':
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid Choice, Please try again\n\n");

                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    //Reports menu
    public static void reportsMenu(Scanner scanner) {
        boolean running = true;
        while (running) {

            FileManager.headerLogo("src/main/resources/headers/reports-menu-ascii.txt");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("--> ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        //Sends to month to date
                        FileManager.headerLogo("src/main/resources/headers/current-month-report-ascii.txt");
                        Reports.mtd();

                        break;
                    case 2:
                        //send to previous month
                        Reports.previousMonth();

                        break;
                    case 3:
                        //send to year to date
                        Reports.ytd();

                        break;
                    case 4:
                        //send to previous year
                        Reports.previousYear();

                        break;
                    case 5:
                        //send to search by vendor
                        System.out.print("Enter vendor name: ");
                        String vendor = scanner.nextLine();
                        System.out.println("\n");
                        Reports.vendorSearch(vendor);

                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid Choice, Please try again\n");

                        break;
                }
                System.out.println("\n");
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    //Prints any list its given
    public static void printList(List<Transaction> transactions) {

        System.out.printf("%-15s │ %-15s │ %-30s │ %-30s │ $%-10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

        for (Transaction transaction : transactions) {
            System.out.printf("%-15tD │ %-15tr │ %-30s │ %-30s │ %10.2f$\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
        }
    }
}