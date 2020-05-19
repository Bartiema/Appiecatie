package objects.AccountStuff;

import objects.AccountStuff.AccountList;
import objects.AccountStuff.Transaction;

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

    public void add(Transaction transaction){
        transactions.addFirst(transaction);
        if(size()>100) transactions.removeLast();
    }

    public int size() {
        return transactions.size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Transaction t : transactions){
            s.append(t.toString()).append("\n");
        }
        return s.toString();
    }

    public String toWrite() {
        StringBuilder s = new StringBuilder();
        for(Transaction t : transactions){
            s.append(t.toWrite()).append("\n");
        }
        return s.toString();
    }

    public void toRead(File file , AccountList accounts) throws FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            add(Transaction.toRead(scanner.nextLine(), accounts));
        }
        scanner.close();
    }

    public List<Transaction> getAll() {
        return this.transactions;
    }
}
