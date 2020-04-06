package main.contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.objects.Account;
import main.objects.AccountList;

import java.net.URL;
import java.util.ResourceBundle;

public class InstellingenViewController implements Initializable {
    private AccountList accountList;
    private MainController mainController;

    @FXML
    private Button newFeut;

    @FXML
    private Label naamLabel0;
    @FXML
    private Label naamLabel1;
    @FXML
    private Label naamLabel2;
    @FXML
    private Label naamLabel3;
    @FXML
    private Label naamLabel4;
    @FXML
    private Label naamLabel5;

    @FXML
    private Button uitgestemd0;
    @FXML
    private Button uitgestemd1;
    @FXML
    private Button uitgestemd2;
    @FXML
    private Button uitgestemd3;
    @FXML
    private Button uitgestemd4;
    @FXML
    private Button uitgestemd5;


    @FXML
    private TextField editField0;
    @FXML
    private TextField editField1;
    @FXML
    private TextField editField2;
    @FXML
    private TextField editField3;
    @FXML
    private TextField editField4;
    @FXML
    private TextField editField5;


    @FXML
    private Button editConfirm0;
    @FXML
    private Button editConfirm1;
    @FXML
    private Button editConfirm2;
    @FXML
    private Button editConfirm3;
    @FXML
    private Button editConfirm4;
    @FXML
    private Button editConfirm5;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void setData(){
        naamLabel0.setText(accountList.get(0).getName());
        naamLabel1.setText(accountList.get(1).getName());
        naamLabel2.setText(accountList.get(2).getName());
        naamLabel3.setText(accountList.get(3).getName());
        naamLabel4.setText(accountList.get(4).getName());
        naamLabel5.setText(accountList.get(5).getName());
    }

    public void newFeut(ActionEvent event){
        Account feut = new Account();
        accountList.add(feut);
        accountList.sort();
        mainController.write();
    }

    public void makeOld(ActionEvent event) {
        Button pressedButton = (Button) event.getSource();
    }
}
