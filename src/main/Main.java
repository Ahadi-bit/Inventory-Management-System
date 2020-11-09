package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Inventory inv = new Inventory();
        addTestData(inv);

        Parent root = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop(){
        System.out.println("Terminated!!!");
    }


    public static void main(String[] args) {
        launch(args);
    }

    void addTestData(Inventory inv){
        Part a1 = new InHouse(1,"Part A1",2.99,10,100,101);
        Part a2 = new InHouse(1,"Part A2",4.99,6,100,101);
        Part b = new InHouse(1,"Part B",7.99,3,100,102);

        inv.addPart(a1);
    }
}
