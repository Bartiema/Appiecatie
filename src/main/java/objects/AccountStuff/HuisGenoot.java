package objects.AccountStuff;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class HuisGenoot extends Genoot{
    private int stock;
    private TransactionList transactionList;


    /**
     * Constructor for a completely new Huisgenoot
     * @param birthDay date of birth
     */
    public HuisGenoot(Date birthDay) {
        super(birthDay);
        this.stock = 0;
        this.transactionList = new TransactionList();
    }

    /**
     * Constructor for a huisgenoot from file
     * transactionlist should be set when reading from all files
     * @param name name
     * @param drankTotal total nr of beers drank
     * @param joinedHouse date of joining house
     * @param birthDay birthday
     * @param stock stock of this person
     */
    public HuisGenoot(String name, int drankTotal, Date joinedHouse, Date birthDay, int stock) {
        super(name, drankTotal, joinedHouse, birthDay);
        this.stock = stock;
    }

    public int getStock(){
        return this.stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public TransactionList getTransactionList(){
        return this.transactionList;
    }

    public void setTransactionList(TransactionList transactionList){
        this.transactionList = transactionList;
    }

    public HuisGenoot toRead(String line) throws ParseException {
        Scanner scan = new Scanner(line).useDelimiter(" - ");
        HuisGenoot huisGenoot = (HuisGenoot) super.toRead(scan);
        huisGenoot.setStock(scan.nextInt());
        scan.close();
        return huisGenoot;
    }

    public String toWrite() {
        return super.toWrite() + " - " + this.stock;
    }
}
