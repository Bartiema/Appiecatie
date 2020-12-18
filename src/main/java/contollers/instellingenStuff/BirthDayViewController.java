package contollers.instellingenStuff;

import contollers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import objects.AccountStuff.AccountList;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class BirthDayViewController implements Initializable {
    private AccountList accountList;
    private MainController mainController;
    private Label currentSelection;

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

    private  LinkedList<Label> dayLabelList = new LinkedList<>();
    @FXML
    private Label birthDayLabel0;
    @FXML
    private Label birthDayLabel1;
    @FXML
    private Label birthDayLabel2;
    @FXML
    private Label birthDayLabel3;
    @FXML
    private Label birthDayLabel4;
    @FXML
    private Label birthDayLabel5;

    private LinkedList<Button> confirmButtonList = new LinkedList<>();
    @FXML
    private Button confirm0;
    @FXML
    private Button confirm1;
    @FXML
    private Button confirm2;
    @FXML
    private Button confirm3;
    @FXML
    private Button confirm4;
    @FXML
    private Button confirm5;

    @FXML
    private Button button0;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button buttonDel;

    @FXML
    private Label dayLabel;
    @FXML
    private Pane dayPane;

    @FXML
    private Label monthLabel;
    @FXML
    private Pane monthPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        naamLabelList.add(naamLabel0);
        naamLabelList.add(naamLabel1);
        naamLabelList.add(naamLabel2);
        naamLabelList.add(naamLabel3);
        naamLabelList.add(naamLabel4);
        naamLabelList.add(naamLabel5);

        dayLabelList.add(birthDayLabel0);
        dayLabelList.add(birthDayLabel1);
        dayLabelList.add(birthDayLabel2);
        dayLabelList.add(birthDayLabel3);
        dayLabelList.add(birthDayLabel4);
        dayLabelList.add(birthDayLabel5);

        confirmButtonList.add(confirm0);
        confirmButtonList.add(confirm1);
        confirmButtonList.add(confirm2);
        confirmButtonList.add(confirm3);
        confirmButtonList.add(confirm4);
        confirmButtonList.add(confirm5);

    }

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void setData(){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM");
        for(int i = 0; i<6; i++){
            naamLabelList.get(i).setText(accountList.get(i).getName());
            dayLabelList.get(i).setText(format.format(accountList.get(i).getBirthDay()));
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
        mainController.sleepTimerUpdate();
    }

    public void setCurrentSelection(MouseEvent event){
        Border blackBorder = new Border(new BorderStroke(Paint.valueOf("000000") , BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT));
        Border whiteBorder = new Border(new BorderStroke(Paint.valueOf("ffffff") , BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT));
        if(event.getSource().equals(dayPane)){
            currentSelection = dayLabel;
            dayPane.setBorder(blackBorder);
            monthPane.setBorder(whiteBorder);
        }if(event.getSource().equals(monthPane)){
            currentSelection = monthLabel;
            dayPane.setBorder(whiteBorder);
            monthPane.setBorder(blackBorder);
        }
        mainController.sleepTimerUpdate();
    }

    public void confirm(ActionEvent actionEvent) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM");
    }
}
