package main.contollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import main.objects.Account;
import main.objects.AccountList;

import java.net.URL;
import java.util.ResourceBundle;

public class StatistiekViewController implements Initializable {

    private AccountList accountList;
    private MainController mainController;

    @FXML
    private ListView<Account> listView;

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController controller){
        this.mainController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * the Method setting all the data in the ListView
     * first updates the data and then loads it into a observable list(aka just calling toString and making line seperation) then sets those Items
     */
    public void setData() throws IllegalAccessException {
        accountList.updateAll();


        Account blank = null;
        for(Account a : accountList){
            if (a.getName().equals(" ")){
                blank = a;
                break;
            }
        }

        if(blank!= null) {
            accountList.remove(blank);

            ObservableList<Account> o1 = FXCollections.observableArrayList(accountList.getAll());

            accountList.add(blank);
            accountList.sort();

            listView.setItems(o1);
        }else{
            throw new IllegalAccessException(" Blank not found");
        }
        }
    }

