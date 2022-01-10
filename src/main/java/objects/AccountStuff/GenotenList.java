package objects.AccountStuff;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Scanner;

public class GenotenList {
    private LinkedList<Genoot> genotenlist;
    private int totalStock;

    public GenotenList(){
        genotenlist = new LinkedList<>();
        totalStock = 0;
    }

    public GenotenList(File file, boolean old) throws FileNotFoundException, ParseException {
        genotenlist = new LinkedList<>();
        this.toRead(file, old);
        totalStock = 0;
        this.setTotalStock();
    }

    public LinkedList<Genoot> getGenotenlist() {
        return this.genotenlist;
    }

    public int getTotalStock() {
        return  this.totalStock;
    }

    public Genoot get(int index){
        return this.genotenlist.get(index);
    }

    public void add(Genoot g){
        genotenlist.addLast(g);
    }

    public void sort() {
        genotenlist.sort(Genoot::compareTo);
    }

    public void setTotalStock() {
        int newTotal = 0;
        for (Genoot g : genotenlist){
            if (g instanceof HuisGenoot) {
                HuisGenoot h = (HuisGenoot) g;
                newTotal += h.getStock();
            }
        }
        totalStock = newTotal;
    }

    public void tableUpdate() {
        for (Genoot g : genotenlist){
            if (g instanceof HuisGenoot) {
                HuisGenoot h = (HuisGenoot) g;
                h.update();
            }
        }
    }

    public void toRead(File file, boolean old) throws FileNotFoundException, ParseException {
       Scanner scan = new Scanner(file);
       scan.useDelimiter(" - ");
       while (scan.hasNextLine()) {
           if(old) genotenlist.add(OudGenoot.toRead(scan));
           else genotenlist.add(HuisGenoot.toRead(scan));
           scan.nextLine();
       }
       scan.close();
    }

    public String toWrite() {
        StringBuilder res = new StringBuilder();
        for (Genoot g: genotenlist) {
            if(g instanceof HuisGenoot) {
                HuisGenoot h = (HuisGenoot) g;
                res.append(h.toWrite()).append("\n");
            } else {
                OudGenoot h = (OudGenoot) g;
                res.append(h.toWrite()).append("\n");
            }
        }
        return res.toString();
    }
}
