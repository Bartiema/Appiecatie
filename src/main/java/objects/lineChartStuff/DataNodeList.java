package objects.lineChartStuff;

import javafx.scene.chart.XYChart;
import objects.AccountStuff.Genoot;
import objects.AccountStuff.GenotenList;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class DataNodeList {
    private Genoot dataOwner;
    private LinkedList<DataNode> dataNodes;

    public Genoot getDataOwner() {
        return dataOwner;
    }

    public LinkedList<DataNode> getDataNodes() {
        return dataNodes;
    }

    public DataNodeList(Genoot account) {
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

    public static LinkedList<DataNodeList> toRead(GenotenList accountList, File file) throws FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(file);
        LinkedList<DataNodeList> res = new LinkedList<>();
        DataNodeList dataNodeList = null;


        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();

            if(accountList.containsBasedOnName(s)){
                if(dataNodeList!=null) res.add(dataNodeList);
                for(Genoot a : accountList.getGenotenlist()) if (s.equals(a.getName())) dataNodeList = new DataNodeList(a);
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

    public void removeOnDate(int date) {
        for(DataNode d : dataNodes)
            if (d.getDateNr() == date) {
                dataNodes.remove(d);
                break;
            }
    }

    public int getDrankOnDate(int date){
        for (int i = 0; i < dataNodes.size(); i++) {
            if(dataNodes.get(i).getDateNr() == date) {
                return dataNodes.get(i).getTotalDrank();
            }
        }
      return 0;
    }

    public void update(int drank , int nrDate){
        boolean contains = false;
        for(DataNode d : dataNodes)
            if (d.getDateNr() == nrDate) {
                contains = true;
                break;
            }
        if(contains) this.removeOnDate(nrDate);
        this.add(new DataNode(drank, nrDate));
    }
}
