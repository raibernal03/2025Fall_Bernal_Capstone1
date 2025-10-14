package logic;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileManager {
    //reads file
    public static List<Transaction> readFile() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            FileReader fr = new FileReader("src/main/resources/transactions.csv");
            BufferedReader reader = new BufferedReader(fr);

            String line;

            while ((line = reader.readLine()) != null) {
                String[] transactionData = line.split("\\|");
                Transaction transaction = new Transaction();

                //Setting values to object to each transaction
                transaction.setDate(LocalDate.parse(transactionData[0]));
                transaction.setTime(LocalTime.parse(transactionData[1]));
                transaction.setDescription(transactionData[2]);
                transaction.setVendor(transactionData[3]);
                transaction.setAmount(Double.parseDouble(transactionData[4]));

                transactions.add(transaction);
            }
            reader.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //sorts list by date
        transactions.sort(Comparator.comparing((Transaction t) -> t.getDate()).reversed());

        return transactions;
    }

    //writes to file
    public static void writeFile(Transaction transaction) {
        try {
            FileWriter fw = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("\n" + transaction.getDate().toString() + "|" + transaction.getTime().toString() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount());

            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //reads file for header
    public static void headerLogo(String logoPath) {
        try {
            FileReader fr = new FileReader(logoPath);

            //great the file is open now we need to go through it
            BufferedReader reader = new BufferedReader(fr);

            //Let's actually go through the file line by line
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);

            }
            reader.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}