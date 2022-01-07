package objects.AccountStuff;

import java.util.Date;

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
}
