package contollers;

import contollers.instellingenStuff.InstellingenViewController;
import contollers.instellingenStuff.TransactionViewController;
import contollers.statistiekStuff.JarfGildeViewController;
import contollers.statistiekStuff.LineChartViewController;
import contollers.statistiekStuff.StatistiekViewController;
import contollers.turfStuff.BierVerliesViewContoller;
import contollers.turfStuff.TurfBeerViewController;
import contollers.turfStuff.TurfKratViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import objects.AccountStuff.AccountList;
import objects.JarfiniteitStuff.JarfStatList;
import objects.MessageList;
import objects.ScedulingStuff.New.DailyJob;
import objects.ScedulingStuff.New.MinuteJob;
import objects.ScedulingStuff.New.MonthlyJob;
import objects.ScedulingStuff.Old.DayIterator;
import objects.ScedulingStuff.Old.MonthIterator;
import objects.lineChartStuff.DataNodeList;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;

//Todo: JavaDoc in all files, and making comments around the place to explain stuff.

public class MainController implements Initializable {
    private AccountList accountList;
    private MessageList messages = new MessageList();
    private LinkedList<DataNodeList> dataNodeLists;
    private JarfStatList jarfStatList;
    private LocalTime timeOfLastAction = LocalTime.now();
    private boolean screenOn = true;


    private Random random = new Random();
    private int HJcounter = random.nextInt(999);

    File accountFile = new File("src/main/resources/files/Accounts");
    File messageFile = new File("src/main/resources/files/Messages");
    File transactionFile = new File("src/main/resources/files/Transactions");
    File LineChartDataFile = new File("src/main/resources/files/LineChartData");
    File JarfStatFile = new File("src/main/resources/files/JarfStats");


    //the extra loaders for the other possible pages
    FXMLLoader turfBeerPageLoader = new FXMLLoader(getClass().getResource("/views/turfStuff/turfBeerView.fxml"));
    FXMLLoader statistiekPageLoader = new FXMLLoader(getClass().getResource("/views/statistiekStuff/statistiekView.fxml"));
    FXMLLoader bierverliesPageLoader = new FXMLLoader(getClass().getResource("/views/turfStuff/bierVerliesView.fxml"));
    FXMLLoader instellingenPageLoader = new FXMLLoader(getClass().getResource("/views/instellingenStuff/instellingenView.fxml"));
    FXMLLoader transactionPageLoader = new FXMLLoader(getClass().getResource("/views/instellingenStuff/transactionView.fxml"));
    FXMLLoader jarfStatPageLoader = new FXMLLoader(getClass().getResource("/views/statistiekStuff/jarfGildeView.fxml"));
    FXMLLoader turfKratPageLoader = new FXMLLoader(getClass().getResource("/views/turfStuff/turfKratView.fxml"));
    FXMLLoader lineChartPageLoader = new FXMLLoader(getClass().getResource("/views/statistiekStuff/lineChartView.fxml"));

    //the Panes containing all the extra gui
    private final AnchorPane turfBeerPane = turfBeerPageLoader.load();
    private final AnchorPane statistiekPane = statistiekPageLoader.load();
    private final AnchorPane bierVerliesPane = bierverliesPageLoader.load();
    private final AnchorPane instellingenPane = instellingenPageLoader.load();
    private final AnchorPane transactionPane = transactionPageLoader.load();
    private final AnchorPane jarfGildePane = jarfStatPageLoader.load();
    private final AnchorPane turfKratPane = turfKratPageLoader.load();
    private final AnchorPane lineChartPane = lineChartPageLoader.load();

    //The controllers of the other Panes
    TurfBeerViewController turfBeerViewController = turfBeerPageLoader.getController();
    StatistiekViewController statistiekViewController = statistiekPageLoader.getController();
    BierVerliesViewContoller bierVerliesViewContoller = bierverliesPageLoader.getController();
    InstellingenViewController instellingenViewController = instellingenPageLoader.getController();
    TransactionViewController transactionViewController = transactionPageLoader.getController();
    JarfGildeViewController jarfGildeViewController = jarfStatPageLoader.getController();
    TurfKratViewController turfKratViewController = turfKratPageLoader.getController();
    LineChartViewController lineChartViewController = lineChartPageLoader.getController();


    //The MessageBoard
    @FXML
    private Label messageBoard;

