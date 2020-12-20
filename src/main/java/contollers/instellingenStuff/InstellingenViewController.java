package contollers.instellingenStuff;

import contollers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import objects.AccountStuff.Account;
import objects.AccountStuff.AccountList;
import objects.AudioOutputOverHead;
import objects.JarfiniteitStuff.JarfList;
import objects.lineChartStuff.DataNode;
import objects.lineChartStuff.DataNodeList;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InstellingenViewController implements Initializable {

    private AccountList accountList;
    private MainController mainController;
    private LinkedList<DataNodeList> monthNodeLists;
    private LinkedList<DataNodeList> yearNodeLists;
    private LinkedList<JarfList> jarfLists;

    @FXML
    private Button newFeut;
    @FXML
    private Button birthDayButton;

    private LinkedList<Label> naamLabelList = new LinkedList<>();
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

    private LinkedList<Button> uitgestemdList = new LinkedList<>();
    @FXML
    private Button uitgestemd0;
    @FXML
    private Button uitgestemd1;
    @FXML
    private Button uitgestemd2;
    @FXML
    private Button uitgestemd3;
    @FXML
    private Button uitgestemd4;
    @FXML
    private Button uitgestemd5;

    private LinkedList<TextField> editFieldList = new LinkedList<>();
    @FXML
    private TextField editField0;
    @FXML
    private TextField editField1;
    @FXML
    private TextField editField2;
    @FXML
    private TextField editField3;
    @FXML
    private TextField editField4;
    @FXML
    private TextField editField5;

    private LinkedList<Button> editConfirmList = new LinkedList<>();
    @FXML
    private Button editConfirm0;
    @FXML
    private Button editConfirm1;
    @FXML
    private Button editConfirm2;
    @FXML
    private Button editConfirm3;
    @FXML
    private Button editConfirm4;
    @FXML
    private Button editConfirm5;

    @FXML
    private Button TransactionViewButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        naamLabelList.add(naamLabel0);
        naamLabelList.add(naamLabel1);
        naamLabelList.add(naamLabel2);
        naamLabelList.add(naamLabel3);
        naamLabelList.add(naamLabel4);
        naamLabelList.add(naamLabel5);

        uitgestemdList.add(uitgestemd0);
        uitgestemdList.add(uitgestemd1);
        uitgestemdList.add(uitgestemd2);
        uitgestemdList.add(uitgestemd3);
        uitgestemdList.add(uitgestemd4);
        uitgestemdList.add(uitgestemd5);

        editFieldList.add(editField0);
        editFieldList.add(editField1);
        editFieldList.add(editField2);
        editFieldList.add(editField3);
        editFieldList.add(editField4);
        editFieldList.add(editField5);

        editConfirmList.add(editConfirm0);
        editConfirmList.add(editConfirm1);
        editConfirmList.add(editConfirm2);
        editConfirmList.add(editConfirm3);
        editConfirmList.add(editConfirm4);
        editConfirmList.add(editConfirm5);
    }

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    public void setMonthNodeLists(LinkedList<DataNodeList> monthNodeLists) {
        this.monthNodeLists = monthNodeLists;
    }
    public void setYearNodeLists(LinkedList<DataNodeList> yearNodeLists) {
        this.yearNodeLists = yearNodeLists;
    }
    public void setJarfLists(LinkedList<JarfList> jarfLists) {
        this.jarfLists = jarfLists;
    }

    public void setData(){
        for(int i = 0; i<6; i++){
            naamLabelList.get(i).setText(accountList.get(i).getName());
            editConfirmList.get(i).setDisable(false);
            uitgestemdList.get(i).setDisable(false);
            if (accountList.get(i).getName().equals(" ")) {
                editConfirmList.get(i).setDisable(true);
                uitgestemdList.get(i).setDisable(true);
            } else if (accountList.get(i).getName().equals("B3nni3")) {
                editConfirmList.get(i).setDisable(true);
            }
        }
    }

    public void newFeut(ActionEvent event){
        Account feut = new Account();
        accountList.add(feut);
        accountList.sort();

        mainController.write();

        DataNodeList monthNodeList = new DataNodeList(feut);
        DataNode dataNode = new DataNode(0, 0);
        monthNodeList.add(dataNode);
        monthNodeLists.add(monthNodeList);

        DataNodeList yearNodeList = new DataNodeList(feut);
        yearNodeList.add(dataNode);
        yearNodeLists.add(yearNodeList);

        JarfList jarfList = new JarfList(feut.getName());
        jarfLists.addLast(jarfList);

        mainController.writeDaily();
        mainController.writeJarf();
        setData();
        mainController.sleepTimerUpdate();

    }

    public void makeOld(ActionEvent event) {
        Account newOldDude = null;
        DataNodeList oldDudeList = null;
        for(int i = 0; i<6; i++) if (event.getSource().equals(uitgestemdList.get(i))) newOldDude = accountList.get(i);
        for(int i = 0; i< monthNodeLists.size(); i++) if (monthNodeLists.get(i).getDataOwner().equals(newOldDude)) monthNodeLists.remove(i);
        for(int i = 0; i<jarfLists.size(); i++) if (jarfLists.get(i).getOwner().equals(newOldDude.getName())) jarfLists.remove(i);
        for(int i = 0; i<yearNodeLists.size(); i++) if (yearNodeLists.get(i).getDataOwner().equals(newOldDude)) oldDudeList = yearNodeLists.get(i);

        Date currDate = new Date();
        File file = new File("src/main/resources/files/ZuipStats/" + currDate.getYear() + "-ZuipStats.txt");
        String s = oldDudeList.toWrite();
        try {
            if(file.createNewFile()){
                FileWriter writer = new FileWriter(file);
                writer.write(s.toString());
                writer.close();
            } else {
                Scanner sc = new Scanner(file);
                StringBuilder sb = new StringBuilder(s);
                while (sc.hasNextLine()){
                    sb.append(sc.nextLine());
                }
                sc.close();
                FileWriter writer = new FileWriter(file);
                writer.write(sb.toString());
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        newOldDude.setOld();
        accountList.sort();
        mainController.write();
        mainController.writeDaily();
        mainController.writeJarf();
        AudioOutputOverHead.playAudio("src/main/resources/sounds/ChurchBell.wav");
        setData();
        mainController.sleepTimerUpdate();
    }

    public void editConfimPress(ActionEvent event) {
        for(int i = 0; i<6; i++){
            if(event.getSource().equals((editConfirmList.get(i)))){
                jarfLists.get(i).setOwner(editFieldList.get(i).getText());
                accountList.get(i).setName(editFieldList.get(i).getText());
                editFieldList.get(i).setText("");
            }
        }
        mainController.write();
        mainController.writeDaily();
        mainController.writeJarf();
        setData();
        mainController.sleepTimerUpdate();
    }

    public void TransactionView(ActionEvent actionEvent) {
        mainController.transactionView();
    }

    public void birthDayView(ActionEvent actionEvent) { mainController.birthDayView(); }
}
