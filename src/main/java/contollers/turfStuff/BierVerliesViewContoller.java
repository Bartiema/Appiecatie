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
import objects.AccountStuff.Account;
import objects.AccountStuff.AccountList;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class BierVerliesViewContoller implements Initializable {
    private AccountList accountList;
    private MainController mainController;

    private LinkedList<Account> selectedAccounts = new LinkedList<Account>();
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

    public void setAccountList(AccountList accountList){
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

        selectedAccounts = new LinkedList<Account>();
        for(int i = 0; i<6; i++) selectedAccounts.add(null);

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
    }

    public void everyoneButton(ActionEvent event){
        for(int i = 0; i<6; i++) selectedAccounts.set(i, accountList.get(i));
        namePane0.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        namePane1.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        namePane2.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        namePane3.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        namePane4.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        namePane5.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
    }

    public void namePanePress(MouseEvent mouseEvent) {
        Pane pressed = (Pane)mouseEvent.getSource();
        if(pressed.equals(namePane0)){
            if(selectedAccounts.get(0)!=null){
                selectedAccounts.set(0, null);
                namePane0.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            } else {
                selectedAccounts.set(0, accountList.get(0));
                namePane0.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            }
        } else if(pressed.equals(namePane1)){
            if(selectedAccounts.get(1)!=null){
                selectedAccounts.set(1, null);
                namePane1.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            } else {
                selectedAccounts.set(1, accountList.get(1));
                namePane1.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            }
        } else if(pressed.equals(namePane2)){
            if(selectedAccounts.get(2)!=null){
                selectedAccounts.set(2, null);
                namePane2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            } else {
                selectedAccounts.set(2, accountList.get(2));
                namePane2.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            }
        } else if(pressed.equals(namePane3)){
            if(selectedAccounts.get(3)!=null){
                selectedAccounts.set(3, null);
                namePane3.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            } else {
                selectedAccounts.set(3, accountList.get(3));
                namePane3.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            }
        } else if(pressed.equals(namePane4)){
            if(selectedAccounts.get(4)!=null){
                selectedAccounts.set(4, null);
                namePane4.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            } else {
                selectedAccounts.set(4, accountList.get(4));
                namePane4.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            }
        } else if(pressed.equals(namePane5)){
            if(selectedAccounts.get(5)!=null){
                selectedAccounts.set(5, null);
                namePane5.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            } else {
                selectedAccounts.set(5, accountList.get(5));
                namePane5.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            }
        }
    }

    public void confirmBierverlies(ActionEvent event){
        selectedAccounts.remove(null);
        selectedAccounts.remove(null);
        selectedAccounts.remove(null);
        selectedAccounts.remove(null);
        selectedAccounts.remove(null);
        selectedAccounts.remove(null);
        if(selectedAccounts.size()==0) return;


        int nrKratten = 0;
        int nrPinten = 0;
        if(!krattenField.getText().equals("")){
           nrKratten = Integer.parseInt(krattenField.getText());
        } if(!pintenField.getText().equals("")){
            nrPinten = Integer.parseInt(pintenField.getText());
        }
        int actualStock = nrKratten*24 + nrPinten;

        accountList.calculateBierVerlies(actualStock,selectedAccounts);
        accountList.updateAll();
        accountList.updateTotalStock();

        namePane0.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        namePane1.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        namePane2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        namePane3.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        namePane4.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        namePane5.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        selectedAccounts = new LinkedList<Account>();
        for(int i = 0; i<6; i++) selectedAccounts.add(null);

        totalStock.setText(String.valueOf(accountList.getTotalStock()));
        krattenField.setText("");
        pintenField.setText("");
        mainController.write();
    }
}
