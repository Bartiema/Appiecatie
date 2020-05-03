package main.contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.objects.AccountList;
import main.objects.MessageList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Random;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    private AccountList accountList;
    private MessageList messages = new MessageList();

    File accountFile = new File("Accounts");
    File messageFile = new File("src\\main\\main\\files\\Messages");
    File transactionFile = new File("src\\main\\main\\files\\Transactions");

    private Random random = new Random();

    //the extra loaders for the other possible pages
    FXMLLoader turfPageLoader = new FXMLLoader(getClass().getResource("/turfView.fxml"));
    FXMLLoader statistiekPageLoader = new FXMLLoader(getClass().getResource("/statistiekView.fxml"));
    FXMLLoader bierverliesPageLoader = new FXMLLoader(getClass().getResource("/bierVerliesView.fxml"));
    FXMLLoader instellingenPageLoader = new FXMLLoader(getClass().getResource("/instellingenView.fxml"));
    FXMLLoader transactionPageLoader = new FXMLLoader(getClass().getResource("/transactionView.fxml"));

    //the Panes containing all the extra gui
    private AnchorPane turfPane = turfPageLoader.load();
    private AnchorPane statistiekPane = statistiekPageLoader.load();
    private AnchorPane bierVerliesPane = bierverliesPageLoader.load();
    private AnchorPane instellingenPane = instellingenPageLoader.load();
    private AnchorPane transactionPane = transactionPageLoader.load();

    //The controllers of the other Panes
    TurfViewController turfViewController = turfPageLoader.getController();
    StatistiekViewController statistiekViewController = statistiekPageLoader.getController();
    BierVerliesViewContoller bierVerliesViewContoller = bierverliesPageLoader.getController();
    InstellingenViewController instellingenViewController = instellingenPageLoader.getController();
    TransactionViewController transactionViewController = transactionPageLoader.getController();

    //The MessageBoard
    @FXML
    private Label messageBoard;

    //The buttons in the top bar
    @FXML
    private Button turvenButton;
    @FXML
    private Button statistiekenButton;
    @FXML
    private Button bierverliesButton;

    //the Main Pane for setting the other panes on top
    @FXML
    private AnchorPane mainPane;

    public MainController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
          Fetching the file to build the accountList
         */
        accountList = new AccountList();
        FileWriter writer = null;
        try {
            accountList.toRead(accountFile);
            accountList.setTransactionList(transactionFile);
            writer = new FileWriter(accountFile);

            messages.toRead(messageFile);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        /*
          Updating the total stock and accounts to be correct before initializing
         */
        accountList.sort();
        accountList.updateAll();
        accountList.updateTotalStock();

        AnchorPane.setTopAnchor(turfPane, (double)125);
        mainPane.getChildren().add(turfPane);

        statistiekViewController.setAccountList(accountList);
        statistiekViewController.setMainController(this);

        TurfViewController turfViewController = turfPageLoader.getController();
        turfViewController.setAccountList(accountList);
        turfViewController.setMainController(this);
        turfViewController.setAllStocks();
        turfViewController.setNames();
        turfViewController.positiveBeer();

        bierVerliesViewContoller.setAccountList(accountList);
        bierVerliesViewContoller.setMainController(this);

        instellingenViewController.setAccountList(accountList);
        instellingenViewController.setMainController(this);

        transactionViewController.setAccountList(accountList);
        transactionViewController.setMainController(this);

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

    /**
     * The method reseting the Messageboard pulling a string out of the Messages file
     */
    public void resetMessageBoard(){
       int randomGuess = random.nextInt(messages.size()*5);
       String s = messages.get(randomGuess);
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
            FileWriter accwriter = new FileWriter(accountFile);
            accwriter.write(accountList.toWrite());
            accwriter.close();

            FileWriter transactionWriter = new FileWriter(transactionFile);
            transactionWriter.write(accountList.getTransactionList().toWrite());
            transactionWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * eventHandler for the turfbutton being pressed
     * First checks if the turfpane isnt already the active pane
     * then removes the other panes if its not
     * Then it will load in the TurfPane
     * @param event - the button being pressed
     */
    public void turfView(ActionEvent event) {
        if(mainPane.getChildren().contains(turfPane)) return;
        if(mainPane.getChildren().contains(statistiekPane)) mainPane.getChildren().remove(statistiekPane);
        if(mainPane.getChildren().contains(bierVerliesPane)) mainPane.getChildren().remove(bierVerliesPane);
        if(mainPane.getChildren().contains(instellingenPane)) mainPane.getChildren().remove(instellingenPane);
        if(mainPane.getChildren().contains(transactionPane)) mainPane.getChildren().remove(transactionPane);

        AnchorPane.setTopAnchor(turfPane, (double)125);
        mainPane.getChildren().add(turfPane);

        turfViewController.setAllStocks();
        turfViewController.setNames();
        turfViewController.positiveBeer();
    }

    /**
     * eventHandler for the statistiekButton being pressed
     * First checks if the statistiekPane isnt already the active pane
     * then removes the other panes if its not
     * Then it will load in the statistiekPane
     * @param event - the button being pressed
     */
    public void statistiekenView(ActionEvent event) throws IllegalAccessException {
        if(mainPane.getChildren().contains(statistiekPane)) return;
        if(mainPane.getChildren().contains(turfPane)) mainPane.getChildren().remove(turfPane);
        if(mainPane.getChildren().contains(bierVerliesPane)) mainPane.getChildren().remove(bierVerliesPane);
        if(mainPane.getChildren().contains(instellingenPane)) mainPane.getChildren().remove(instellingenPane);
        if(mainPane.getChildren().contains(transactionPane)) mainPane.getChildren().remove(transactionPane);

        AnchorPane.setTopAnchor(statistiekPane, (double)125);
        mainPane.getChildren().add(statistiekPane);

        statistiekViewController.setData();
    }

    public void bierverliesView(ActionEvent event){
        if(mainPane.getChildren().contains(bierVerliesPane)) return;
        if(mainPane.getChildren().contains(turfPane)) mainPane.getChildren().remove(turfPane);
        if(mainPane.getChildren().contains(statistiekPane)) mainPane.getChildren().remove(statistiekPane);
        if(mainPane.getChildren().contains(instellingenPane)) mainPane.getChildren().remove(instellingenPane);
        if(mainPane.getChildren().contains(transactionPane)) mainPane.getChildren().remove(transactionPane);

        AnchorPane.setTopAnchor(bierVerliesPane, (double)125);
        mainPane.getChildren().add(bierVerliesPane);

        bierVerliesViewContoller.setData();
    }

    public void instellingenView(ActionEvent event){
        if(mainPane.getChildren().contains(instellingenPane)) return;
        if(mainPane.getChildren().contains(turfPane)) mainPane.getChildren().remove(turfPane);
        if(mainPane.getChildren().contains(statistiekPane)) mainPane.getChildren().remove(statistiekPane);
        if(mainPane.getChildren().contains(bierVerliesPane)) mainPane.getChildren().remove(bierVerliesPane);
        if(mainPane.getChildren().contains(transactionPane)) mainPane.getChildren().remove(transactionPane);

        AnchorPane.setTopAnchor(instellingenPane, (double)125);
        mainPane.getChildren().add(instellingenPane);
        instellingenViewController.setData();
    }

    public void transactionView(){
        if(mainPane.getChildren().contains(transactionPane)) return;
        if(mainPane.getChildren().contains(turfPane)) mainPane.getChildren().remove(turfPane);
        if(mainPane.getChildren().contains(statistiekPane)) mainPane.getChildren().remove(statistiekPane);
        if(mainPane.getChildren().contains(bierVerliesPane)) mainPane.getChildren().remove(bierVerliesPane);
        if(mainPane.getChildren().contains(instellingenPane)) mainPane.getChildren().remove(instellingenPane);

        AnchorPane.setTopAnchor(transactionPane, (double)125);
        mainPane.getChildren().add(transactionPane);
        transactionViewController.setData();
    }
 }
