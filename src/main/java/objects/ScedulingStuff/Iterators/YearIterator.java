package objects.ScedulingStuff.Iterators;

import java.util.Calendar;
import java.util.Date;

public class YearIterator {
    private Date date;
    private Calendar calendar = Calendar.getInstance();

    public YearIterator(){
        date = new Date();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        if(calendar.getTime().after(date)){
            calendar.add(Calendar.YEAR, -1);
        }
    }
    public Date next() {
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }
}
