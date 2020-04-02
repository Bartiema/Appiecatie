package main.contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.objects.AccountList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Random;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    private AccountList accountList;
    private Random random = new Random();
    File file = new File("Accounts");

    private AnchorPane turfPane;
    private AnchorPane statistiekPane;

    //The MessageBoard
    @FXML
    private Label messageBoard;

    @FXML
    private Button turvenButton;
    @FXML
    private Button statistiekenButton;
    @FXML
    private AnchorPane mainPane;


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

        FXMLLoader turfPageLoader = new FXMLLoader(getClass().getResource("/turfView.fxml"));
        FXMLLoader statistiekPageLoader = new FXMLLoader(getClass().getResource("/statistiekView.fxml"));

        try {
            turfPane = turfPageLoader.load();
            statistiekPane = statistiekPageLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AnchorPane.setTopAnchor(turfPane, (double)125);
        mainPane.getChildren().add(turfPane);

        TurfViewController turfViewController = turfPageLoader.getController();
        turfViewController.setAccountList(accountList);
        turfViewController.setMainController(this);
        turfViewController.setAllStocks();
        turfViewController.setNames();



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

    public Label getMessageBoard(){
        return this.messageBoard;
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
        } else if(randomGuess == 5){
            s = "Bless up";
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

    public void turfView(ActionEvent event) {
        if(mainPane.getChildren().contains(turfPane)) return;
        if(mainPane.getChildren().contains(statistiekPane)) mainPane.getChildren().remove(statistiekPane);
        FXMLLoader turfPageLoader = new FXMLLoader(getClass().getResource("/turfView.fxml"));
        AnchorPane.setTopAnchor(turfPane, (double)125);
        mainPane.getChildren().add(turfPane);

        TurfViewController turfViewController = turfPageLoader.getController();
        turfViewController.setAccountList(accountList);
        turfViewController.setMainController(this);
        turfViewController.setAllStocks();
        turfViewController.setNames();
    }


    public void statistiekenView(ActionEvent event) {
        if(mainPane.getChildren().contains(statistiekPane)) return;
        if(mainPane.getChildren().contains(turfPane)) mainPane.getChildren().remove(turfPane);
        FXMLLoader statistiekPageLoader = new FXMLLoader(getClass().getResource("/statistiekView.fxml"));
        AnchorPane.setTopAnchor(statistiekPane, (double)125);
        mainPane.getChildren().add(statistiekPane);

        StatistiekViewController statistiekViewController = statistiekPageLoader.getController();
        statistiekViewController.setAccountList(accountList);
        statistiekViewController.setMainController(this);
    }
 }
