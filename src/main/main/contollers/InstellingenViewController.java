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
import java.util.LinkedList;
import java.util.ResourceBundle;

public class InstellingenViewController implements Initializable {
    private AccountList accountList;
    private MainController mainController;

    @FXML
    private Button newFeut;

    private LinkedList<Label> naamLabelList = new LinkedList<>();
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

    private LinkedList<Button> uitgestemdList = new LinkedList<>();
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

    private LinkedList<TextField> editFieldList = new LinkedList<>();
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

    private LinkedList<Button> editConfirmList = new LinkedList<>();
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

    @FXML
    private Button TransactionViewButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        naamLabelList.add(naamLabel0);
        naamLabelList.add(naamLabel1);
        naamLabelList.add(naamLabel2);
        naamLabelList.add(naamLabel3);
        naamLabelList.add(naamLabel4);
        naamLabelList.add(naamLabel5);

        uitgestemdList.add(uitgestemd0);
        uitgestemdList.add(uitgestemd1);
        uitgestemdList.add(uitgestemd2);
        uitgestemdList.add(uitgestemd3);
        uitgestemdList.add(uitgestemd4);
        uitgestemdList.add(uitgestemd5);

        editFieldList.add(editField0);
        editFieldList.add(editField1);
        editFieldList.add(editField2);
        editFieldList.add(editField3);
        editFieldList.add(editField4);
        editFieldList.add(editField5);

        editConfirmList.add(editConfirm0);
        editConfirmList.add(editConfirm1);
        editConfirmList.add(editConfirm2);
        editConfirmList.add(editConfirm3);
        editConfirmList.add(editConfirm4);
        editConfirmList.add(editConfirm5);
    }

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void setData(){
        for(int i = 0; i<6; i++){
            naamLabelList.get(i).setText(accountList.get(i).getName());
            if(accountList.get(i).getName().equals("")||accountList.get(i).getName().equals("B3nni3")){
                editConfirmList.get(i).setDisable(true);
            }
        }
    }

    public void newFeut(ActionEvent event){
        Account feut = new Account();
        accountList.add(feut);
        accountList.sort();
        mainController.write();
        setData();
    }

    public void makeOld(ActionEvent event) {
        for(int i = 0; i<6; i++){
            if(event.getSource().equals(uitgestemdList.get(i))){
                accountList.get(i).setOld();
            }
        }
        accountList.sort();
        mainController.write();
        setData();
    }

    public void editConfimPress(ActionEvent event) {
        for(int i = 0; i<6; i++){
            if(event.getSource().equals((editConfirmList.get(i)))){
                accountList.get(i).setName(editFieldList.get(i).getText());
                editFieldList.get(i).setText("");
            }
        }
        mainController.write();
        setData();
    }

    public void TransactionView(ActionEvent actionEvent) {
        mainController.transactionView();
    }
}
