package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

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
        Part a1 = new InHouse(1,"Part A1",2.99,10,100,101,3);
        Part a2 = new InHouse(2,"Part A2",4.99,6,100,101,4);
        Part b = new InHouse(3,"Part B",7.99,3,100,102,5);
        Part c1 = new Outsourced(1,"Part C1",7.99,3,99,102, "IBM");
        Part c2 = new Outsourced(2,"Part C2",9.99,8,100,102,"GameStop");
        Part d = new Outsourced(3,"Part D",21.99,2,89,102, "Aldi");

        Product prod1 = new Product(1,"PROD 1",10.99,7,101,105);
        Product prod2 = new Product(2,"PROD 2",8.99,3,77,18);
        Product prod3 = new Product(3,"PROD 3",12.99,2,32,55);

        inv.addPart(a1);
        inv.addPart(a2);
        inv.addPart(b);
        inv.addPart(c1);
        inv.addPart(c2);
        inv.addPart(d);

        inv.addProduct(prod1);
        inv.addProduct(prod2);
        inv.addProduct(prod3);

    }
}
