package objects.JarfiniteitStuff;

import java.util.Date;

public class Jarf {
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
}
