package logic;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class Reports {
    static LocalDate today = LocalDate.now();

    //Month to Date
    public static void mtd(){
        List<Transaction> transactions = FileManager.readFile();
        List<Transaction> mtd = new ArrayList<>();
        //Variables for date and mtd
        LocalDate mtdStart = today.withDayOfMonth(1);
        LocalDate mtdEnd = today;
        for(Transaction t: transactions){
            if (t.getDate().isBefore(mtdEnd) && t.getDate().isAfter(mtdStart)){
                mtd.add(t);
            }
        }
        Main.printList(mtd);
    }

    //Previous Month
    public static void previousMonth(){
        YearMonth lastMonth = YearMonth.from(today).minusMonths(1);
        LocalDate prevMonthStart = lastMonth.atDay(1); // first day of previous month
        LocalDate prevMonthEnd = lastMonth.atEndOfMonth();

        List<Transaction> transactions = FileManager.readFile(); // OG List

        List<Transaction> previousMonth = new ArrayList<>();// List that will hold previous month transactions

        for (Transaction t: transactions){
            if(t.getDate().isBefore(prevMonthEnd) && t.getDate().isAfter(prevMonthStart)){
                previousMonth.add(t);
            }
        }
        Main.printList(previousMonth); //Print list
    }

    //Year to Date
    public static void ytd(){
        List<Transaction> transactions = FileManager.readFile();
        List<Transaction> ytd = new ArrayList<>();
        LocalDate ytdStart = today.withDayOfYear(1); // Jan 1 2025 this year
        LocalDate ytdEnd = today;
        for(Transaction t: transactions){
            if (t.getDate().isBefore(ytdEnd) && t.getDate().isAfter(ytdStart)){
                ytd.add(t);
            }
        }
        Main.printList(ytd);

    }


    //Previous Year
    public static void previousYear(){
        List<Transaction>  transactions = FileManager.readFile();
        List<Transaction> lastYear = new ArrayList<>();
        int ly = today.getYear() - 1;
        LocalDate prevYearStart = LocalDate.of(ly, 1, 1);  // setting date to 01/01/ly
        LocalDate prevYearEnd = LocalDate.of(ly, 12, 31);

        for(Transaction t: transactions){
            if(t.getDate().isBefore(prevYearEnd) && t.getDate().isAfter(prevYearStart)){
                lastYear.add(t);
            }
        }
        Main.printList(lastYear);

    }

    //Search by Vendor
    public static void vendorSearch(String keyword) {
        List<Transaction> transactions = FileManager.readFile();
        List<Transaction> vendorTransactions = new ArrayList<>();

        for(Transaction transaction : transactions) {
            if(transaction.getVendor().toLowerCase().contains(keyword.toLowerCase())) {
                vendorTransactions.add(transaction);
            }
        }

        Main.printList(vendorTransactions);
    }

}
