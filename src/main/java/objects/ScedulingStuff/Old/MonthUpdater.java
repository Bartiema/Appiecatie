package objects.ScedulingStuff.Old;

import contollers.MainController;
import objects.AccountStuff.AccountList;

public class MonthUpdater {
    private MonthSceduler monthSceduler;
    private MainController mainController;
    private AccountList accountList;

    public MonthUpdater(MainController mainController, AccountList accounts){
        this.monthSceduler = new MonthSceduler();
        this.mainController = mainController;
        this.accountList = accounts;
    }

    public void start(){
        monthSceduler.schedule(new MonthTask(mainController, accountList), new MonthIterator());
    }
}
