package main.contollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.objects.AccountList;

import java.net.URL;
import java.util.ResourceBundle;

public class StatistiekViewController implements Initializable {

    private AccountList accountList;
    private MainController mainController;

    @FXML
    public TableView statistiekTable;
    @FXML
    public TableColumn nameTable;

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController controller){
        this.mainController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
