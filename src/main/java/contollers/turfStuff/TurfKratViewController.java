package contollers.turfStuff;

import contollers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import objects.AccountStuff.AccountList;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class TurfKratViewController implements Initializable {
    private AccountList accountList;
    private MainController mainController;
    //The Names and Stocks of the Bierview
    private LinkedList<Label> accountNameList = new LinkedList<>();
    private LinkedList<Label> accountStockList = new LinkedList<>();
    @FXML
    private Label naamAcc0;
    @FXML
    private Label naamAcc1;
    @FXML
    private Label naamAcc2;
    @FXML
    private Label naamAcc3;
    @FXML
    private Label naamAcc4;
    @FXML
    private Label naamAcc5;
    @FXML
    private Label stockAcc0;
    @FXML
    private Label stockAcc1;
    @FXML
    private Label stockAcc2;
    @FXML
    private Label stockAcc3;
    @FXML
    private Label stockAcc4;
    @FXML
    private Label stockAcc5;
    @FXML
    private Label totalStock;

    //Account panes for background changes
    private LinkedList<Pane> accountPaneList = new LinkedList<>();
    @FXML
    private Pane AccPane0;
    @FXML
    private Pane AccPane1;
    @FXML
    private Pane AccPane2;
    @FXML
    private Pane AccPane3;
    @FXML
    private Pane AccPane4;
    @FXML
    private Pane AccPane5;

    //The KratKoop Button's
    private LinkedList<Button> kratKoopButtonList = new LinkedList<>();
    @FXML
    private Button kratKoop0;
    @FXML
    private Button kratKoop1;
    @FXML
    private Button kratKoop2;
    @FXML
    private Button kratKoop3;
    @FXML
    private Button kratKoop4;
    @FXML
    private Button kratKoop5;

    //KratMis button's
    private LinkedList<Button> misKratButtonList = new LinkedList<>();
    @FXML
    private Button misKrat0;
    @FXML
    private Button misKrat1;
    @FXML
    private Button misKrat2;
    @FXML
    private Button misKrat3;
    @FXML
    private Button misKrat4;
    @FXML
    private Button misKrat5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountNameList.add(naamAcc0);
        accountNameList.add(naamAcc1);
        accountNameList.add(naamAcc2);
        accountNameList.add(naamAcc3);
        accountNameList.add(naamAcc4);
        accountNameList.add(naamAcc5);

        accountStockList.add(stockAcc0);
        accountStockList.add(stockAcc1);
        accountStockList.add(stockAcc2);
        accountStockList.add(stockAcc3);
        accountStockList.add(stockAcc4);
        accountStockList.add(stockAcc5);

        accountPaneList.add(AccPane0);
        accountPaneList.add(AccPane1);
        accountPaneList.add(AccPane2);
        accountPaneList.add(AccPane3);
        accountPaneList.add(AccPane4);
        accountPaneList.add(AccPane5);

        kratKoopButtonList.add(kratKoop0);
        kratKoopButtonList.add(kratKoop1);
        kratKoopButtonList.add(kratKoop2);
        kratKoopButtonList.add(kratKoop3);
        kratKoopButtonList.add(kratKoop4);
        kratKoopButtonList.add(kratKoop5);

        misKratButtonList.add(misKrat0);
        misKratButtonList.add(misKrat1);
        misKratButtonList.add(misKrat2);
        misKratButtonList.add(misKrat3);
        misKratButtonList.add(misKrat4);
        misKratButtonList.add(misKrat5);
    }

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }
    public void setMainController(MainController controller){
        this.mainController = controller;
    }

    /**
     * A method setting all the names in the view
     */
    public void setNames() {
        for(int i = 0; i<6; i++) accountNameList.get(i).setText(accountList.get(i).getName());
    }

    /**
     * a method setting all the Stocks in the view
     */
    public void setAllStocks(){
        for(int i = 0; i<6;i++) accountStockList.get(i).setText(String.valueOf(accountList.get(i).getStock()));
        totalStock.setText(String.valueOf(accountList.getTotalStock()));
        setPositiveTimer();
    }
    /**
     * Method for handeling the miskrat button
     * @param event - event
     */
    public void misKratButtonClicked(ActionEvent event){
        mainController.resetMessageBoard();
        for(int i = 0; i<6 ; i++){
            if(event.getSource().equals(misKratButtonList.get(i))){
                accountList.misKrat(i);
                accountStockList.get(i).setText(String.valueOf(accountList.get(i).getStock()));
            }
        }
        mainController.getMessageBoard().setText("Zieke mega-feut die je bent");
        totalStock.setText(String.valueOf(accountList.getTotalStock()));
        mainController.write();
        divisibleBy24();
        positiveBeer();
    }

    /**
     * Method for handeling the kratkoop button
     * @param event - event
     */
    public void kratKoopButtonClicked(ActionEvent event){
        mainController.resetMessageBoard();
        for( int i = 0; i<6; i++){
            if(event.getSource().equals(kratKoopButtonList.get(i))){
                accountList.kratKoop(i);
                accountStockList.get(i).setText(String.valueOf(accountList.get(i).getStock()));
            }
        }
        mainController.getMessageBoard().setText("Mooie lul die je bent");
        totalStock.setText(String.valueOf(accountList.getTotalStock()));
        positiveBeer();
        divisibleBy24();
        mainController.write();

    }

    /**
     * A method setting the Background to white or red depending on stock value
     */
    public void positiveBeer(){
        Background redBackground = new Background(new BackgroundFill(Color.RED, null, null));
        Background whiteBackground = new Background(new BackgroundFill(Color.WHITE, null, null));
        for(int i = 0; i<6; i++){
            if(accountList.get(i).getStock()>=0) accountPaneList.get(i).setBackground(whiteBackground);
            if(accountList.get(i).getStock()<0) accountPaneList.get(i).setBackground(redBackground);
        }
    }

    public void setPositiveTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                positiveBeer();
                divisibleBy24();
            }
        };

        timer.schedule(timerTask, 100);
    }

    public void divisibleBy24(){
        if(accountList.getTotalStock() % 24 == 0 && accountList.getTotalStock()!=0) totalStock.setTextFill(Color.YELLOWGREEN);
        else totalStock.setTextFill(Color.BLACK);
        for(Label l: accountStockList){
            if(Integer.parseInt(l.getText()) % 24 == 0 && Integer.parseInt(l.getText()) != 0) l.setTextFill(Color.GREEN);
            else l.setTextFill(Color.BLACK);
        }
    }
}

