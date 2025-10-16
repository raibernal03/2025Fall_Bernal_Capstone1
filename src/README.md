# Accounting Ledger Application
### Examples

* [ ]  Add Deposit and Payment
* [ ]  View all Transactions, deposits, and payments
* [ ]  View transactions by {mtd, ytd, previous month, previous year}
* [ ]  Search transaction by vendor name

<h3>Class organization</h3>

* Main class
  * main
  * Main menu
  * Ledger menu
  * reports menu
  * print list -- Prints any list I give it
* Ledger class
  * display deposits and payments
  * add deposits and payments
* Reports Class
  * Creates a list of MTD transactions and print it
  * Creates a list of previous month transactions and print it
  * Creates a list of YTD Transactions and print it 
  * Creates a list of previous year transactions and print it 
  * Creates a list based of the vendor they want to look up nd print it 
* FileManager
  * file that prints the headers/title
  * fileReasder() --> reads the file
  * fileWriter() --> writes t the file
* Transaction (pojo)
  * date
  * time
  * description
  * vendor
  * amount
  * constructor
  * getter & setter
* Customer Search

### File Manager & CSV 👷‍♀️

An issue I had along the way was that my writer was writing to the csv file kinda weird, it was writing all the deposits and payment in the same line, and it was because I forgot to tel java to write it onto a new line

I also needed to take out the header line from the csv file

### Defensive Coding 🤺

I used recursion for some pleases just in case the user inputs an incorrect character it'll let them try again

```java
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
                printList(transactions);
                ledgerMenu(scanner);
                break;
            case 'D':
                //Show all positive transactions
                FileManager.headerLogo("src/main/resources/headers/deposits-ascii.txt");
                Ledger.depositPayment("deposits");

                ledgerMenu(scanner);
                break;
            case 'P':
                FileManager.headerLogo("src/main/resources/headers/payments-ascii.txt");
                Ledger.depositPayment("payments");
                ledgerMenu(scanner);
                break;
            case 'R':
                reportsMenu(scanner);
                break;
            case 'H':
                menu(scanner);
                break;
            default:
                System.out.println("Invalid Choice, Please try again\n\n");
                ledgerMenu(scanner);
        }
    }
```

### Something NEW I Learned 👩‍🎓

Something new that I learned was how to use the date, it was honestly hard but I asked ai to give me a reference guide to dates in java and to give me some examples, and Ia lso watched a video that kinda went over how dates worked

### The Most Proud Of

Te part im the most proud of is the ASCII art headers, when I first wanted to use the headers like this I was trying to use the `System.out.println();` but I noticed that when it would print it out it would look funky, so I figured if I put it in a `.txt` file I can just read it and it'll print it out the way it is in the `.txt` file

I also created a method that'll take the address of what ever file I want it to and it'll print it out

I used this method to print out all my headers

```java
public static void headerLogo(String logoPath) {
        System.out.println("\n\n\n");
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
```

### Issues, Challenge, Bugs🪳

Passing list through arguments at first

```java
public class Main {
  public static void main(String[] args){
    List<Transaction> transactions = FileManager.readFile();
    ledgerMenu(transactions);
  }
  
  public static void ledgerMenu(List<Transaction> transactions){
    Ledger.deposits(transactions);
  }
}

public class Ledger{
  public static void deposits(List<Transaction> transactions){
    //code
  }
}

```

The issue was that throughout the whole project the file would only read from the file once and pass it throughout the classes and methods, so if I added a deposit or a payment and I would go back to the view the transactions the transaction I just added wwould not be shown;

My Solution

```java
public class Main {
  public static void main(String[] args){
  ledgerMenu();
  
  }
  
  public static void ledgerMenu(){
    Ledger.deposits();
  }
}

public class Ledger{
  public static void deposits(){
    List<Transaction> transactions = FileManager.readFile();
    //code
  }
} 
```

I made it so that every time I need to print the list I will have to read the file again gain like in the example above

### Hardest Bug 😭



