package contollers.extrasStuff;

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
import objects.JarfiniteitStuff.Jarf;
import objects.JarfiniteitStuff.JarfList;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

public class JarfGildeViewController implements Initializable {
    private AccountList accountList;
    private MainController mainController;
    private LinkedList<JarfList> jarfLists;


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
    public void setJarfLists(LinkedList<JarfList> jarfLists){
        this.jarfLists = jarfLists;
    }


    public void setData(){

        TableView<Jarf> tableView = new TableView<>();
        LinkedList<Jarf> temp = new LinkedList<>();
        for(JarfList j: jarfLists){
           temp.add(j.getLastJarf());
        }

        ObservableList<Jarf> o1 = FXCollections.observableArrayList(temp);

        tableView.setItems(o1);

        TableColumn<Jarf, String> nameColumn = new TableColumn<>("Naam");
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Jarf, Date> dateTableColumn = new TableColumn<>("Datum van Jarf");
        dateTableColumn.setCellValueFactory(new PropertyValueFactory("date"));


        Callback<TableColumn<Jarf, String>, TableCell<Jarf, String>> stringCellFactory = new Callback<TableColumn<Jarf, String>, TableCell<Jarf, String>>() {
            public TableCell<Jarf, String> call(TableColumn param) {
                return new TableCell<Jarf, String>() {

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
        Callback<TableColumn<Jarf, Date>, TableCell<Jarf, Date>> dateCellFactory = new Callback<TableColumn<Jarf, Date>, TableCell<Jarf, Date>>() {
            public TableCell<Jarf, Date> call(TableColumn param) {
                return new TableCell<Jarf, Date>() {

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
            qtyLabelList.get(i).setText(String.valueOf(jarfLists.get(i).nrJarfs()));
        }
    }

    public void jarfButton(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        AudioOutputOverHead.playAudio("src/main/resources/sounds/Cheer.wav");
        mainController.sleepTimerUpdate();
        for(int i = 0; i<6 ; i++){
            if(jarfButtonList.get(i).equals(button)){
                jarfLists.get(i).addJarf();
                break;
            }
        }
        setData();
        mainController.writeJarf();
    }

    public void misJarfButton(ActionEvent actionEvent){
        Button button = (Button) actionEvent.getSource();
        mainController.sleepTimerUpdate();
        for(int i = 0; i<6 ; i++){
            if(misJarfList.get(i).equals(button)){
                jarfLists.get(i).removeJarf();
                break;
            }
        }
        setData();
        mainController.writeJarf();
    }
}
