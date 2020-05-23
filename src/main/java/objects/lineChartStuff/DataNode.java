package objects.lineChartStuff;

import javafx.scene.chart.XYChart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DataNode {
    private int totalDrank;
    private Date dateInMonth;

    public int getTotalDrank() {
        return totalDrank;
    }

    public Date getDateInMonth() {
        return dateInMonth;
    }

    /**
     * Constructor for Read versions
     * @param totalDrank - The Amount Drank
     * @param dateInMonth - The date of the Month this node is made in
     */
    public DataNode(int totalDrank, Date dateInMonth) {

        this.totalDrank = totalDrank;
        this.dateInMonth = dateInMonth;
    }

    /**
     * Constructor for new DataNodes
     * @param totalDrank - The Amount Drank
     */
    public DataNode(int totalDrank) {
        this.totalDrank = totalDrank;
        this.dateInMonth = new Date();
    }

    public String toWrite() {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        return  totalDrank + " - " +
                format.format(dateInMonth);
    }

    public static DataNode toRead(String line) throws ParseException {
        System.out.println(line);
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(" - ");

        int totalDrank = scanner.nextInt();
        String date = scanner.next();

        SimpleDateFormat format = new SimpleDateFormat("dd");
        Date dateInMonth = format.parse(date);

        return new DataNode(totalDrank, dateInMonth);
    }

    public XYChart.Data<Integer, Date> getXYChartData() {
        return new XYChart.Data<Integer, Date>(totalDrank, dateInMonth);
    }
}
