package main.contollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import main.objects.AccountList;
import main.objects.Transaction;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionViewController implements Initializable {
    private AccountList accountList;
    private MainController mainController;

    @FXML
    private ListView<Transaction> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void setData() {
        ObservableList<Transaction> o1 = FXCollections.observableArrayList(accountList.getTransactionList().getAll());
        listView.setItems(o1);
    }

}
