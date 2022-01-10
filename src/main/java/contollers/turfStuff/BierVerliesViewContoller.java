package contollers.turfStuff;

import contollers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import objects.AccountStuff.Genoot;
import objects.AccountStuff.GenotenList;
import objects.AccountStuff.HuisGenoot;
import objects.AudioOutputOverHead;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class BierVerliesViewContoller implements Initializable {
    private GenotenList accountList;
    private MainController mainController;

    private boolean[] selectedAccounts;
//TODO reformat this shit

    @FXML
    private Label totalStock;

    @FXML
    private Label krattenField;
    @FXML
    private Pane krattenPane;

    @FXML
    private Label pintenField;
    @FXML
    private Pane pintenPane;

    private Label currentSelection;

    @FXML
    private Button everyoneButton;

    @FXML
    private Pane namePane0;
    @FXML
    private Label nameLabel0;

    @FXML
    private Pane namePane1;
    @FXML
    private Label nameLabel1;

    @FXML
    private Pane namePane2;
    @FXML
    private Label nameLabel2;

    @FXML
    private Pane namePane3;
    @FXML
    private Label nameLabel3;

    @FXML
    private Pane namePane4;
    @FXML
    private Label nameLabel4;

    @FXML
    private Pane namePane5;
    @FXML
    private Label nameLabel5;

    @FXML
    private Button confirmButton;

    @FXML
    private Button button3;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button8;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button0;
    @FXML
    private Button button9;
    @FXML
    private Button button4;
    @FXML
    private Button button7;
    @FXML
    private Button buttonDel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setAccountList(GenotenList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void setData(){
        nameLabel0.setText(accountList.get(0).getName());
        nameLabel1.setText(accountList.get(1).getName());
        nameLabel2.setText(accountList.get(2).getName());
        nameLabel3.setText(accountList.get(3).getName());
        nameLabel4.setText(accountList.get(4).getName());
        nameLabel5.setText(accountList.get(5).getName());
        totalStock.setText(String.valueOf(accountList.getTotalStock()));

        selectedAccounts = new boolean[6];
        for(int i = 0; i<6; i++) selectedAccounts[i] = false;

        krattenPane.setBorder(new Border(new BorderStroke(Paint.valueOf("000000") , BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT)));
        pintenPane.setBorder(new Border(new BorderStroke(Paint.valueOf("ffffff") , BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT)));
        currentSelection = krattenField;

        krattenField.setText("");
        pintenField.setText("");
    }


    public void setCurrentSelection(MouseEvent event){
        Border blackBorder = new Border(new BorderStroke(Paint.valueOf("000000") , BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT));
        Border whiteBorder = new Border(new BorderStroke(Paint.valueOf("ffffff") , BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT));
        if(event.getSource().equals(krattenPane)){
            currentSelection = krattenField;
            krattenPane.setBorder(blackBorder);
            pintenPane.setBorder(whiteBorder);
        }if(event.getSource().equals(pintenPane)){
            currentSelection = pintenField;
            krattenPane.setBorder(whiteBorder);
            pintenPane.setBorder(blackBorder);
        }
        mainController.sleepTimerUpdate();
    }

    public void numpad(ActionEvent event){
        Button pressedButton = (Button)event.getSource();
        StringBuilder builder = new StringBuilder(currentSelection.getText());
        if(pressedButton.equals(button0)){
            builder.append(0);
        } else if(pressedButton.equals(button1)){
            builder.append(1);
        } else if(pressedButton.equals(button2)){
            builder.append(2);
        } else if(pressedButton.equals(button3)){
            builder.append(3);
        } else if(pressedButton.equals(button4)){
            builder.append(4);
        } else if(pressedButton.equals(button5)){
            builder.append(5);
        } else if(pressedButton.equals(button6)){
            builder.append(6);
        } else if(pressedButton.equals(button7)){
            builder.append(7);
        } else if(pressedButton.equals(button8)){
            builder.append(8);
        } else if(pressedButton.equals(button9)){
            builder.append(9);
        } else if(pressedButton.equals(buttonDel)){
            builder.delete(builder.length()-1,builder.length());
        }
        currentSelection.setText(builder.toString());
        mainController.sleepTimerUpdate();
    }

    public void everyoneButton(ActionEvent event){
        for(int i = 0; i<6; i++) selectedAccounts[i] = true;
        namePane0.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        namePane1.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        namePane2.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        namePane3.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        namePane4.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        namePane5.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        mainController.sleepTimerUpdate();
    }

    public void namePanePress(MouseEvent mouseEvent) {
        Pane pressed = (Pane)mouseEvent.getSource();
        if(pressed.equals(namePane0)){
            if(selectedAccounts[0]){
                selectedAccounts[0] = false;
                namePane0.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            } else {
                selectedAccounts[0] = true;
                namePane0.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            }
        } else if(pressed.equals(namePane1)){
            if(selectedAccounts[1]){
                selectedAccounts[1] = false;
                namePane1.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            } else {
                selectedAccounts[1] = true;
                namePane1.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            }
        } else if(pressed.equals(namePane2)){
            if(selectedAccounts[2]){
                selectedAccounts[2] = false;
                namePane2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            } else {
                selectedAccounts[2] = true;
                namePane2.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            }
        } else if(pressed.equals(namePane3)){
            if(selectedAccounts[3]){
                selectedAccounts[3] = false;
                namePane3.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            } else {
                selectedAccounts[3] = true;
                namePane3.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            }
        } else if(pressed.equals(namePane4)){
            if(selectedAccounts[4]){
                selectedAccounts[4] = false;
                namePane4.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            } else {
                selectedAccounts[4] = true;
                namePane4.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            }
        } else if(pressed.equals(namePane5)){
            if(selectedAccounts[5]){
                selectedAccounts[5] = false;
                namePane5.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            } else {
                selectedAccounts[5] = true;
                namePane5.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            }
        }
        mainController.sleepTimerUpdate();
    }

    public void confirmBierverlies(ActionEvent event){
        int nrHuisGenoten = 0;
        for (int i = 0; i < 6; i++) {
            if(selectedAccounts[i]) nrHuisGenoten++;
        }
        if (nrHuisGenoten == 0) return;
        int nrKratten = 0;
        int nrPinten = 0;
        if(!krattenField.getText().equals("")){
           nrKratten = Integer.parseInt(krattenField.getText());
        } if(!pintenField.getText().equals("")){
            nrPinten = Integer.parseInt(pintenField.getText());
        }
        int actualStock = nrKratten*24 + nrPinten;

        accountList.calculateBierVerlies(actualStock, selectedAccounts, nrHuisGenoten);
        accountList.tableUpdate();
        accountList.setTotalStock();

        namePane0.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        namePane1.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        namePane2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        namePane3.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        namePane4.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        namePane5.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        for(int i = 0; i<6; i++) selectedAccounts[i] = false;

        totalStock.setText(String.valueOf(accountList.getTotalStock()));
        krattenField.setText("");
        pintenField.setText("");
        mainController.write();
        AudioOutputOverHead.playAudio("src/main/resources/sounds/Pling.wav");
        mainController.sleepTimerUpdate();
    }
}
