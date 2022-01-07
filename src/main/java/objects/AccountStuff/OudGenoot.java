package objects.AccountStuff;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class OudGenoot extends Genoot{
    Date leftHouse;

    public OudGenoot(String name, int drankTotal, Date joinedHouse, Date birthDay, Date leftHouse) {
        super(name, drankTotal, joinedHouse, birthDay);
        this.leftHouse = leftHouse;
    }

    public Date getLeftHouse() {
        return leftHouse;
    }

    public void setLeftHouse(Date leftHouse) {
        this.leftHouse = leftHouse;
    }

    @Override
    public double calculatePerDay() {
        long diff = leftHouse.getTime() - getJoinedHouse().getTime();
        double nrDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        return getDrankTotal() / nrDays;
    }

    @Override
    public double calculatePerMonth() {
        int currentYear = leftHouse.getYear();
        int currentMonth = leftHouse.getMonth();

        int dateYear = getJoinedHouse().getYear();
        int dateMonth = getJoinedHouse().getMonth();

        int differenceYear = currentYear - dateYear;
        int differenceMonth = currentMonth - dateMonth;

        double totalMonth = differenceMonth + 12*differenceYear;
        return getDrankTotal()/totalMonth;
    }
}