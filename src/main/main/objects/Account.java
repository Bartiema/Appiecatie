package main.objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Account implements Comparable<Account> {
    private String name;
    private int stock;
    private int drankTotal;
    private Date created;
    private double drankPerDay;
    private double drankPerMonth;
    private boolean isOld;

    public Account(){
        this.name = "Feut";
        this.stock = 0;
        this.drankTotal = 0;
        this.drankPerDay = 0;
        this.drankPerMonth = 0;
        this.created = new Date();
        this.isOld = false;
    }

    /**
     * Constructor for new Account
     * @param name
     */
    public Account(String name){
        this.name = name;
        this.stock = 0;
        this.drankTotal = 0;
        this.drankPerDay = 0;
        this.drankPerMonth = 0;
        this.created = new Date();
        this.isOld = false;
    }

    /**
     * Constructor for read (existing) Accounts
     * @param name - the Name of the Account
     * @param stock - Amount of beers
     * @param drankTotal - Total beer drank
     * @param created - Date of account Creation
     * @param isOld - Is it an old Housemate (if this is true this Account will only be displayed in Statistics)
     */
    public Account(String name, int stock, int drankTotal, Date created, boolean isOld){
        this.name = name;
        this.stock = stock;
        this.created = created;
        this.drankTotal = drankTotal;
        this.drankPerDay = calculatePerDay(drankTotal, created);
        this.drankPerMonth = calculatePerMonth(drankTotal, created);
        this.isOld = isOld;
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

    public Date getCreated() {
        return created;
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

    /**
     * a method to return the String to be Written to file
     * @return String
     */
    public String toWrite(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return name + " - "
                + stock + " - "
                + drankTotal + " - "
                + dateFormat.format(created) + " - "
                + isOld;
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

        boolean isOld = true;

        String isOldS = scanner.nextLine();
        if(isOldS.equals(" - false")) isOld = false;

        return new Account(name, stock, drankTotal, date, isOld);
    }

    /**
     * A calculation method to calculate the amount of beers drank per day
     * @param drankTotal - the Total number of beers drank by the account
     * @param date - the date of creation of the account
     * @return the Double of the value
     */
    public static double calculatePerDay(int drankTotal, Date date){
       Date currentDate = new Date();
       int currentYear = currentDate.getYear();
       int currentMonth = currentDate.getMonth();
       int currentDay = currentDate.getDay();

       int dateYear = date.getYear();
       int dateMonth = date.getMonth();
       int dateDay = date.getMonth();

       int differenceYear = currentYear - dateYear;
       int differenceMonth = currentMonth - dateMonth;
       int differenceDay = currentDay - dateDay;

       double TotalDays = differenceDay + 30.42*differenceMonth + 365.25*differenceYear;
        return drankTotal/TotalDays;
    }

    /**
     * a calculation method to calculate the amount of beers drank per month
     * @param drankTotal - the Total number of beers drank by the account
     * @param date - the date of creation of the account
     * @return the Double of the value
     */
    public static double calculatePerMonth(int drankTotal, Date date){
        Date currentDate = new Date();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonth();

        int dateYear = date.getYear();
        int dateMonth = date.getMonth();

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
    }
    /**
     * A method to update a account when he or some other idiot missclicked
     * When this method is called the writer should also update the file
     */
    public void misBeer(){
        stock += 1;
        drankTotal -= 1;
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
    }

    /**
     * For updating the stats
     * This method is called from AccountList when something else is done
     * @param date - the date this method is called on for the updates
     */
    public void update(Date date){
        drankPerDay = calculatePerDay(drankTotal, date);
        drankPerMonth = calculatePerMonth(drankTotal, date);
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
                isOld == account.isOld &&
                name.equals(account.name) &&
                created.equals(account.created);
    }

    /**
     * for Sorting
     * @param o - the compaired object
     * @return - int for sorting
     */
    @Override
    public int compareTo(Account o) {
        return this.created.compareTo(o.created);
    }

    /**
     * Simple toString for ListView
     * @return - the String
     */
    @Override
    public String toString(){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return name + " - " +
                drankTotal + " - " +
                drankPerMonth + " - " +
                drankPerDay + " - " +
                format.format(created);
    }
}
