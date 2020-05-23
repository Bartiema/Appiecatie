package contollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import objects.AccountStuff.AccountList;

import java.net.URL;
import java.util.ResourceBundle;

public class LineChartController implements Initializable {
    private AccountList accountList;
    private MainController mainController;

    @FXML
    private LineChart lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController controller){
        this.mainController = controller;
    }
}
