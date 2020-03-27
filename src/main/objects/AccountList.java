package objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class AccountList {
    private LinkedList<Account> accounts;
    private int totalStock;

    public AccountList(){
        accounts = new LinkedList<>();
        totalStock = 0;
    }

    public void add(Account a){
        accounts.add(a);
    }

    public Account get(int index){
        return accounts.get(index);
    }

    public boolean contains(Account account){
        return accounts.contains(account);
    }

    public int getTotalStock(){
        return this.totalStock;
    }

    /**
     * The method to return the String that is writen in the File
     * @return String
     */
    public String toWrite(){
        StringBuilder s1 = new StringBuilder();
        for(Account a : accounts) {
            s1.append(a.toWrite()).append("\n");
        }
        return s1.toString();
    }

    /**
     * The method to read from a File
     * @param file - the file to read from
     * @throws FileNotFoundException - when the file cant be found
     */
    public void toRead(File file) throws FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            this.accounts.add(Account.toRead(scanner.nextLine()));
        }
        scanner.close();
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
     /**
      * to Update the totalStock
      */
    public void updateTotalStock(){
        int stock = 0;
        for(Account a : accounts){
            stock += a.getStock();
        }
    }

    public void calculateBierVerlies(int actualStock, LinkedList<Account> people){
        int verlies = totalStock - actualStock;
        int lossPP = verlies/people.size();
        for(Account a : people){
            for(Account b : accounts){
                if(a.equals(b)){
                    int newStock = b.getStock()-lossPP;
                    b.setStock(newStock);
                }
            }
        }
    }
}
