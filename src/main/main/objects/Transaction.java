package main.objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Transaction {
    private Account account;
    private int oldStock;
    private int newStock;
    private String type;
    private Date date;

    public Transaction(Account account, int oldStock, int newStock, String type){
        this.account = account;
        this.oldStock = oldStock;
        this.newStock = newStock;
        this.type = type;
        date = new Date();
    }

    public Transaction(Account account, int oldStock, int newStock, String type, Date date){
        this.account = account;
        this.oldStock = oldStock;
        this.newStock = newStock;
        this.type = type;
        this.date = date;
    }


    public Account getAccount() {
        return account;
    }

    public int getOldStock() {
        return oldStock;
    }

    public int getNewStock() {
        return newStock;
    }

    public String getType() {
        return this.type;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("H:mm dd MMMMM");
        return "Persoon: " + account.getName() +
                " - Oude Stand: " + oldStock +
                " - Nieuwe Stand: " + newStock +
                " - Knop gedrukt: " + type +
                " - Datum: " + format.format(date);
    }

    public String toWrite(){
        SimpleDateFormat format = new SimpleDateFormat("H:mm dd MMMMM");
        return account.getName() +
                " - " + oldStock +
                " - " + newStock +
                " - " + type +
                " - " + format.format(date);
    }

    public static Transaction toRead(String string, AccountList accounts) throws ParseException {
        Scanner scanner = new Scanner(string);
        scanner.useDelimiter(" - ");
        String accName = scanner.next();
        int oldStock = scanner.nextInt();
        int newStock = scanner.nextInt();
        String type = scanner.next();
        SimpleDateFormat format = new SimpleDateFormat("H:mm dd MMMMM");
        Date date = format.parse(scanner.next());
        Account account = null;

        for(Account a : accounts){
            if(accName.equals(a.getName())){
                account = a;
            }
        }
        scanner.close();
        return new Transaction(account, oldStock, newStock, type, date);
    }
}
