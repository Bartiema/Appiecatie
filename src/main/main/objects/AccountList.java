package main.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

public class AccountList implements Iterable<Account>{
    private LinkedList<Account> accounts;
    private int totalStock;

    public AccountList(){
        accounts = new LinkedList<>();
        totalStock = 0;
    }

    public List<Account> getAll(){
        return this.accounts;
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
        totalStock = stock;
    }

    /**
     * to Calculate bierverlies
     * @param actualStock - the actual stock
     * @param people - the people present
     */
    public void calculateBierVerlies(int actualStock, LinkedList<Account> people){
        int verlies = totalStock - actualStock;
        int lossPP = verlies/people.size();
        for(Account a : people){
            for(Account b : accounts){
                if(a.equals(b)){
                    for(int i = 0; i<lossPP; i++){
                        b.beerDrank();
                    }
                }
            }
        }
    }

    public void beerDrank(int index){
        totalStock -= 1;
        accounts.get(index).beerDrank();
    }

    public void misBeer(int index){
        totalStock += 1;
        accounts.get(index).misBeer();
    }

    public void misKrat(int index){
        totalStock -= 24;
        accounts.get(index).misCrate();
    }

    public void kratKoop(int index){
        totalStock += 24;
        accounts.get(index).boughtCrate();
    }

    /**
     * The method to allow Accountlist to be itarable
     * @return - The iterator
     */
    @Override
    public Iterator<Account> iterator() {
        return accounts.iterator();
    }

    /**
     * The method that sorts the List of Accounts
     * puts non old people on the first spots
     * ranked on assiniteit
     */
    public void sort(){
        LinkedList<Account> young = new LinkedList<>();
        LinkedList<Account> old = new LinkedList<>();

        for(Account a : accounts){
            if(a.isOld()) old.add(a);
            else if(!a.isOld()) young.add(a);
        }
        young.sort(Account::compareTo);
        old.sort(Account::compareTo);

        accounts = new LinkedList<>();
        accounts.addAll(young);
        accounts.addAll(old);
    }

    /**
     * simple tostring for the Listview stuff
     * @return - the String
     */
    @Override
    public String toString(){
      StringBuilder s = new StringBuilder();
        for(Account a : accounts){
          s.append(a.toString()).append("\n");
      }
        return s.toString();
    }

    public int size(){
        return accounts.size();
    }

    public void remove(Account account){
        accounts.remove(account);
    }
}
