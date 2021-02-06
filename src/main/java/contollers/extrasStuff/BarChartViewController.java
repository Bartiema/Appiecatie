package contollers.extrasStuff;

import com.sun.javafx.binding.StringFormatter;
import contollers.MainController;
import javafx.css.converter.StringConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import objects.AccountStuff.AccountList;
import objects.ScedulingStuff.Iterators.MonthIterator;
import objects.lineChartStuff.DataNodeList;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
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
    public void setYearNodeLists(LinkedList<DataNodeList> yearNodeLists) {
        this.yearNodeLists = yearNodeLists;
    }

    public void setData() {
        int currYear = new Date().getYear();
        yearSlider.setMax(currYear);
        yearSlider.setMin(121.0);
        yearSlider.setMajorTickUnit(2.0);
        yearSlider.setMinorTickCount(1);
        yearSlider.setBlockIncrement(1);
        yearSlider.setValue(yearSlider.getMax());
        yearSlider.setSnapToTicks(true);
        makeBarChart(yearNodeLists);
    }

    public void makeBarChart(LinkedList<DataNodeList> list){
        if(!container.getChildren().isEmpty()) container.getChildren().remove(0);

        NumberAxis YAxis = new NumberAxis();
        YAxis.setLabel("Hoeveelheid Pinten getanked in de maand");
        CategoryAxis XAxis = new CategoryAxis();
        XAxis.setLabel("Maand");
        YAxis.setLowerBound(0);

        BarChart barChart = new BarChart(XAxis, YAxis);

        SimpleDateFormat format = new SimpleDateFormat("MMMM", new Locale("NL"));
        SimpleDateFormat mat = new SimpleDateFormat("D");


        for(DataNodeList d : list){
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName(d.getDataOwner().getName());
            MonthIterator monthIterator = new MonthIterator(11);
            int prevMonths = 0;
            for (int i = 0; i < 12; i++) {
                Date date = monthIterator.next();
                if(d.getDrankOnDate(Integer.parseInt(mat.format(date))) == 0) {
                    prevMonths = 0;
                    series.getData().add(new XYChart.Data<String, Integer>(format.format(date) , 0));
                } else {
                    int drankThatMonth = d.getDrankOnDate(Integer.parseInt(mat.format(date))) - prevMonths;
                    prevMonths = d.getDrankOnDate(Integer.parseInt(mat.format(date)));
                    series.getData().add(new XYChart.Data<String, Integer>(format.format(date), drankThatMonth));
                }
            }
            barChart.getData().add(series);
        }


        barChart.setMinHeight(670);
        container.getChildren().add(barChart);    }

    public void yearUpdate() throws FileNotFoundException, ParseException {
        int currYear = new Date().getYear();
        System.out.println(yearSlider.getValue());
        if(yearSlider.getValue() == currYear) makeBarChart(yearNodeLists);
        else {
            File yearFile = new File("src/main/resources/files/ZuipStats/" + (int)(1900 + yearSlider.getValue()) + "-ZuipStats.txt");
            LinkedList<DataNodeList> yearList = DataNodeList.toRead(accountList, yearFile);
            makeBarChart(yearList);
        }
    }
}
