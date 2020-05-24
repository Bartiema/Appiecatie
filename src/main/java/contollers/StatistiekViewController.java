package contollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import objects.AccountStuff.Account;
import objects.AccountStuff.AccountList;
import objects.lineChartStuff.DataNodeList;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

public class StatistiekViewController implements Initializable {

    private AccountList accountList;
    private MainController mainController;
    private LinkedList<DataNodeList> dataNodeLists;

    @FXML
    private VBox container;
    @FXML
    private Button lineChartButton;
    @FXML
    private Button tabelButton;

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController controller){
        this.mainController = controller;
    }
    public void setDataNodeLists(LinkedList<DataNodeList> dataNodeLists) {
        this.dataNodeLists = dataNodeLists;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * the Method setting all the data in the ListView
     * first updates the data and then loads it into a observable list(aka just calling toString and making line seperation) then sets those Items
     */
    public void setData() throws IllegalAccessException {
        if(!container.getChildren().isEmpty()) container.getChildren().remove(0);

        tabelSet();
        }

    public void lineChart(ActionEvent actionEvent) {
        if(container.getChildren().get(0) instanceof LineChart) return;
        container.getChildren().remove(0);

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

        lineChart.setMinHeight(690);

        container.getChildren().add(lineChart);
    }

    public void tabelButton(ActionEvent actionEvent) throws IllegalAccessException {
        if(container.getChildren().get(0) instanceof TableView) return;
        container.getChildren().remove(0);

        tabelSet();
    }

    public void tabelSet() throws IllegalAccessException {

        TableView<Account> tableView = new TableView<>();

        ObservableList<Account> o1 = null;

        accountList.updateAll();
        Account blank = null;
        for(Account a : accountList)
            if (a.getName().equals(" ")) {
                blank = a;
                break;
            }
        if(blank!= null) {
            accountList.remove(blank);
            o1 = FXCollections.observableArrayList(accountList.getAll());
            accountList.add(blank);
            accountList.sort();
        }else throw new IllegalAccessException(" Blank not found");

        tableView.setItems(o1);

        TableColumn<Account, String> nameColumn = new TableColumn<>("Naam");
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Account, Integer> totalDrankColumn = new TableColumn<>("Totaal gezopen");
        totalDrankColumn.setCellValueFactory(new PropertyValueFactory("drankTotal"));
        TableColumn<Account, Integer> drankThisMonthColumn = new TableColumn<>("Deze maand gezopen");
        drankThisMonthColumn.setCellValueFactory(new PropertyValueFactory("drankThisMonth"));
        TableColumn<Account, Double> drankPerMonthColumn = new TableColumn<>("Gemiddeld per maand gezopen");
        drankPerMonthColumn.setCellValueFactory(new PropertyValueFactory("drankPerMonth"));
        TableColumn<Account, Double> drankPerDayColumn = new TableColumn<>("Gemiddeld per dag gezopen");
        drankPerDayColumn.setCellValueFactory(new PropertyValueFactory("drankPerDay"));
        TableColumn<Account, Date> dateJoinedColumn = new TableColumn<>("Datum in huis gekomen");
        dateJoinedColumn.setCellValueFactory(new PropertyValueFactory("joinedHouse"));
        TableColumn<Account, Date> dateLeftColumn = new TableColumn<>("Datum uit huis getreden");
        dateLeftColumn.setCellValueFactory(new PropertyValueFactory("leftHouse"));

        Callback<TableColumn<Account, String>, TableCell<Account, String>> stringCellFactory = new Callback<TableColumn<Account, String>, TableCell<Account, String>>() {
            public TableCell<Account, String> call(TableColumn param) {
                return new TableCell<Account, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setFont(new Font(24));
                            setText(item);
                        }
                    }
                };
            }
        };
        Callback<TableColumn<Account, Integer>, TableCell<Account, Integer>> intCellFactory = new Callback<TableColumn<Account, Integer>, TableCell<Account, Integer>>() {
            public TableCell<Account, Integer> call(TableColumn param) {
                return new TableCell<Account, Integer>() {

                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setFont(new Font(24));
                            setText(item.toString());
                            if(item % 24 == 0) {
                                this.setTextFill(Color.GREEN);
                                this.setText(item.toString() + " VO!");
                            }

                        }
                    }
                };
            }
        };
        Callback<TableColumn<Account, Double>, TableCell<Account, Double>> doubleCellFactory = new Callback<TableColumn<Account, Double>, TableCell<Account, Double>>() {
            public TableCell<Account, Double> call(TableColumn param) {
                return new TableCell<Account, Double>() {

                    @Override
                    public void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setFont(new Font(24));
                            setText(item.toString());
                            if(item % 24 == 0) {
                                this.setTextFill(Color.GREEN);
                                this.setText(item.toString() + " VO!");
                            }

                        }
                    }
                };
            }
        };
        Callback<TableColumn<Account, Date>, TableCell<Account, Date>> dateCellFactory = new Callback<TableColumn<Account, Date>, TableCell<Account, Date>>() {
            public TableCell<Account, Date> call(TableColumn param) {
                return new TableCell<Account, Date>() {

                    @Override
                    public void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setFont(new Font(18));
                            if(item == null){
                                setText("");
                            } else {
                                SimpleDateFormat format = new SimpleDateFormat("EEEE, d MMM, yyyy", new Locale("NL"));
                                setText(format.format(item));
                            }
                        }
                    }
                };
            }
        };

        nameColumn.setCellFactory(stringCellFactory);
        totalDrankColumn.setCellFactory(intCellFactory);
        drankThisMonthColumn.setCellFactory(intCellFactory);
        drankPerMonthColumn.setCellFactory(doubleCellFactory);
        drankPerDayColumn.setCellFactory(doubleCellFactory);
        dateJoinedColumn.setCellFactory(dateCellFactory);
        dateLeftColumn.setCellFactory(dateCellFactory);

        tableView.getColumns().setAll(nameColumn, totalDrankColumn, drankThisMonthColumn, drankPerMonthColumn, drankPerDayColumn, dateJoinedColumn, dateLeftColumn);

        tableView.setMinHeight(690);

        container.getChildren().add(tableView);
    }
}