    //The buttons in the top bar
    @FXML
    private Button turvenBeerButton;
    @FXML
    private Button turvenKratButton;
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
        jarfStatList = new JarfStatList();
        try {
            accountList.toRead(accountFile);
            accountList.setTransactionList(transactionFile);

            messages.toRead(messageFile);

            dataNodeLists = DataNodeList.toRead(accountList, LineChartDataFile);

            jarfStatList.toRead(JarfStatFile);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        /*
          Updating the total stock and accounts to be correct before initializing
         */
        accountList.sort();
        accountList.updateAll();
        accountList.updateTotalStock();
        accountList.getTransactionList().sort();

        AnchorPane.setTopAnchor(turfBeerPane, (double)125);
        mainPane.getChildren().add(turfBeerPane);

        turfBeerViewController.setAccountList(accountList);
        turfBeerViewController.setMainController(this);
        turfBeerViewController.setAllStocks();
        turfBeerViewController.setNames();
        turfBeerViewController.setLongPositiveTimer();

        statistiekViewController.setAccountList(accountList);
        statistiekViewController.setMainController(this);
        statistiekViewController.setDataNodeLists(dataNodeLists);
        statistiekViewController.setJarfGildePage(jarfGildePane);
        statistiekViewController.setJarfGildeViewController(jarfGildeViewController);
        statistiekViewController.setLineChartPage(lineChartPane);
        statistiekViewController.setLineChartViewController(lineChartViewController);

        bierVerliesViewContoller.setAccountList(accountList);
        bierVerliesViewContoller.setMainController(this);

        instellingenViewController.setAccountList(accountList);
        instellingenViewController.setMainController(this);
        instellingenViewController.setDataNodeLists(dataNodeLists);
        instellingenViewController.setJarfStatList(jarfStatList);

        transactionViewController.setAccountList(accountList);
        transactionViewController.setMainController(this);

        jarfGildeViewController.setAccountList(accountList);
        jarfGildeViewController.setJarfStatList(jarfStatList);
        jarfGildeViewController.setMainController(this);

        turfKratViewController.setAccountList(accountList);
        turfKratViewController.setMainController(this);

        lineChartViewController.setAccountList(accountList);
        lineChartViewController.setDataNodeLists(dataNodeLists);
        lineChartViewController.setMainController(this);



        //Daily and Montly scheduler
        StdSchedulerFactory factory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = factory.getScheduler();
            scheduler.start();
            //Data for daily and montly task
            JobDataMap data = new JobDataMap();
            data.put("accounts", accountList);
            data.put("controller", this);
            data.put("dataNodeLists", dataNodeLists);
            //Data for display procces
            Runtime rt = Runtime.getRuntime();
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("runtime", rt);
            dataMap.put("screen", screenOn);
            dataMap.put("time", timeOfLastAction);


            JobDetail dailyJobDetail = JobBuilder.newJob(DailyJob.class).usingJobData(data).withIdentity("DailyJob","Group1").build();
            JobDetail monthlyJobDetail = JobBuilder.newJob(MonthlyJob.class).usingJobData(data).withIdentity("MonthlyJob","Group2").build();
            JobDetail minuteJobDetail = JobBuilder.newJob(MinuteJob.class).usingJobData(dataMap).withIdentity("MinuteJob","Group3").build();

            Trigger dailyTrigger = TriggerBuilder.newTrigger().startAt(new DayIterator().next()).withIdentity("DailyTrigger", "Group1").withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().preserveHourOfDayAcrossDaylightSavings(true).withIntervalInHours(24)).build();
            Trigger monthlyTrigger = TriggerBuilder.newTrigger().startAt(new MonthIterator().next()).withIdentity("MonthlyTrigger", "Group2").withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().preserveHourOfDayAcrossDaylightSavings(true).withIntervalInMonths(1)).build();
            Trigger minuteTrigger = TriggerBuilder.newTrigger().startAt(new Date()).withIdentity("MinuteTrigger", "Group3").withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().preserveHourOfDayAcrossDaylightSavings(true).withIntervalInMinutes(1)).build();

            scheduler.scheduleJob(dailyJobDetail, dailyTrigger);
            scheduler.scheduleJob(monthlyJobDetail, monthlyTrigger);
            scheduler.scheduleJob(minuteJobDetail, minuteTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }




    }

    public Label getMessageBoard(){
        return this.messageBoard;
    }
    public void sleepTimerUpdate() {
        timeOfLastAction = LocalTime.now();
        screenOn = true;
    }
    public int getHJcounter() { return HJcounter; }
    public void increaseHJcounter() {HJcounter++;}

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
     * A method to quickly write the account and transaciton updates to the file
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
     * The method that updates the lineChartFile
     */
    public void writeDaily() {
        try{
            FileWriter lineChartWriter = new FileWriter(LineChartDataFile);
            StringBuilder s = new StringBuilder();
            for(DataNodeList d : dataNodeLists){
                s.append(d.toWrite());
            }
            lineChartWriter.write(s.toString());
            lineChartWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJarf(){
        try {
            FileWriter jarfStatWriter = new FileWriter(JarfStatFile);
            jarfStatWriter.write(jarfStatList.toWrite());
            jarfStatWriter.close();
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
    public void turfBeerView(ActionEvent event) {
        if(mainPane.getChildren().contains(turfBeerPane)) return;
        if(mainPane.getChildren().contains(statistiekPane)) mainPane.getChildren().remove(statistiekPane);
        if(mainPane.getChildren().contains(bierVerliesPane)) mainPane.getChildren().remove(bierVerliesPane);
        if(mainPane.getChildren().contains(instellingenPane)) mainPane.getChildren().remove(instellingenPane);
        if(mainPane.getChildren().contains(transactionPane)) mainPane.getChildren().remove(transactionPane);
        if(mainPane.getChildren().contains(turfKratPane)) mainPane.getChildren().remove(turfKratPane);

        AnchorPane.setTopAnchor(turfBeerPane, (double)125);
        mainPane.getChildren().add(turfBeerPane);

        turfBeerViewController.setAllStocks();
        turfBeerViewController.setNames();
        sleepTimerUpdate();
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
        if(mainPane.getChildren().contains(turfBeerPane)) mainPane.getChildren().remove(turfBeerPane);
        if(mainPane.getChildren().contains(bierVerliesPane)) mainPane.getChildren().remove(bierVerliesPane);
        if(mainPane.getChildren().contains(instellingenPane)) mainPane.getChildren().remove(instellingenPane);
        if(mainPane.getChildren().contains(transactionPane)) mainPane.getChildren().remove(transactionPane);
        if(mainPane.getChildren().contains(turfKratPane)) mainPane.getChildren().remove(turfKratPane);

        AnchorPane.setTopAnchor(statistiekPane, (double)125);
        mainPane.getChildren().add(statistiekPane);

        statistiekViewController.setData();
        sleepTimerUpdate();
    }

    public void bierverliesView(ActionEvent event){
        if(mainPane.getChildren().contains(bierVerliesPane)) return;
        if(mainPane.getChildren().contains(turfBeerPane)) mainPane.getChildren().remove(turfBeerPane);
        if(mainPane.getChildren().contains(statistiekPane)) mainPane.getChildren().remove(statistiekPane);
        if(mainPane.getChildren().contains(instellingenPane)) mainPane.getChildren().remove(instellingenPane);
        if(mainPane.getChildren().contains(transactionPane)) mainPane.getChildren().remove(transactionPane);
        if(mainPane.getChildren().contains(turfKratPane)) mainPane.getChildren().remove(turfKratPane);

        AnchorPane.setTopAnchor(bierVerliesPane, (double)125);
        mainPane.getChildren().add(bierVerliesPane);

        bierVerliesViewContoller.setData();
        sleepTimerUpdate();
    }

    public void instellingenView(ActionEvent event){
        if(mainPane.getChildren().contains(instellingenPane)) return;
        if(mainPane.getChildren().contains(turfBeerPane)) mainPane.getChildren().remove(turfBeerPane);
        if(mainPane.getChildren().contains(statistiekPane)) mainPane.getChildren().remove(statistiekPane);
        if(mainPane.getChildren().contains(bierVerliesPane)) mainPane.getChildren().remove(bierVerliesPane);
        if(mainPane.getChildren().contains(transactionPane)) mainPane.getChildren().remove(transactionPane);
        if(mainPane.getChildren().contains(turfKratPane)) mainPane.getChildren().remove(turfKratPane);

        AnchorPane.setTopAnchor(instellingenPane, (double)125);
        mainPane.getChildren().add(instellingenPane);
        instellingenViewController.setData();
        sleepTimerUpdate();
    }

    public void transactionView(){
        if(mainPane.getChildren().contains(transactionPane)) return;
        if(mainPane.getChildren().contains(turfBeerPane)) mainPane.getChildren().remove(turfBeerPane);
        if(mainPane.getChildren().contains(statistiekPane)) mainPane.getChildren().remove(statistiekPane);
        if(mainPane.getChildren().contains(bierVerliesPane)) mainPane.getChildren().remove(bierVerliesPane);
        if(mainPane.getChildren().contains(instellingenPane)) mainPane.getChildren().remove(instellingenPane);
        if(mainPane.getChildren().contains(turfKratPane)) mainPane.getChildren().remove(turfKratPane);

        AnchorPane.setTopAnchor(transactionPane, (double)125);
        mainPane.getChildren().add(transactionPane);
        transactionViewController.setData();
        sleepTimerUpdate();
    }

    public void turfKratView(ActionEvent event){
        if(mainPane.getChildren().contains(turfKratPane)) return;
        if(mainPane.getChildren().contains(turfBeerPane)) mainPane.getChildren().remove(turfBeerPane);
        if(mainPane.getChildren().contains(statistiekPane)) mainPane.getChildren().remove(statistiekPane);
        if(mainPane.getChildren().contains(bierVerliesPane)) mainPane.getChildren().remove(bierVerliesPane);
        if(mainPane.getChildren().contains(instellingenPane)) mainPane.getChildren().remove(instellingenPane);
        if(mainPane.getChildren().contains(transactionPane)) mainPane.getChildren().remove(transactionPane);

        AnchorPane.setTopAnchor(turfKratPane, (double)125);
        mainPane.getChildren().add(turfKratPane);

        turfKratViewController.setAllStocks();
        turfKratViewController.setNames();
        sleepTimerUpdate();
    }

 }
