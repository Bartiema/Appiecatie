package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.AccountStuff.Account;
import objects.AccountStuff.AccountList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views/mainView.fxml"));
        primaryStage.setTitle("24 Bravo");
        primaryStage.setScene(new Scene(root, 1280, 956));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {

        FileWriter writer = new FileWriter(new File("src/main/resources/files/Accounts"));
        Account a2 = new Account("B3nni3");
        Account a3 = new Account("Harry");
        Account a4 = new Account("Bibi");
        Account a5 = new Account("Wiski");
        Account a6 = new Account("HJ");
        Account a1 = new Account("Josso");
        AccountList l1 = new AccountList();
        l1.add(a1);
        l1.add(a2);
        l1.add(a3);
        l1.add(a4);
        l1.add(a5);
        l1.add(a6);
        writer.write(l1.toWrite());
        writer.close();

        launch(args);
    }
}
