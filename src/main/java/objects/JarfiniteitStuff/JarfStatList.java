package objects.JarfiniteitStuff;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Scanner;

public class JarfStatList {
    private LinkedList<JarfStat> jarfStats;

    public JarfStatList() {
        jarfStats = new LinkedList<>();
    }

    public void add(JarfStat jarfStat) {
        jarfStats.add(jarfStat);
    }

    public JarfStat get(int index){
        return jarfStats.get(index);
    }

    public void removeAll(){
        for(JarfStat j: jarfStats) jarfStats.remove(j);
    }

    public void remove(JarfStat jarfStat){
        jarfStats.removeIf(j -> j.equals(jarfStat));
    }

    public void sort(){
        jarfStats.sort(JarfStat::compareTo);
    }

    public String toWrite() {
        StringBuilder s = new StringBuilder();
        for(JarfStat j : jarfStats){
            s.append(j.toWrite()).append("\n");
        }
        return s.toString();
    }

    public void toRead(File file) throws FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            jarfStats.add(JarfStat.toRead(scanner.nextLine()));
        }
        scanner.close();
    }

    public boolean contains(String name){
        for(JarfStat j: jarfStats) if(j.getName().equals(name)) return true;
        return false;
    }

    public LinkedList<JarfStat> getAll(){
        return jarfStats;
    }

    public JarfStat getOnName(String name){
        for(JarfStat j: jarfStats) if(j.getName().equals(name)) return j;
        return null;
    }
}
