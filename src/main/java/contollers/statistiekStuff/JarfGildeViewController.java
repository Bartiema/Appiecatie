package contollers.statistiekStuff;

import contollers.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import objects.AccountStuff.AccountList;
import objects.AudioOutputOverHead;
import objects.JarfiniteitStuff.JarfStat;
import objects.JarfiniteitStuff.JarfStatList;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

public class JarfGildeViewController implements Initializable {
    private JarfStatList jarfStatList;
    private AccountList accountList;
    private MainController mainController;


    private LinkedList<Label> naamLabelList;
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


    private LinkedList<Button> jarfButtonList;
    @FXML
    private Button jarfButton0;
    @FXML
    private Button jarfButton1;
    @FXML
    private Button jarfButton2;
    @FXML
    private Button jarfButton3;
    @FXML
    private Button jarfButton4;
    @FXML
    private Button jarfButton5;


    private LinkedList<Label> qtyLabelList;
    @FXML
    private Label qtyLabel0;
    @FXML
    private Label qtyLabel1;
    @FXML
    private Label qtyLabel2;
    @FXML
    private Label qtyLabel3;
    @FXML
    private Label qtyLabel4;
    @FXML
    private Label qtyLabel5;

    private LinkedList<Button> misJarfList;
    @FXML
    private Button misJarf0;
    @FXML
    private Button misJarf1;
    @FXML
    private Button misJarf2;
    @FXML
    private Button misJarf3;
    @FXML
    private Button misJarf4;
    @FXML
    private Button misJarf5;


    @FXML
    private Pane container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        naamLabelList = new LinkedList<>();
        naamLabelList.add(naamLabel0);
        naamLabelList.add(naamLabel1);
        naamLabelList.add(naamLabel2);
        naamLabelList.add(naamLabel3);
        naamLabelList.add(naamLabel4);
        naamLabelList.add(naamLabel5);

        jarfButtonList = new LinkedList<>();
        jarfButtonList.add(jarfButton0);
        jarfButtonList.add(jarfButton1);
        jarfButtonList.add(jarfButton2);
        jarfButtonList.add(jarfButton3);
        jarfButtonList.add(jarfButton4);
        jarfButtonList.add(jarfButton5);

        qtyLabelList = new LinkedList<>();
        qtyLabelList.add(qtyLabel0);
        qtyLabelList.add(qtyLabel1);
        qtyLabelList.add(qtyLabel2);
        qtyLabelList.add(qtyLabel3);
        qtyLabelList.add(qtyLabel4);
        qtyLabelList.add(qtyLabel5);

        misJarfList = new LinkedList<>();
        misJarfList.add(misJarf0);
        misJarfList.add(misJarf1);
        misJarfList.add(misJarf2);
        misJarfList.add(misJarf3);
        misJarfList.add(misJarf4);
        misJarfList.add(misJarf5);
    }


    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController controller){
        this.mainController = controller;
    }
    public void setJarfStatList(JarfStatList jarfStatList){
        this.jarfStatList = jarfStatList;
    }

    public void setData(){
        jarfStatList.sort();
        TableView<JarfStat> tableView = new TableView<>();

        ObservableList<JarfStat> o1 = FXCollections.observableArrayList(jarfStatList.getIfThisMonth());

        tableView.setItems(o1);

        TableColumn<JarfStat, String> nameColumn = new TableColumn<>("Naam");
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<JarfStat, Date> dateTableColumn = new TableColumn<>("Datum van Jarf");
        dateTableColumn.setCellValueFactory(new PropertyValueFactory("date"));


        Callback<TableColumn<JarfStat, String>, TableCell<JarfStat, String>> stringCellFactory = new Callback<TableColumn<JarfStat, String>, TableCell<JarfStat, String>>() {
            public TableCell<JarfStat, String> call(TableColumn param) {
                return new TableCell<JarfStat, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setFont(new Font(30));
                            setText(item);
                        }
                    }
                };
            }
        };
        Callback<TableColumn<JarfStat, Date>, TableCell<JarfStat, Date>> dateCellFactory = new Callback<TableColumn<JarfStat, Date>, TableCell<JarfStat, Date>>() {
            public TableCell<JarfStat, Date> call(TableColumn param) {
                return new TableCell<JarfStat, Date>() {

                    @Override
                    public void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setFont(new Font(30));
                            if(item == null){
                                setText("");
                            } else {
                                SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", new Locale("NL"));
                                setText(format.format(item));
                            }
                        }
                    }
                };
            }
        };

        nameColumn.setCellFactory(stringCellFactory);
        dateTableColumn.setCellFactory(dateCellFactory);

        tableView.getColumns().setAll(nameColumn, dateTableColumn);
        tableView.setMinHeight(567);
        tableView.setMinWidth(597);

        container.getChildren().add(tableView);


        for(int i =0; i<6; i++){
            naamLabelList.get(i).setText(accountList.get(i).getName());
            if(jarfStatList.contains(accountList.get(i).getName())) qtyLabelList.get(i).setText(String.valueOf(jarfStatList.getOnName(accountList.get(i).getName()).getQuantity()));
            else qtyLabelList.get(i).setText("");
        }
    }

    public void jarfButton(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        AudioOutputOverHead.playAudio("src/main/resources/sounds/Cheer.wav");
        for (int i =0; i<6; i++){
            if(button.equals(jarfButtonList.get(i))){
                if(jarfStatList.contains(accountList.get(i).getName())){
                    jarfStatList.getOnName(accountList.get(i).getName()).update();
                    setData();
                    mainController.writeJarf();
                    return;
                }else{
                    jarfStatList.add(new JarfStat(accountList.get(i).getName()));
                    jarfStatList.sort();
                    setData();
                    mainController.writeJarf();
                    return;
                }
            }
        }
    }

    public void misJarfButton(ActionEvent actionEvent){
        Button button = (Button) actionEvent.getSource();
        for(int i =0; i<6; i++){
            if(button.equals(misJarfList.get(i))){
                jarfStatList.getOnName(accountList.get(i).getName()).reduce();
                setData();
                mainController.writeJarf();
                return;
            }
        }
    }
}
