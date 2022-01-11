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
import objects.AccountStuff.GenotenList;
import objects.AccountStuff.HuisGenoot;
import objects.AudioOutputOverHead;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class TurfBeerViewController implements Initializable {

    private GenotenList accountList;
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

    //The beer drank buttons
    private LinkedList<Button> beerDrankButtonList = new LinkedList<>();
    @FXML
    private Button beerDrank0;
    @FXML
    private Button beerDrank1;
    @FXML
    private Button beerDrank2;
    @FXML
    private Button beerDrank3;
    @FXML
    private Button beerDrank4;
    @FXML
    private Button beerDrank5;

    //The misBeer button's
    private  LinkedList<Button> misBeerButtonList = new LinkedList<>();
    @FXML
    private Button misBeer0;
    @FXML
    private Button misBeer1;
    @FXML
    private Button misBeer2;
    @FXML
    private Button misBeer3;
    @FXML
    private Button misBeer4;
    @FXML
    private Button misBeer5;

    @FXML
    private Button HJbutton;
    @FXML
    private Label HJlabel;

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

        beerDrankButtonList.add(beerDrank0);
        beerDrankButtonList.add(beerDrank1);
        beerDrankButtonList.add(beerDrank2);
        beerDrankButtonList.add(beerDrank3);
        beerDrankButtonList.add(beerDrank4);
        beerDrankButtonList.add(beerDrank5);

        misBeerButtonList.add(misBeer0);
        misBeerButtonList.add(misBeer1);
        misBeerButtonList.add(misBeer2);
        misBeerButtonList.add(misBeer3);
        misBeerButtonList.add(misBeer4);
        misBeerButtonList.add(misBeer5);
    }

    public void setAccountList(GenotenList accountList){
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
        for(int i = 0; i<6;i++) {
            HuisGenoot h = (HuisGenoot) accountList.get(i);
            accountStockList.get(i).setText(String.valueOf(h.getStock()));
        }
        totalStock.setText(String.valueOf(accountList.getTotalStock()));
        HJlabel.setText(String.valueOf(mainController.getHJcounter()));
        setPositiveTimer();
    }

    /**
     * What happens when someone takes a beer
     * @param event - the button being clicked
     */
    public void drankButtonClicked(ActionEvent event) {
        mainController.resetMessageBoard();
        for(int i = 0; i<6 ; i++){
            if(event.getSource().equals(beerDrankButtonList.get(i))){
                HuisGenoot h = (HuisGenoot) accountList.get(i);
                h.beerTurf();
                if(h.getStock()<0)  mainController.getMessageBoard().setText("Ga bier kopen met je hoofd");
                accountStockList.get(i).setText(String.valueOf(h.getStock()));
            }
        }
        totalStock.setText(String.valueOf(accountList.getTotalStock()));

        mainController.write();
        positiveBeer();
        divisibleBy24();

        AudioOutputOverHead.playAudio("src/main/resources/sounds/Pling.wav");
        mainController.sleepTimerUpdate();
    }
    /**
     * Method for handeling the misbeer button
     * @param event - event
     */
    public void misBeerButtonClicked(ActionEvent event){
        mainController.resetMessageBoard();
        for(int i = 0; i<6 ; i++){
            if(event.getSource().equals(misBeerButtonList.get(i))){
                HuisGenoot h = (HuisGenoot) accountList.get(i);
                h.beerMisTurf();
                accountStockList.get(i).setText(String.valueOf(h.getStock()));
            }
        }
        totalStock.setText(String.valueOf(accountList.getTotalStock()));
        mainController.getMessageBoard().setText("Zieke feut die je bent");
        mainController.write();
        positiveBeer();
        divisibleBy24();
        AudioOutputOverHead.playAudio("src/main/resources/sounds/Pling.wav");
        mainController.sleepTimerUpdate();

    }

    /**
     * A method setting the Background to white or red depending on stock value
     */
    public void positiveBeer(){
        Background redBackground = new Background(new BackgroundFill(Color.RED, null, null));
        Background whiteBackground = new Background(new BackgroundFill(Color.WHITE, null, null));
        for(int i = 0; i<6; i++){
            HuisGenoot h = (HuisGenoot) accountList.get(i);
            if(h.getStock()>=0) accountPaneList.get(i).setBackground(whiteBackground);
            if(h.getStock()<0) accountPaneList.get(i).setBackground(redBackground);
        }
    }

    public void setLongPositiveTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                positiveBeer();
                divisibleBy24();
            }
        };

        timer.schedule(timerTask, 1000);
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

    public void HJcounter(ActionEvent actionEvent) {
        mainController.increaseHJcounter();
        HJlabel.setText(String.valueOf(mainController.getHJcounter()));
    }
}
