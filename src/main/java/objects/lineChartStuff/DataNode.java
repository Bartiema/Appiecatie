package objects.lineChartStuff;

import javafx.scene.chart.XYChart;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class DataNode {
    private int totalDrank;
    private int nrInMonth;

    public int getTotalDrank() {
        return totalDrank;
    }

    public int getDateInMonth() {
        return nrInMonth;
    }

    /**
     * Constructor for Read versions
     * @param totalDrank - The Amount Drank
     * @param nrInMonth - The date of the Month this node is made in
     */
    public DataNode(int totalDrank, int nrInMonth) {

        this.totalDrank = totalDrank;
        this.nrInMonth = nrInMonth;
    }

    /**
     * Constructor for new DataNodes
     * @param totalDrank - The Amount Drank
     */
    public DataNode(int totalDrank) {
        this.totalDrank = totalDrank;
        this.nrInMonth = new Date().getDate();
    }

    public String toWrite() {
        return  totalDrank + " - " + nrInMonth;
    }

    public static DataNode toRead(String line) throws ParseException {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(" - ");

        int totalDrank = scanner.nextInt();
        int nrInMonth = scanner.nextInt();


        return new DataNode(totalDrank, nrInMonth);
    }

    public XYChart.Data getXYChartData() {
        return new XYChart.Data(nrInMonth ,totalDrank);
    }
}
