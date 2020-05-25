package objects.lineChartStuff;

import javafx.scene.chart.XYChart;
import objects.AccountStuff.Account;
import objects.AccountStuff.AccountList;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
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
        res.add(dataNodeList);
        return res;
    }

    public XYChart.Series getXYChartSeries() {
        XYChart.Series res = new XYChart.Series();
        res.setName(dataOwner.getName());
        for(DataNode d : dataNodes){
            res.getData().add(d.getXYChartData());
        }

        return res;
    }

    public void removeAll(){
        while(!dataNodes.isEmpty()) dataNodes.remove();
    }
}
