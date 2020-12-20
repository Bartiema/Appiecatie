package contollers.extrasStuff;

import contollers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import objects.AccountStuff.AccountList;
import objects.lineChartStuff.DataNodeList;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class LineChartViewController implements Initializable {

    private AccountList accountList;
    private MainController mainController;
    private LinkedList<DataNodeList> dataNodeLists;

    @FXML
    private VBox container;
    @FXML
    private Button updateButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController controller){
        this.mainController = controller;
    }
    public void setDataNodeLists(LinkedList<DataNodeList> dataNodeLists) {
        this.dataNodeLists = dataNodeLists;
    }

    public void setData() {
        if(!container.getChildren().isEmpty()) container.getChildren().remove(0);

        NumberAxis YAxis = new NumberAxis();
        YAxis.setLabel("Hoeveelheid bier gezopen");
        NumberAxis XAxis = new NumberAxis();
        XAxis.setLabel("Hoeveelste dag in maand");
        XAxis.setAutoRanging(false);
        XAxis.setTickUnit(1);
        XAxis.setUpperBound(31);
        YAxis.setLowerBound(1);

        LineChart lineChart = new LineChart(XAxis, YAxis);

        for(DataNodeList d : dataNodeLists){
            XYChart.Series series = d.getXYChartSeries();
            lineChart.getData().add(series);

        }

        lineChart.setMinHeight(670);


        container.getChildren().add(lineChart);
    }

    public void update(ActionEvent event) {
        for(DataNodeList d : dataNodeLists) d.update();
        mainController.sleepTimerUpdate();
        setData();
    }
}
