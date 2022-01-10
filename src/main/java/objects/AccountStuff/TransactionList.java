package objects.AccountStuff;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TransactionList {
    private LinkedList<Transaction> transactions;

    public TransactionList() {
        transactions = new LinkedList<>();
    }

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }
}
