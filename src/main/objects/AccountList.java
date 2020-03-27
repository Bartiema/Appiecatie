package objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class AccountList {
    LinkedList<Account> accounts;
    int totalStock;

    public AccountList(File file) throws FileNotFoundException {
        accounts = AccountList.toRead(file);

        int stock = 0;
        for(Account a : accounts){
            stock += a.getStock();
        }
        totalStock = stock;
    }

    public void add(Account a){
        accounts.add(a);
    }

    public Account get(int index){
        return accounts.get(index);
    }

    /**
     * The method to return the String that is writen in the File
     * @return String
     */
    public String toWrite(){
        StringBuilder s1 = new StringBuilder();
        for(Account a :accounts) {
            s1.append(a.toWrite()).append("\n");
        }
        return s1.toString();
    }

    /**
     * The method to read from a File
     * @param file - the file to read from
     * @return the list of accounts in the file
     * @throws FileNotFoundException - when the file cant be found
     */
    public static LinkedList<Account> toRead(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        LinkedList<Account> accounts = new LinkedList<>();

        while(scanner.hasNextLine()){
            accounts.add(Account.toRead(scanner.nextLine()));
        }
        return accounts;
    }

    /**
     * A method to update all account statistics
     */
    public void updateAll(){
        Date date = new Date();
        for(Account a : accounts){
            a.update(date);
        }
    }

}
