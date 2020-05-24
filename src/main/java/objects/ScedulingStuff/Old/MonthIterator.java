package objects.ScedulingStuff.Old;

import java.util.Calendar;
import java.util.Date;

public class MonthIterator {
    private Date date;
    private Calendar calendar = Calendar.getInstance();


    public MonthIterator(){
        date = new Date();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        if(calendar.getTime().after(date)){
            calendar.add(Calendar.MONTH, -1);
        }
    }

    public Date next() {
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }
}
