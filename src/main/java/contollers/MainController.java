package contollers;

import contollers.extrasStuff.*;
import contollers.instellingenStuff.BirthDayViewController;
import contollers.instellingenStuff.InstellingenViewController;
import contollers.instellingenStuff.TransactionViewController;
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
import objects.JarfiniteitStuff.JarfList;
import objects.MessageList;
import objects.ScedulingStuff.Iterators.YearIterator;
import objects.ScedulingStuff.Jobs.DailyJob;
import objects.ScedulingStuff.Jobs.MinuteJob;
import objects.ScedulingStuff.Jobs.MonthlyJob;
import objects.ScedulingStuff.Iterators.DayIterator;
import objects.ScedulingStuff.Iterators.MonthIterator;
import objects.ScedulingStuff.Jobs.YearlyJob;
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
    private LinkedList<DataNodeList> monthNodeLists;
    private LinkedList<DataNodeList> yearNodeLists;
    private LinkedList<JarfList> jarfLists;
    private LocalTime timeOfLastAction = LocalTime.now();
    private boolean screenOn = true;


    private Random random = new Random();
    private int HJcounter = random.nextInt(999);

    File accountFile = new File("src/main/resources/files/Accounts");
    File messageFile = new File("src/main/resources/files/Messages");
    File transactionFile = new File("src/main/resources/files/Transactions");
    File monthChartDataFile = new File("src/main/resources/files/monthChartData");
    File yearChartDataFile = new File("src/main/resources/files/yearChartData");
    File JarfStatFile = new File("src/main/resources/files/JarfStats");


    //the extra loaders for the other possible pages
    FXMLLoader turfBeerPageLoader = new FXMLLoader(getClass().getResource("/views/turfStuff/turfBeerView.fxml"));
    FXMLLoader statistiekPageLoader = new FXMLLoader(getClass().getResource("/views/extrasStuff/extrasView.fxml"));
    FXMLLoader bierverliesPageLoader = new FXMLLoader(getClass().getResource("/views/turfStuff/bierVerliesView.fxml"));
    FXMLLoader instellingenPageLoader = new FXMLLoader(getClass().getResource("/views/instellingenStuff/instellingenView.fxml"));
    FXMLLoader transactionPageLoader = new FXMLLoader(getClass().getResource("/views/instellingenStuff/transactionView.fxml"));
    FXMLLoader jarfStatPageLoader = new FXMLLoader(getClass().getResource("/views/extrasStuff/jarfGildeView.fxml"));
    FXMLLoader turfKratPageLoader = new FXMLLoader(getClass().getResource("/views/turfStuff/turfKratView.fxml"));
    FXMLLoader lineChartPageLoader = new FXMLLoader(getClass().getResource("/views/extrasStuff/lineChartView.fxml"));
    FXMLLoader birthDayPageLoader = new FXMLLoader(getClass().getResource("/views/instellingenStuff/birthDayView.fxml"));
    FXMLLoader jarfGraphPageLoader = new FXMLLoader(getClass().getResource("/views/extrasStuff/jarfGraphView.fxml"));
    FXMLLoader barChartPageLoader = new FXMLLoader(getClass().getResource("/views/extrasStuff/barChartView.fxml"));

    //the Panes containing all the extra gui
    private final AnchorPane turfBeerPane = turfBeerPageLoader.load();
    private final AnchorPane statistiekPane = statistiekPageLoader.load();
    private final AnchorPane bierVerliesPane = bierverliesPageLoader.load();
    private final AnchorPane instellingenPane = instellingenPageLoader.load();
    private final AnchorPane transactionPane = transactionPageLoader.load();
    private final AnchorPane jarfGildePane = jarfStatPageLoader.load();
    private final AnchorPane turfKratPane = turfKratPageLoader.load();
    private final AnchorPane lineChartPane = lineChartPageLoader.load();
    private final AnchorPane birthDayPane = birthDayPageLoader.load();
    private final AnchorPane jarfGraphPane = jarfGraphPageLoader.load();
    private final AnchorPane barChartPane = barChartPageLoader.load();

    //The controllers of the other Panes
    TurfBeerViewController turfBeerViewController = turfBeerPageLoader.getController();
    ExtrasViewController extrasViewController = statistiekPageLoader.getController();
    BierVerliesViewContoller bierVerliesViewContoller = bierverliesPageLoader.getController();
    InstellingenViewController instellingenViewController = instellingenPageLoader.getController();
    TransactionViewController transactionViewController = transactionPageLoader.getController();
    JarfGildeViewController jarfGildeViewController = jarfStatPageLoader.getController();
    TurfKratViewController turfKratViewController = turfKratPageLoader.getController();
    LineChartViewController lineChartViewController = lineChartPageLoader.getController();
    BirthDayViewController birthDayViewController = birthDayPageLoader.getController();
    JarfGraphViewController jarfGraphViewController = jarfGraphPageLoader.getController();
    BarChartViewController barChartViewController = barChartPageLoader.getController();


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
        try {
            accountList.toRead(accountFile);
            accountList.setTransactionList(transactionFile);

            messages.toRead(messageFile);

            monthNodeLists = DataNodeList.toRead(accountList, monthChartDataFile);
            yearNodeLists = DataNodeList.toRead(accountList, yearChartDataFile);
            jarfLists = JarfList.toRead(JarfStatFile , accountList);

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

        extrasViewController.setAccountList(accountList);
        extrasViewController.setMainController(this);
        extrasViewController.setDataNodeLists(monthNodeLists);
        extrasViewController.setJarfGildePage(jarfGildePane);
        extrasViewController.setJarfGildeViewController(jarfGildeViewController);
        extrasViewController.setLineChartPage(lineChartPane);
        extrasViewController.setLineChartViewController(lineChartViewController);
        extrasViewController.setJarfGraphPage(jarfGraphPane);
        extrasViewController.setJarfGraphViewController(jarfGraphViewController);
        extrasViewController.setBarChartPage(barChartPane);
        extrasViewController.setBarChartViewController(barChartViewController);

        bierVerliesViewContoller.setAccountList(accountList);
        bierVerliesViewContoller.setMainController(this);

        instellingenViewController.setAccountList(accountList);
        instellingenViewController.setMainController(this);
        instellingenViewController.setMonthNodeLists(monthNodeLists);
        instellingenViewController.setJarfLists(jarfLists);

        transactionViewController.setAccountList(accountList);
        transactionViewController.setMainController(this);

        jarfGildeViewController.setAccountList(accountList);
        jarfGildeViewController.setJarfLists(jarfLists);
        jarfGildeViewController.setMainController(this);

        turfKratViewController.setAccountList(accountList);
        turfKratViewController.setMainController(this);

        lineChartViewController.setAccountList(accountList);
        lineChartViewController.setMonthNodeLists(monthNodeLists);
        lineChartViewController.setYearNodeLists(yearNodeLists);
        lineChartViewController.setMainController(this);

        birthDayViewController.setAccountList(accountList);
        birthDayViewController.setMainController(this);

        jarfGraphViewController.setAccountList(accountList);
        jarfGraphViewController.setJarfLists(jarfLists);
        jarfGraphViewController.setMainController(this);

        barChartViewController.setAccountList(accountList);
        barChartViewController.setMainController(this);
        barChartViewController.setYearNodeLists(yearNodeLists);



        //Daily and Montly scheduler
        StdSchedulerFactory factory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = factory.getScheduler();
            scheduler.start();
            //Data for daily and montly task
            JobDataMap data = new JobDataMap();
            data.put("accounts", accountList);
            data.put("controller", this);
            data.put("monthNodeLists", monthNodeLists);
            data.put("yearNodeLists", yearNodeLists);
            //Data for display procces
            Runtime rt = Runtime.getRuntime();
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("runtime", rt);
            dataMap.put("screen", screenOn);
            dataMap.put("time", timeOfLastAction);


            JobDetail dailyJobDetail = JobBuilder.newJob(DailyJob.class).usingJobData(data).withIdentity("DailyJob","Group1").build();
            JobDetail monthlyJobDetail = JobBuilder.newJob(MonthlyJob.class).usingJobData(data).withIdentity("MonthlyJob","Group2").build();
            JobDetail minuteJobDetail = JobBuilder.newJob(MinuteJob.class).usingJobData(dataMap).withIdentity("MinuteJob","Group3").build();
            JobDetail yearlyJobDetail = JobBuilder.newJob(YearlyJob.class).usingJobData(data).withIdentity("YearlyJob", "Group4").build();

            Trigger dailyTrigger = TriggerBuilder.newTrigger().startAt(new DayIterator().next()).withIdentity("DailyTrigger", "Group1").withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().preserveHourOfDayAcrossDaylightSavings(true).withIntervalInHours(24)).build();
            Trigger monthlyTrigger = TriggerBuilder.newTrigger().startAt(new MonthIterator().next()).withIdentity("MonthlyTrigger", "Group2").withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().preserveHourOfDayAcrossDaylightSavings(true).withIntervalInMonths(1)).build();
            Trigger minuteTrigger = TriggerBuilder.newTrigger().startAt(new Date()).withIdentity("MinuteTrigger", "Group3").withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().preserveHourOfDayAcrossDaylightSavings(true).withIntervalInMinutes(1)).build();
            Trigger yearlyTrigger = TriggerBuilder.newTrigger().startAt(new YearIterator().next()).withIdentity("YearlyTrigger", "Group4").withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().preserveHourOfDayAcrossDaylightSavings(true).withIntervalInYears(1)).build();

            scheduler.scheduleJob(dailyJobDetail, dailyTrigger);
            scheduler.scheduleJob(monthlyJobDetail, monthlyTrigger);
            scheduler.scheduleJob(minuteJobDetail, minuteTrigger);
            scheduler.scheduleJob(yearlyJobDetail, yearlyTrigger);
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
            FileWriter monthChartWriter = new FileWriter(monthChartDataFile);
            StringBuilder s = new StringBuilder();
            for(DataNodeList d : monthNodeLists){
                s.append(d.toWrite());
            }
            monthChartWriter.write(s.toString());
            monthChartWriter.close();

            FileWriter yearChartWriter = new FileWriter(yearChartDataFile);
            StringBuilder sr = new StringBuilder();
            for (DataNodeList d : yearNodeLists){
                s.append(d.toWrite());
            }
            yearChartWriter.write(sr.toString());
            yearChartWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJarf(){
        try {
            FileWriter jarfStatWriter = new FileWriter(JarfStatFile);
            StringBuilder s = new StringBuilder();
            for(JarfList j : jarfLists){
                s.append(j.toWrite());
            }
            jarfStatWriter.write(s.toString());
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
        if(mainPane.getChildren().contains(birthDayPane)) mainPane.getChildren().remove(birthDayPane);

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
        if(mainPane.getChildren().contains(birthDayPane)) mainPane.getChildren().remove(birthDayPane);

        AnchorPane.setTopAnchor(statistiekPane, (double)125);
        mainPane.getChildren().add(statistiekPane);

        extrasViewController.setData();
        sleepTimerUpdate();
    }

    public void bierverliesView(ActionEvent event){
        if(mainPane.getChildren().contains(bierVerliesPane)) return;
        if(mainPane.getChildren().contains(turfBeerPane)) mainPane.getChildren().remove(turfBeerPane);
        if(mainPane.getChildren().contains(statistiekPane)) mainPane.getChildren().remove(statistiekPane);
        if(mainPane.getChildren().contains(instellingenPane)) mainPane.getChildren().remove(instellingenPane);
        if(mainPane.getChildren().contains(transactionPane)) mainPane.getChildren().remove(transactionPane);
        if(mainPane.getChildren().contains(turfKratPane)) mainPane.getChildren().remove(turfKratPane);
        if(mainPane.getChildren().contains(birthDayPane)) mainPane.getChildren().remove(birthDayPane);

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
        if(mainPane.getChildren().contains(birthDayPane)) mainPane.getChildren().remove(birthDayPane);

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
        if(mainPane.getChildren().contains(birthDayPane)) mainPane.getChildren().remove(birthDayPane);

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
        if(mainPane.getChildren().contains(birthDayPane)) mainPane.getChildren().remove(birthDayPane);

        AnchorPane.setTopAnchor(turfKratPane, (double)125);
        mainPane.getChildren().add(turfKratPane);

        turfKratViewController.setAllStocks();
        turfKratViewController.setNames();
        sleepTimerUpdate();
    }

    public void birthDayView(){
        if(mainPane.getChildren().contains(birthDayPane)) return;
        if(mainPane.getChildren().contains(turfBeerPane)) mainPane.getChildren().remove(turfBeerPane);
        if(mainPane.getChildren().contains(statistiekPane)) mainPane.getChildren().remove(statistiekPane);
        if(mainPane.getChildren().contains(bierVerliesPane)) mainPane.getChildren().remove(bierVerliesPane);
        if(mainPane.getChildren().contains(instellingenPane)) mainPane.getChildren().remove(instellingenPane);
        if(mainPane.getChildren().contains(transactionPane)) mainPane.getChildren().remove(transactionPane);
        if(mainPane.getChildren().contains(turfKratPane)) mainPane.getChildren().remove(turfKratPane);

        AnchorPane.setTopAnchor(birthDayPane, (double)125);
        mainPane.getChildren().add(birthDayPane);

        birthDayViewController.setData();
        sleepTimerUpdate();
    }
 }
