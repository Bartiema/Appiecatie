package objects.JarfiniteitStuff;

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

    public Jarf getLastJarf(){
        return new Jarf(owner, jarfList.peek());
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

}
