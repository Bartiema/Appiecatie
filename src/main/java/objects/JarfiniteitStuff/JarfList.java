package objects.JarfiniteitStuff;

import javafx.scene.chart.XYChart;
import objects.AccountStuff.AccountList;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class JarfList {
    private String owner;
    private LinkedList<Date> jarfList;

    public JarfList(String account){
        owner = account;
        jarfList = new LinkedList<>();
    }

    public void setOwner(String s){
        owner = s;
    }

    public LinkedList<Date> getAll(){
        return jarfList;
    }

    public String getOwner() {
        return owner;
    }

    public void addJarf(){
        jarfList.addFirst(new Date());
    }

    public void add(Date date){
        jarfList.add(date);
    }

    public void removeJarf(){
        jarfList.removeFirst();
    }

    public Date peek(){
        return jarfList.peek();
    }

    public int nrJarfs(){
        return jarfList.size();
    }

    public Jarf getLastJarf() throws ParseException {
       if(jarfList.peek() == null){
           SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
           return new Jarf(owner, format.parse("01-01-0001"));
       } else {
           return new Jarf(owner, jarfList.peek());
       }
    }

    public static LinkedList<JarfList> toRead(File file, AccountList accounts) throws FileNotFoundException, ParseException {
        LinkedList<JarfList> res = new LinkedList<>();
        Scanner sc = new Scanner(file);
        JarfList jarfList = null;
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

        while(sc.hasNextLine()){
            String s = sc.nextLine();

            if(accounts.containsBasedOnName(s)){
                if(jarfList != null) { res.add(jarfList); }
                jarfList = new JarfList(s);
            } else {
                jarfList.add(format.parse(s));
            }
        }
        res.add(jarfList);
        return res;
    }

    public String toWrite(){
        StringBuilder s = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        s.append(owner + "\n");
        for(Date d : jarfList){
            s.append(format.format(d) + "\n");
        }
        return s.toString();
    }

    public XYChart.Series getXYChartSeries() {
        XYChart.Series<String, Integer> res = new XYChart.Series<>();
        res.setName(this.owner);
        int maandag = 0;
        int dinsdag = 0;
        int woensdag = 0;
        int donderdag = 0;
        int vrijdag = 0;
        int zaterdag = 0;
        int zondag = 0;

        for(Date d : jarfList){
            if(d.getDay() == 1) maandag++;
            if(d.getDay() == 2) dinsdag++;
            if(d.getDay() == 3) woensdag++;
            if(d.getDay() == 4) donderdag++;
            if(d.getDay() == 5) vrijdag++;
            if(d.getDay() == 6) zaterdag++;
            if(d.getDay() == 0) zondag++;
        }
        System.out.println(maandag + " " + dinsdag + " " + woensdag + " " + donderdag + " " + vrijdag + " " + zaterdag + " " + zondag);
        XYChart.Data<String, Integer> maData =  new XYChart.Data("Maandag" ,maandag);
        XYChart.Data<String, Integer> dinData =  new XYChart.Data("Dinsdag" ,dinsdag);
        XYChart.Data<String, Integer> woeData =  new XYChart.Data("Woensdag" ,woensdag);
        XYChart.Data<String, Integer> donData =  new XYChart.Data("Donderdag" ,donderdag);
        XYChart.Data<String, Integer> vrijData =  new XYChart.Data("Vrijdag" ,vrijdag);
        XYChart.Data<String, Integer> zatData =  new XYChart.Data("Zaterdag" ,zaterdag);
        XYChart.Data<String, Integer> zonData =  new XYChart.Data("Zondag" ,zondag);

        res.getData().addAll(maData, dinData, woeData, donData, vrijData, zatData, zonData);
        return res;
    }
}
