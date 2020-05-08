package main.objects.MonthlyUpdateStuff;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MonthSceduler {

    class SchedulerTimerTask extends TimerTask {
        private MonthTask monthTask;
        private MonthIterator iterator;
        public SchedulerTimerTask(MonthTask monthTask,
                                  MonthIterator iterator) {
            this.monthTask = monthTask;
            this.iterator = iterator;
        }
        public void run() {
            monthTask.run();
            reschedule(monthTask, iterator);
        }
    }

    private final Timer timer = new Timer();

    public MonthSceduler() {
    }

    public void cancel() {
        timer.cancel();
    }

    public void schedule(MonthTask monthTask,
                         MonthIterator iterator) {

        Date time = iterator.next();
        if (time == null) {
            monthTask.cancel();
        } else {
            synchronized(monthTask.lock) {
                if (monthTask.state != MonthTask.VIRGIN) {
                    throw new IllegalStateException("Task already scheduled " + "or cancelled");
                }
                monthTask.state = MonthTask.SCHEDULED;
                monthTask.timerTask =
                        new SchedulerTimerTask(monthTask, iterator);
                timer.schedule(monthTask.timerTask, time);
            }
        }
    }

    private void reschedule(MonthTask monthTask,
                            MonthIterator iterator) {

        Date time = iterator.next();
        if (time == null) {
            monthTask.cancel();
        } else {
            synchronized(monthTask.lock) {
                if (monthTask.state != MonthTask.CANCELLED) {
                    monthTask.timerTask =
                            new SchedulerTimerTask(monthTask, iterator);
                    timer.schedule(monthTask.timerTask, time);
                }
            }
        }
    }
}
