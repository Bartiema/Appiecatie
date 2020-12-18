package contollers.instellingenStuff;

import contollers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import objects.AccountStuff.AccountList;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class BirthDayViewController implements Initializable {
    private AccountList accountList;
    private MainController mainController;

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        naamLabelList.add(naamLabel0);
        naamLabelList.add(naamLabel1);
        naamLabelList.add(naamLabel2);
        naamLabelList.add(naamLabel3);
        naamLabelList.add(naamLabel4);
        naamLabelList.add(naamLabel5);

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
        }
    }
}
