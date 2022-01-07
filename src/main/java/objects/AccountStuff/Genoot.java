package objects.AccountStuff;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Genoot {
    private String name;
    private int drankTotal;
    private Date joinedHouse;
    private Date birthDay;
    private double drankPerDay;
    private double drankPerMonth;


    /**
     * Constructor for new genoot
     * @param birthDay the birthday of new housemate;
     */
    public Genoot(Date birthDay){
        this.name = "Feut";
        this.drankTotal = 0;
        this.joinedHouse = new Date();
        this.birthDay = birthDay;
        this.drankPerDay = 0;
        this.drankPerMonth = 0;
    }

    public Genoot(String name, int drankTotal, Date joinedHouse, Date birthDay){
        this.name = name;
        this.drankTotal = drankTotal;
        this.joinedHouse = joinedHouse;
        this.birthDay = birthDay;
        this.drankPerDay = calculatePerDay();
        this.drankPerMonth = calculatePerMonth();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrankTotal() {
        return drankTotal;
    }

    public void setDrankTotal(int drankTotal) {
        this.drankTotal = drankTotal;
    }

    public Date getJoinedHouse() {
        return joinedHouse;
    }

    public void setJoinedHouse(Date joinedHouse) {
        this.joinedHouse = joinedHouse;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public double getDrankPerDay() {
        return drankPerDay;
    }

    public double getDrankPerMonth() {
        return drankPerMonth;
    }

    public double calculatePerDay(){
        Date date = new Date();
        long diff = date.getTime() - getJoinedHouse().getTime();
        double nrDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        return this.drankTotal / nrDays;
    }

    public double calculatePerMonth(){
        Date date = new Date();

        int currentYear = date.getYear();
        int currentMonth = date.getMonth();

        int dateYear = getJoinedHouse().getYear();
        int dateMonth = getJoinedHouse().getMonth();

        int differenceYear = currentYear - dateYear;
        int differenceMonth = currentMonth - dateMonth;

        double totalMonth = differenceMonth + 12*differenceYear;
        return drankTotal/totalMonth;
    }
}
