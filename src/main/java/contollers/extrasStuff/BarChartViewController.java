package contollers.extrasStuff;

import contollers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import objects.AccountStuff.AccountList;
import objects.JarfiniteitStuff.JarfList;
import objects.ScedulingStuff.Iterators.MonthIterator;
import objects.lineChartStuff.DataNodeList;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

public class BarChartViewController implements Initializable {
    private AccountList accountList;
    private MainController mainController;
    private LinkedList<DataNodeList> yearNodeLists;


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
    public void setYearNodeLists(LinkedList<DataNodeList> yearNodeLists) {
        this.yearNodeLists = yearNodeLists;
    }

    public void setData(){
        if(!container.getChildren().isEmpty()) container.getChildren().remove(0);

        NumberAxis YAxis = new NumberAxis();
        YAxis.setLabel("Hoeveelheid Pinten getanked in de maand");
        CategoryAxis XAxis = new CategoryAxis();
        XAxis.setLabel("Maand");
        YAxis.setLowerBound(0);

        BarChart barChart = new BarChart(XAxis, YAxis);

        SimpleDateFormat format = new SimpleDateFormat("MMMM", new Locale("NL"));
        SimpleDateFormat mat = new SimpleDateFormat("D");


        for(DataNodeList d : yearNodeLists){
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName(d.getDataOwner().getName());
            MonthIterator monthIterator = new MonthIterator(11);
            int prevMonths = 0;
            for (int i = 0; i < 12; i++) {
                Date date = monthIterator.next();
                int drankThatMonth = d.getDrankOnDate(Integer.parseInt(mat.format(date))) - prevMonths;
                prevMonths = d.getDrankOnDate(Integer.parseInt(mat.format(date)));
                series.getData().add(new XYChart.Data<String, Integer>(format.format(date) , drankThatMonth));
            }
            barChart.getData().add(series);
        }


        barChart.setMinHeight(670);
        container.getChildren().add(barChart);
    }

    public void update(ActionEvent actionEvent) {
    }
}
