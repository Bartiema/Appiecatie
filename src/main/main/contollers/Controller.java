package main.contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.objects.Account;
import main.objects.AccountList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Random;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    private AccountList accountList;
    private Random random = new Random();
    File file = new File("Accounts");
    //The MessageBoard
    @FXML
    private Label messageBoard;

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

    //New Feut buttons
    @FXML
    private MenuItem newFeut;
    @FXML
    private TextField newFeutTextField;

    //Oude lul buttons
    @FXML
    private MenuItem oudeLul;
    @FXML
    private TextField oudeLulTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
          Fetching the file to build the accountList
         */
        accountList = new AccountList();
        FileWriter writer = null;
        try {
            accountList.toRead(file);
            writer = new FileWriter(file);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        /*
          Updating the total stock and accounts to be correct before initializing
         */
        accountList.sort();
        accountList.updateAll();
        accountList.updateTotalStock();

        /*
          Setting names and Stocks
         */
        setNames();
        setAllStocks();

        /*
          Closing the Writer and saving before shutdown
         */
        try {
            if (writer == null) throw new AssertionError();
            writer.write(accountList.toWrite());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        resetMessageBoard();
        if(event.getSource() instanceof Button) {
            Button eventButton = (Button) event.getSource();

            //Updating the correct account
            if(eventButton.equals(beerDrank0)){
                accountList.beerDrank(0);
                stockAcc0.setText(String.valueOf(accountList.get(0).getStock()));
                if(accountList.get(0).getStock()<0){
                    messageBoard.setText("Ga bier kopen met je hoofd");
                }
            } else if(eventButton.equals(beerDrank1)){
                accountList.beerDrank(1);
                stockAcc1.setText(String.valueOf(accountList.get(1).getStock()));
                if(accountList.get(1).getStock()<0){
                    messageBoard.setText("Ga bier kopen met je hoofd");
                }
            } else if(eventButton.equals(beerDrank2)){
                accountList.beerDrank(2);
                stockAcc2.setText(String.valueOf(accountList.get(2).getStock()));
                if(accountList.get(2).getStock()<0){
                    messageBoard.setText("Ga bier kopen met je hoofd");
                }
            } else if(eventButton.equals(beerDrank3)){
                accountList.beerDrank(3);
                stockAcc3.setText(String.valueOf(accountList.get(3).getStock()));
                if(accountList.get(3).getStock()<0){
                    messageBoard.setText("Ga bier kopen met je hoofd");
                }
            } else if(eventButton.equals(beerDrank4)){
                accountList.beerDrank(4);
                stockAcc4.setText(String.valueOf(accountList.get(4).getStock()));
                if(accountList.get(4).getStock()<0){
                    messageBoard.setText("Ga bier kopen met je hoofd");
                }
            } else if(eventButton.equals(beerDrank5)){
                accountList.beerDrank(5);
                stockAcc5.setText(String.valueOf(accountList.get(5).getStock()));
                if(accountList.get(5).getStock()<0){
                    messageBoard.setText("Ga bier kopen feut");
                }
            }
            if(randomise((int)levelSlider.getValue())) messageBoard.setText("Trek een Spies Amice");
            totalStock.setText(String.valueOf(accountList.getTotalStock()));
            write();
        }
    }

    public void resetMessageBoard(){
       String s = "AppieZicht";
       int randomGuess = random.nextInt(100);
        if(randomGuess == 0){
           s = "AppieZicht Bravo";
       } else if(randomGuess == 1){
            s = "Vo voor de Leden";
        } else if(randomGuess == 2 ){
            s = "24 Bravo";
        } else if(randomGuess == 3){
            s = "Brand is geen Spiesbier";
        } else if(randomGuess == 4){
            s = "Ben je wel hard genoeg aan het borrelen";
        }
        messageBoard.setText(s);
    }

    /**
     * The randomizer for spiesjes
     * @return true means Spies, false is Not spies
     */
    public boolean randomise(int level){
        switch(level){
            case 1:
                return random.nextInt(300) == 0;
            case 2:
                return random.nextInt(100) == 0;
            case 3:
                return random.nextInt(25) == 0;
            case 4:
                return random.nextInt(10) == 0;
            case 5:
                return random.nextInt(5) == 0;
            default:
                return false;
        }
    }

    /**
     * Method for handeling the misbeer button
     * @param event - event
     */
    public void misBeerButtonClicked(ActionEvent event){
        resetMessageBoard();
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
            messageBoard.setText("Zieke feut die je bent");
            write();
        }
    }

    /**
     * Method for handeling the miskrat button
     * @param event - event
     */
    public void misKratButtonClicked(ActionEvent event){
        resetMessageBoard();
        if(event.getSource() instanceof Button) {
            Button eventButton = (Button) event.getSource();
            messageBoard.setText("Zieke mega-feut die je bent");

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
            write();
        }
    }

    /**
     * Method for handeling the kratkoop button
     * @param event - event
     */
    public void kratKoopButtonClicked(ActionEvent event){
        resetMessageBoard();
        if(event.getSource() instanceof Button) {
            Button eventButton = (Button) event.getSource();
            messageBoard.setText("Mooie lul die je bent");

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
            write();
        }
    }

    /**
     * A method to quickly write the updates to the file
     */
    public void write(){
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(accountList.toWrite());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * New Feut Button
     * makes a new account based on what is in the textfield
     * @param event - event
     */
    public void newFeut(ActionEvent event) {
        if(newFeutTextField.getCharacters().toString().equals("")){
            Account feut = new Account("Feut");
            accountList.add(feut);
        } else {
            Account feut = new Account(newFeutTextField.getCharacters().toString());
            accountList.add(feut);
        }
        accountList.sort();
        write();
    }

    public void makeOudeLul(ActionEvent event){
        String name = oudeLulTextField.getCharacters().toString();
        for(Account a : accountList){
            if(a.getName().equals(name)) a.setOld();
        }
        accountList.sort();
        write();
        setAllStocks();
        setNames();
    }
 }
