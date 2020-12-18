package objects.ScedulingStuff.Old;

import java.util.Calendar;
import java.util.Date;

public class DayIterator {
    private Date date;
    private Calendar calendar = Calendar.getInstance();


    public DayIterator(){
        date = new Date();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        if(calendar.getTime().after(date)){
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
    }

    public Date next() {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
}
