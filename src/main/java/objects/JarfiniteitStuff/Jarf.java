package objects.JarfiniteitStuff;

import java.util.Date;

public class Jarf implements Comparable<Jarf> {
    private String name;
    private Date date;

    public Jarf(String name, Date date){
        this.name = name;
        this.date = date;
    }

    public String getName(){
        return name;
    }

    public Date getDate(){
        return date;
    }

    @Override
    public int compareTo(Jarf o) {
        if(this.date == null) return 1;
        if(o == null) return -1;
        else return o.getDate().compareTo(this.getDate());
    }
}
