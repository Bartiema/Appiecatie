package objects.lineChartStuff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import objects.AccountStuff.Account;
import objects.AccountStuff.AccountList;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class DataNodeList {
    private Account dataOwner;
    private LinkedList<DataNode> dataNodes;

    public Account getDataOwner() {
        return dataOwner;
    }

    public LinkedList<DataNode> getDataNodes() {
        return dataNodes;
    }

    public DataNodeList(Account account) {
        this.dataOwner = account;
        dataNodes = new LinkedList<>();
    }

    public void add(DataNode dataNode){
        dataNodes.add(dataNode);
    }

    public String toWrite() {
        StringBuilder s1 = new StringBuilder();
        s1.append(dataOwner.getName()).append("\n");
        for(DataNode a : dataNodes) {
            s1.append(a.toWrite()).append("\n");
        }
        return s1.toString();
    }

    public static LinkedList<DataNodeList> toRead(AccountList accountList, File file) throws FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(file);
        LinkedList<DataNodeList> res = new LinkedList<>();
        DataNodeList dataNodeList = null;


        while(scanner.hasNext()) {
            String s = scanner.nextLine();

            if(accountList.containsBasedOnName(s)){
                if(dataNodeList!=null) res.add(dataNodeList);
                for(Account a : accountList) if (s.equals(a.getName())) dataNodeList = new DataNodeList(a);
            } else {
                dataNodeList.add(DataNode.toRead(s));
            }
        }
        return res;
    }

    public XYChart.Series<Integer, Date> getXYChartSeries() {
        LinkedList<XYChart.Data<Integer, Date>> XYdataNodes = new LinkedList<>();
        for(DataNode d : dataNodes){
            XYdataNodes.add(d.getXYChartData());
        }

        ObservableList<XYChart.Data<Integer, Date>> XYChartSeriesObservableList = FXCollections.observableArrayList(XYdataNodes);

        return new XYChart.Series<Integer, Date>(dataOwner.getName(), XYChartSeriesObservableList);
    }
}
