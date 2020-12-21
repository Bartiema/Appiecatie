package objects.lineChartStuff;

import javafx.scene.chart.XYChart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DataNode {
    private int totalDrank;
    private int nrDate;

    public int getTotalDrank() {
        return totalDrank;
    }

    public int getDateNr() {
        return nrDate;
    }

    /**
     * Constructor
     * @param totalDrank - The Amount Drank
     * @param nrDate - The date of the Month this node is made in
     */
    public DataNode(int totalDrank, int nrDate) {

        this.totalDrank = totalDrank;
        this.nrDate = nrDate;
    }


    public String toWrite() {
        return  totalDrank + " - " + nrDate;
    }

    public static DataNode toRead(String line) throws ParseException {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(" - ");

        int totalDrank = scanner.nextInt();
        int nrInMonth = scanner.nextInt();


        return new DataNode(totalDrank, nrInMonth);
    }

    public XYChart.Data getXYChartData() {
        return new XYChart.Data(nrDate ,totalDrank);
    }
}
