package objects.AccountStuff;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Account implements Comparable<Account> {
    private String name;
    private int stock;
    private int drankTotal;
    private Date joinedHouse;
    private double drankPerDay;
    private double drankPerMonth;
    private int drankThisMonth;
    private boolean isOld;
    private Date leftHouse;


    public Account(){
        this.name = "Feut";
        this.stock = 0;
        this.drankTotal = 0;
        this.drankPerDay = 0;
        this.drankPerMonth = 0;
        this.joinedHouse = new Date();
        this.isOld = false;
        this.drankThisMonth = 0;
        this.leftHouse = null;
    }

    /**
     * Constructor for new Account
     * @param name - the name of the Account
     */
    public Account(String name){
        this.name = name;
        this.stock = 0;
        this.drankTotal = 0;
        this.drankPerDay = 0;
        this.drankPerMonth = 0;
        this.joinedHouse = new Date();
        this.isOld = false;
        this.drankThisMonth = 0;
        this.leftHouse = null;
    }

    /**
     * Constructor for read (existing) Accounts
     * @param name - the Name of the Account
     * @param stock - Amount of beers
     * @param drankTotal - Total beer drank
     * @param created - Date of account Creation
     * @param isOld - Is it an old Housemate (if this is true this Account will only be displayed in Statistics)
     * @param drankThisMonth - the number of beers drank in the current month
     */
    public Account(String name, int stock, int drankTotal, Date created, boolean isOld, int drankThisMonth){
        this.name = name;
        this.stock = stock;
        this.joinedHouse = created;
        this.drankTotal = drankTotal;
        this.drankPerDay = calculatePerDay(drankTotal);
        this.drankPerMonth = calculatePerMonth(drankTotal);
        this.isOld = isOld;
        this.drankThisMonth = drankThisMonth;
        this.leftHouse = null;
    }

    /**
     * Constructor for read (existing) Accounts
     * @param name - the Name of the Account
     * @param stock - Amount of beers
     * @param drankTotal - Total beer drank
     * @param created - Date of account Creation
     * @param isOld - Is it an old Housemate (if this is true this Account will only be displayed in Statistics)
     * @param drankThisMonth - the number of beers drank in the current month
     * @param left - The date the housemate left
     */
    public Account(String name, int stock, int drankTotal, Date created, boolean isOld, int drankThisMonth, Date left){
        this.name = name;
        this.stock = stock;
        this.joinedHouse = created;
        this.drankTotal = drankTotal;
        this.drankPerDay = calculatePerDay(drankTotal);
        this.drankPerMonth = calculatePerMonth(drankTotal);
        this.isOld = isOld;
        this.drankThisMonth = drankThisMonth;
        this.leftHouse = left;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public int getDrankTotal() {
        return drankTotal;
    }

    public Date getJoinedHouse() {
        return joinedHouse;
    }

    public double getDrankPerDay() {
        return drankPerDay;
    }

    public double getDrankPerMonth() {
        return drankPerMonth;
    }

    public boolean isOld() {
        return isOld;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getDrankThisMonth() {
        return this.drankThisMonth;
    }

    public void setDrankThisMonth(int i) {
        this.drankThisMonth = i;
    }

    public Date getLeftHouse() {
        return this.leftHouse;
    }

    /**
     * a method to return the String to be Written to file
     * @return String
     */
    public String toWrite(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String s = name + " - "
                + stock + " - "
                + drankTotal + " - "
                + dateFormat.format(joinedHouse) + " - "
                + drankThisMonth + " - "
                + isOld;
        if(isOld) {
            s += " - " + dateFormat.format(leftHouse);
        }
        return s;
    }

    /**
     * The method to allow for the extraction of a Account from a line
     * @param line - String containing the data for an account
     * @return Account
     */
    public static Account toRead(String line) throws ParseException {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(" - ");

        String name = scanner.next();
        int stock = scanner.nextInt();
        int drankTotal = scanner.nextInt();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse(scanner.next());

        int drankThisMonth = scanner.nextInt();

        boolean isOld = true;

        String isOldS = scanner.next();
        if(isOldS.equals("false")) isOld = false;

        if(isOld) {
            Date left = format.parse(scanner.next());
            scanner.close();
            return new Account(name, stock, drankTotal, date, isOld, drankThisMonth, left);
        } else {
            scanner.close();
            return new Account(name, stock, drankTotal, date, isOld, drankThisMonth);
        }
    }

    /**
     * A calculation method to calculate the amount of beers drank per day
     * @param drankTotal - the Total number of beers drank by the account
     * @return the Double of the value
     */
    public double calculatePerDay(int drankTotal){
        Date date = null;
        if(isOld) date = leftHouse;
        else date = new Date();

        long diff = date.getTime() - getJoinedHouse().getTime();
        double nrDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        return drankTotal / nrDays;
    }

    /**
     * a calculation method to calculate the amount of beers drank per month
     * @param drankTotal - the Total number of beers drank by the account
     * @return the Double of the value
     */
    public double calculatePerMonth(int drankTotal){
        Date date = null;
        if(isOld) date = leftHouse;
        else date = new Date();

        int currentYear = date.getYear();
        int currentMonth = date.getMonth();

        int dateYear = getJoinedHouse().getYear();
        int dateMonth = getJoinedHouse().getMonth();

        int differenceYear = currentYear - dateYear;
        int differenceMonth = currentMonth - dateMonth;

        double totalMonth = differenceMonth + 12*differenceYear;
        return drankTotal/totalMonth;
    }

    /**
     * A method to update a account when he drank a beer
     * When this method is called the writer should also update the file
     */
    public void beerDrank(){
        stock -= 1;
        drankTotal += 1;
        drankThisMonth +=1;
    }
    /**
     * A method to update a account when he or some other idiot missclicked
     * When this method is called the writer should also update the file
     */
    public void misBeer(){
        stock += 1;
        drankTotal -= 1;
        drankThisMonth -= 1;
    }
    /**
     * A method to update a account when he bought a crate
     * When this method is called the writer should also update the file
     */
    public void boughtCrate(){
        stock += 24;
    }
    /**
     * A method to update a account when he or some other idiot missclicked
     * When this method is called the writer should also update the file
     */
    public void misCrate(){
        stock -= 24;
    }

    /**
     * for making someone An Old housemate
     * When this method is called the writer should also update the file
     */
    public void setOld(){
        this.isOld = true;
        this.stock = 0;
        this.leftHouse = new Date();
    }

    /**
     * For updating the stats
     * This method is called from AccountList when something else is done
     */
    public void update(){
        drankPerDay = calculatePerDay(drankTotal);
        drankPerMonth = calculatePerMonth(drankTotal);
    }

    /**
     * SelfExplanatory
     * @param o - the compaired object
     * @return - boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return stock == account.stock &&
                drankTotal == account.drankTotal &&
                drankThisMonth == account.drankThisMonth &&
                isOld == account.isOld &&
                name.equals(account.name) &&
                joinedHouse.equals(account.joinedHouse);
    }

    /**
     * for Sorting
     * @param o - the compaired object
     * @return - int for sorting
     */
    @Override
    public int compareTo(Account o) {
        return this.joinedHouse.compareTo(o.joinedHouse);
    }

    /**
     * Simple toString for ListView aka Statisticks
     * @return - the String
     */
    @Override
    public String toString(){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("######.##");
        return name + " - " +
                drankTotal + " - " +
                drankThisMonth + " - " +
                decimalFormat.format(drankPerMonth) + " - " +
                decimalFormat.format(drankPerDay) + " - " +
                format.format(joinedHouse);
    }
}
