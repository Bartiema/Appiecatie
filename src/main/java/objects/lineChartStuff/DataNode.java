package objects.lineChartStuff;

import objects.AccountStuff.Account;
import objects.AccountStuff.AccountList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DataNode {
    private Account account;
    private int totalDrank;
    private Date dateInMonth;

    public Account getAccount() {
        return account;
    }

    public int getTotalDrank() {
        return totalDrank;
    }

    public Date getDateInMonth() {
        return dateInMonth;
    }

    /**
     * Constructor for Read versions
     * @param account - The account that the DataNode Belongs too
     * @param totalDrank - The Amount Drank
     * @param dateInMonth - The date of the Month this node is made in
     */
    public DataNode(Account account, int totalDrank, Date dateInMonth) {
        this.account = account;
        this.totalDrank = totalDrank;
        this.dateInMonth = dateInMonth;
    }

    /**
     * Constructor for new DataNodes
     * @param account - The account that the DataNode Belongs too
     * @param totalDrank - The Amount Drank
     */
    public DataNode(Account account, int totalDrank) {
        this.account = account;
        this.totalDrank = totalDrank;
        this.dateInMonth = new Date();
    }

    public String toWrite() {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        return account.getName() + " - " +
                totalDrank + " - " +
                format.format(dateInMonth);
    }

    public DataNode toRead(String line, AccountList accountList) throws ParseException {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(" - ");

        String accName = scanner.next();
        int totalDrank = scanner.nextInt();
        String date = scanner.nextLine();

        Account account = null;

        for(Account a : accountList){
            if(accName.equals(a.getName())){
                account = a;
            }
        }

        SimpleDateFormat format = new SimpleDateFormat("dd");
        Date dateInMonth = format.parse(date);

        return new DataNode(account, totalDrank, dateInMonth);
    }

}
