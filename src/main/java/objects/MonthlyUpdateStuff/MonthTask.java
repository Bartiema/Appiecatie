package objects.MonthlyUpdateStuff;

import contollers.MainController;
import objects.AccountStuff.AccountList;

import java.util.TimerTask;

public class MonthTask implements Runnable {
    final Object lock = new Object();

    private MainController mainController;
    private AccountList accountList;

    int state = VIRGIN;
    static final int VIRGIN = 0;
    static final int SCHEDULED = 1;
    static final int CANCELLED = 2;

    TimerTask timerTask;

    public MonthTask(MainController mainController, AccountList accounts) {
        this.mainController = mainController;
        this.accountList = accounts;
    }

    public void run() {
        accountList.updateMonth();
        mainController.write();
    }

    public boolean cancel() {
        synchronized(lock) {
            if (timerTask != null) {
                timerTask.cancel();
            }
            boolean result = (state == SCHEDULED);
            state = CANCELLED;
            return result;
        }
    }

    public long scheduledExecutionTime() {
        synchronized(lock) {
            return timerTask == null ? 0 : timerTask.scheduledExecutionTime();
        }
    }
}
