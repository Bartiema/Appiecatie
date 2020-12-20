package contollers.extrasStuff;

import contollers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import objects.AccountStuff.AccountList;
import objects.JarfiniteitStuff.JarfList;
import objects.lineChartStuff.DataNodeList;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class JarfGraphViewController implements Initializable {

    private AccountList accountList;
    private MainController mainController;
    private LinkedList<JarfList> jarfLists;

    @FXML
    private VBox container;

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController controller){
        this.mainController = controller;
    }
    public void setJarfLists(LinkedList<JarfList> jarfLists) {
        this.jarfLists = jarfLists;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(){
        if(!container.getChildren().isEmpty()) container.getChildren().remove(0);

        NumberAxis YAxis = new NumberAxis();
        YAxis.setLabel("Hoeveelheid keer geJarft");
        CategoryAxis XAxis = new CategoryAxis();
        XAxis.setLabel("Dag in week");
        YAxis.setLowerBound(0);

        BarChart barChart = new BarChart(XAxis, YAxis);

        for(JarfList j : jarfLists){
            XYChart.Series series = j.getXYChartSeries();
            barChart.getData().add(series);
        }

        barChart.setMinHeight(670);
        container.getChildren().add(barChart);
    }
}
