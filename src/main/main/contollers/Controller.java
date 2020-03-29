package main.contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
          Fetching the file to build the accountList
         */
        File file = new File("Accounts");
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
        accountList.updateAll();
        accountList.updateTotalStock();

        /*
          Setting names and Stocks
         */
        setNames(accountList);
        setAllStocks(accountList);

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
     * @param accountList - the list of current accounts
     */
    public void setNames(AccountList accountList) {
        naamAcc0.setText(accountList.get(0).getName());
        naamAcc1.setText(accountList.get(1).getName());
        naamAcc2.setText(accountList.get(2).getName());
        naamAcc3.setText(accountList.get(3).getName());
        naamAcc4.setText(accountList.get(4).getName());
        naamAcc5.setText(accountList.get(5).getName());
    }

    /**
     * a method setting all the Stocks in the view
     * @param accountList - the list of current accounts
     */
    public void setAllStocks(AccountList accountList){
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
            if(randomise((int)levelSlider.getValue())) messageBoard.setText("Trek een Spies Amice");

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
            totalStock.setText(String.valueOf(accountList.getTotalStock()));
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


}
