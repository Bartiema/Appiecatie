package main.contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.objects.AccountList;

import java.net.URL;
import java.util.ResourceBundle;

public class TurfViewController implements Initializable {
    private AccountList accountList;
    private MainController mainController;

    //The Names and Stocks of the Bierview
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

    //Zuiplevel slider
    @FXML
    private Slider levelSlider;

    //The beer drank buttons
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

    //The KratKoop Button's
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

    }

    public void setAccountList(AccountList accountList){
        this.accountList = accountList;
    }

    /**
     * A method setting all the names in the view
     */
    public void setNames() {
        naamAcc0.setText(accountList.get(0).getName());
        naamAcc1.setText(accountList.get(1).getName());
        naamAcc2.setText(accountList.get(2).getName());
        naamAcc3.setText(accountList.get(3).getName());
        naamAcc4.setText(accountList.get(4).getName());
        naamAcc5.setText(accountList.get(5).getName());
    }

    /**
     * a method setting all the Stocks in the view
     */
    public void setAllStocks(){
        stockAcc0.setText(String.valueOf(accountList.get(0).getStock()));
        stockAcc1.setText(String.valueOf(accountList.get(1).getStock()));
        stockAcc2.setText(String.valueOf(accountList.get(2).getStock()));
        stockAcc3.setText(String.valueOf(accountList.get(3).getStock()));
        stockAcc4.setText(String.valueOf(accountList.get(4).getStock()));
        stockAcc5.setText(String.valueOf(accountList.get(5).getStock()));
        totalStock.setText(String.valueOf(accountList.getTotalStock()));
    }

    /**
     * What happens when someone takes a beer
     * @param event - the button being clicked
     */
    public void drankButtonClicked(ActionEvent event) {
        mainController.resetMessageBoard();
        if(event.getSource() instanceof Button) {
            Button eventButton = (Button) event.getSource();

            //Updating the correct account
            if(eventButton.equals(beerDrank0)){
                accountList.beerDrank(0);
                stockAcc0.setText(String.valueOf(accountList.get(0).getStock()));
                if(accountList.get(0).getStock()<0){
                    mainController.getMessageBoard().setText("Ga bier kopen met je hoofd");
                }
            } else if(eventButton.equals(beerDrank1)){
                accountList.beerDrank(1);
                stockAcc1.setText(String.valueOf(accountList.get(1).getStock()));
                if(accountList.get(1).getStock()<0){
                    mainController.getMessageBoard().setText("Ga bier kopen met je hoofd");
                }
            } else if(eventButton.equals(beerDrank2)){
                accountList.beerDrank(2);
                stockAcc2.setText(String.valueOf(accountList.get(2).getStock()));
                if(accountList.get(2).getStock()<0){
                    mainController.getMessageBoard().setText("Ga bier kopen met je hoofd");
                }
            } else if(eventButton.equals(beerDrank3)){
                accountList.beerDrank(3);
                stockAcc3.setText(String.valueOf(accountList.get(3).getStock()));
                if(accountList.get(3).getStock()<0){
                    mainController.getMessageBoard().setText("Ga bier kopen met je hoofd");
                }
            } else if(eventButton.equals(beerDrank4)){
                accountList.beerDrank(4);
                stockAcc4.setText(String.valueOf(accountList.get(4).getStock()));
                if(accountList.get(4).getStock()<0){
                    mainController.getMessageBoard().setText("Ga bier kopen met je hoofd");
                }
            } else if(eventButton.equals(beerDrank5)){
                accountList.beerDrank(5);
                stockAcc5.setText(String.valueOf(accountList.get(5).getStock()));
                if(accountList.get(5).getStock()<0){
                    mainController.getMessageBoard().setText("Ga bier kopen feut");
                }
            }
            if(mainController.randomise((int)levelSlider.getValue())) mainController.getMessageBoard().setText("Trek een Spies Amice");
            totalStock.setText(String.valueOf(accountList.getTotalStock()));
            mainController.write();
            positiveBeer();
        }
    }
    /**
     * Method for handeling the misbeer button
     * @param event - event
     */
    public void misBeerButtonClicked(ActionEvent event){
        mainController.resetMessageBoard();
        if(event.getSource() instanceof Button) {
            Button eventButton = (Button) event.getSource();

            if(eventButton.equals(misBeer0)) {
                accountList.misBeer(0);
                stockAcc0.setText(String.valueOf(accountList.get(0).getStock()));
            } else if(eventButton.equals(misBeer1)) {
                accountList.misBeer(1);
                stockAcc1.setText(String.valueOf(accountList.get(1).getStock()));
            } else if(eventButton.equals(misBeer2)) {
                accountList.misBeer(2);
                stockAcc2.setText(String.valueOf(accountList.get(2).getStock()));
            } else if(eventButton.equals(misBeer3)) {
                accountList.misBeer(3);
                stockAcc3.setText(String.valueOf(accountList.get(3).getStock()));
            } else if(eventButton.equals(misBeer4)) {
                accountList.misBeer(4);
                stockAcc4.setText(String.valueOf(accountList.get(4).getStock()));
            } else if(eventButton.equals(misBeer5)) {
                accountList.misBeer(5);
                stockAcc5.setText(String.valueOf(accountList.get(5).getStock()));
            }
            totalStock.setText(String.valueOf(accountList.getTotalStock()));
            mainController.getMessageBoard().setText("Zieke feut die je bent");
            mainController.write();
            positiveBeer();
        }
    }

    /**
     * Method for handeling the miskrat button
     * @param event - event
     */
    public void misKratButtonClicked(ActionEvent event){
        mainController.resetMessageBoard();
        if(event.getSource() instanceof Button) {
            Button eventButton = (Button) event.getSource();
            mainController.getMessageBoard().setText("Zieke mega-feut die je bent");

            if(eventButton.equals(misKrat0)){
                accountList.misKrat(0);
                stockAcc0.setText(String.valueOf(accountList.get(0).getStock()));
            } else if(eventButton.equals(misKrat1)){
                accountList.misKrat(1);
                stockAcc1.setText(String.valueOf(accountList.get(1).getStock()));
            } else if(eventButton.equals(misKrat2)){
                accountList.misKrat(2);
                stockAcc2.setText(String.valueOf(accountList.get(2).getStock()));
            } else if(eventButton.equals(misKrat3)){
                accountList.misKrat(3);
                stockAcc3.setText(String.valueOf(accountList.get(3).getStock()));
            } else if(eventButton.equals(misKrat4)){
                accountList.misKrat(4);
                stockAcc4.setText(String.valueOf(accountList.get(4).getStock()));
            } else if(eventButton.equals(misKrat5)){
                accountList.misKrat(5);
                stockAcc5.setText(String.valueOf(accountList.get(5).getStock()));
            }
            totalStock.setText(String.valueOf(accountList.getTotalStock()));
            mainController.write();
            positiveBeer();
        }
    }

    /**
     * Method for handeling the kratkoop button
     * @param event - event
     */
    public void kratKoopButtonClicked(ActionEvent event){
        mainController.resetMessageBoard();
        if(event.getSource() instanceof Button) {
            Button eventButton = (Button) event.getSource();
            mainController.getMessageBoard().setText("Mooie lul die je bent");

            if(eventButton.equals(kratKoop0)){
                accountList.kratKoop(0);
                stockAcc0.setText(String.valueOf(accountList.get(0).getStock()));
            } else if(eventButton.equals(kratKoop1)){
                accountList.kratKoop(1);
                stockAcc1.setText(String.valueOf(accountList.get(1).getStock()));
            } else if(eventButton.equals(kratKoop2)){
                accountList.kratKoop(2);
                stockAcc2.setText(String.valueOf(accountList.get(2).getStock()));
            } else if(eventButton.equals(kratKoop3)){
                accountList.kratKoop(3);
                stockAcc3.setText(String.valueOf(accountList.get(3).getStock()));
            } else if(eventButton.equals(kratKoop4)){
                accountList.kratKoop(4);
                stockAcc4.setText(String.valueOf(accountList.get(4).getStock()));
            } else if(eventButton.equals(kratKoop5)){
                accountList.kratKoop(5);
                stockAcc5.setText(String.valueOf(accountList.get(5).getStock()));
            }
            totalStock.setText(String.valueOf(accountList.getTotalStock()));
            positiveBeer();
            mainController.write();
        }
    }

    /**
     * A method setting the Background to white or red depending on stock value
     */
    public void positiveBeer(){
        Color red = Color.RED;
        Color white = Color.WHITE;
        BackgroundFill redFill = new BackgroundFill(red, null, null);
        BackgroundFill whiteFill = new BackgroundFill(white, null, null);
        Background redBackground = new Background(redFill);
        Background whiteBackground = new Background(whiteFill);
        if(accountList.get(0).getStock()<0){
            AccPane0.setBackground(redBackground);
        } if(accountList.get(0).getStock()>= 0){
            AccPane0.setBackground(whiteBackground);
        } if(accountList.get(1).getStock()<0){
            AccPane1.setBackground(redBackground);
        } if(accountList.get(1).getStock()>= 0){
            AccPane1.setBackground(whiteBackground);
        } if(accountList.get(2).getStock()<0){
            AccPane2.setBackground(redBackground);
        } if(accountList.get(2).getStock()>= 0){
            AccPane2.setBackground(whiteBackground);
        } if(accountList.get(3).getStock()<0){
            AccPane3.setBackground(redBackground);
        } if(accountList.get(3).getStock()>= 0){
            AccPane3.setBackground(whiteBackground);
        } if(accountList.get(4).getStock()<0){
            AccPane4.setBackground(redBackground);
        } if(accountList.get(4).getStock()>= 0){
            AccPane4.setBackground(whiteBackground);
        } if(accountList.get(5).getStock()<0){
            AccPane5.setBackground(redBackground);
        } if(accountList.get(5).getStock()>= 0){
            AccPane5.setBackground(whiteBackground);
        }
    }
}
