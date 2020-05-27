package objects.JarfiniteitStuff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class JarfStat implements Comparable<JarfStat>{
    private String name;
    private Date date;
    private int quantity;

    public JarfStat(String name) {
        this.name = name;
        this.date = new Date();
        this.quantity = 1;
    }

    public JarfStat(String name, Date date, int quantity){
        this.name = name;
        this.date = date;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int compareTo(JarfStat o) {
        return o.date.compareTo(this.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JarfStat jarfStat = (JarfStat) o;
        return Objects.equals(name, jarfStat.name) &&
                Objects.equals(date, jarfStat.date);
    }

    public void update(){
        quantity++;
        this.date = new Date();
    }

    public String toWrite(){
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssZ", new Locale("nl"));
        return name + " - " +
                format.format(date) + " - " +
                quantity;
    }

    public static JarfStat toRead(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssZ", new Locale("nl"));
        Scanner scanner = new Scanner(s);
        scanner.useDelimiter(" - ");

        String name = scanner.next();
        Date date = format.parse(scanner.next());
        int qty = scanner.nextInt();
        scanner.close();

        return new JarfStat(name, date, qty);
    }
}
