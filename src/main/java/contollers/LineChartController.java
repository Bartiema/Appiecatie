package contollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.VBox;
import objects.AccountStuff.AccountList;
import objects.lineChartStuff.DataNodeList;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class LineChartController implements Initializable {

    private AccountList accountList;
    private MainController mainController;
    private LinkedList<DataNodeList> dataNodeLists;

    @FXML
    private VBox container;

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
        NumberAxis YAxis = new NumberAxis();
        YAxis.setLabel("Hoeveelheid bier gezopen");
        NumberAxis XAxis = new NumberAxis();
        XAxis.setLabel("Hoeveelste dag in maand");
        XAxis.setAutoRanging(false);
        XAxis.setTickUnit(1);
        XAxis.setUpperBound(30);
        YAxis.setLowerBound(1);

        LineChart lineChart = new LineChart(XAxis, YAxis);

        for(DataNodeList d : dataNodeLists){
            lineChart.getData().add(d.getXYChartSeries());
        }

        lineChart.setMinHeight(800);

        container.getChildren().add(lineChart);
    }
}
