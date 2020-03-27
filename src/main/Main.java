package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.AccountList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException, ParseException {
        File file = new File("Accounts");

        AccountList accountList = new AccountList();
        accountList.toRead(file);
        FileWriter writer = new FileWriter(file);

        accountList.updateAll();
        accountList.updateTotalStock();

        //launch(args);

        writer.write(accountList.toWrite());
        writer.close();
    }
}
