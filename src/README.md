<h1>Accounting Ledger Application</h1>
<h3>Examples</h3>

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
  * Creates a list of MTD transactions
  * Creates a list of previous month transactions
  * Creates a list of YTD Transactions
  * Creates a list of previous year transactions
  * Creates a list based of the vendor they want to look up
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

<h3>Issues I had</h3>

**Passing list through arguments**

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


### Defensive Coding

I used recursion
