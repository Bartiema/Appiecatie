package contollers.extrasStuff;

import contollers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import objects.AccountStuff.AccountList;
import objects.lineChartStuff.DataNode;
import objects.lineChartStuff.DataNodeList;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class LineChartViewController implements Initializable {

    private AccountList accountList;
    private MainController mainController;
    private LinkedList<DataNodeList> monthNodeLists;
    private LinkedList<DataNodeList> yearNodeLists;
    private boolean month;

    @FXML
    private VBox container;
    @FXML
    private Button updateButton;
    @FXML
    private Slider yearSlider;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController controller){
        this.mainController = controller;
    }
    public void setMonthNodeLists(LinkedList<DataNodeList> monthNodeLists) {
        this.monthNodeLists = monthNodeLists;
    }
    public void setYearNodeLists(LinkedList<DataNodeList> yearNodeLists) {
        this.yearNodeLists = yearNodeLists;
    }

    public void setDataMonth() {
        if(!container.getChildren().isEmpty()) container.getChildren().remove(0);
        month = true;
        yearSlider.setDisable(true);
        yearSlider.setOpacity(0);
        NumberAxis YAxis = new NumberAxis();
        YAxis.setLabel("Hoeveelheid bier gezopen");
        NumberAxis XAxis = new NumberAxis();
        XAxis.setLabel("Hoeveelste dag in maand");
        XAxis.setAutoRanging(false);
        XAxis.setTickUnit(1);
        XAxis.setUpperBound(31);
        YAxis.setLowerBound(1);

        LineChart lineChart = new LineChart(XAxis, YAxis);

        for(DataNodeList d : monthNodeLists){
            XYChart.Series series = d.getXYChartSeries();
            lineChart.getData().add(series);

        }

        lineChart.setMinHeight(670);


        container.getChildren().add(lineChart);
    }

    public void setDataYear() {
        if(!container.getChildren().isEmpty()) container.getChildren().remove(0);
        month = false;

        yearSlider.setDisable(false);
        yearSlider.setOpacity(100);
        int currYear = new Date().getYear();
        yearSlider.setMax(currYear);
        yearSlider.setMin(121.0);
        yearSlider.setMajorTickUnit(2.0);
        yearSlider.setMinorTickCount(1);
        yearSlider.setBlockIncrement(1);
        yearSlider.setValue(yearSlider.getMax());

        NumberAxis YAxis = new NumberAxis();
        YAxis.setLabel("Hoeveelheid bier gezopen");
        NumberAxis XAxis = new NumberAxis();
        XAxis.setLabel("Hoeveelste dag in Jaar");
        XAxis.setAutoRanging(false);
        XAxis.setTickUnit(1);
        XAxis.setUpperBound(366);
        YAxis.setLowerBound(1);

        LineChart lineChart = new LineChart(XAxis, YAxis);

        for(DataNodeList d : yearNodeLists){
            XYChart.Series series = d.getXYChartSeries();
            lineChart.getData().add(series);

        }

        lineChart.setMinHeight(670);


        container.getChildren().add(lineChart);
    }

    public void update(ActionEvent event) {
        mainController.sleepTimerUpdate();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("D");

        if(month){
            for(DataNodeList d : monthNodeLists) d.update(d.getDataOwner().getDrankThisMonth(), date.getDate());
            setDataMonth();
        }
        else {
            for(DataNodeList d : yearNodeLists) d.update(d.getDataOwner().getDrankThisYear(), Integer.parseInt(format.format(date)));
            setDataYear();
        }
    }

    public void yearUpdate() throws FileNotFoundException, ParseException {
        int currYear = new Date().getYear();
        System.out.println(yearSlider.getValue());
        if(!container.getChildren().isEmpty()) container.getChildren().remove(0);
        if(yearSlider.getValue() == currYear+1) setDataYear();
        else {
            NumberAxis YAxis = new NumberAxis();
            YAxis.setLabel("Hoeveelheid bier gezopen");
            NumberAxis XAxis = new NumberAxis();
            XAxis.setLabel("Hoeveelste dag in Jaar");
            XAxis.setAutoRanging(false);
            XAxis.setTickUnit(1);
            XAxis.setUpperBound(366);
            YAxis.setLowerBound(1);

            File yearFile = new File("src/main/resources/files/ZuipStats/" + (int)(1900 + yearSlider.getValue()) + "-ZuipStats.txt");
            LinkedList<DataNodeList> lists = DataNodeList.toRead(accountList, yearFile);

            LineChart lineChart = new LineChart(XAxis, YAxis);

            for (DataNodeList d : lists) {
                XYChart.Series series = d.getXYChartSeries();
                lineChart.getData().add(series);

            }

            lineChart.setMinHeight(670);


            container.getChildren().add(lineChart);
        }
    }
}
