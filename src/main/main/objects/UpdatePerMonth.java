package main.objects;

import main.contollers.MainController;

public class UpdatePerMonth implements Runnable{
    private MainController mainController;
    private AccountList accountList;

    @Override
    public void run() {
        accountList.updateMonth();
        mainController.write();
    }

    public UpdatePerMonth(MainController main, AccountList accounts) {
        this.mainController = main;
        this.accountList = accounts;
    }
}
