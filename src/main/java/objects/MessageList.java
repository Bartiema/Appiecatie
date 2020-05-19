package objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MessageList {
    private LinkedList<String> stringList;

    public MessageList(){
        stringList = new LinkedList<>();
    }

    public List<String> getStringList(){
        return this.stringList;
    }

    public void toRead(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            stringList.add(scanner.nextLine());
        }
    }

    public String get(int index){
        if(index>=stringList.size()){
            return "AppieZicht";
        } else {
            return stringList.get(index);
        }
    }

    public int size(){
        return stringList.size();
    }

    public void add(String s){
        stringList.add(s);
    }

}
