package logic;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CustomSearch {
    //Custom Search
    public static void customSearch(Scanner scanner){
        List<Transaction> transactions = FileManager.readFile();
        //Harder Logo
        System.out.println("Enter Start Date: ");
        System.out.println("Use this format mm-dd-yyyy");
        //User input
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter End Date: ");
        System.out.println("Use this format mm-dd-yyyy");
        //User Input
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter Description:");
        //User Input
        String description = scanner.nextLine();

        System.out.println("Enter Vendor: ");
        //User Input
        String vendor = scanner.nextLine();

        System.out.println("Enter Amount: ");
        //User Input
        double  amount = Double.parseDouble(scanner.nextLine());




    }

}
