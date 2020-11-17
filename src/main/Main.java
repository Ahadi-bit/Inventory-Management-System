package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;


/** Main class runs the mainScreen Controller**/
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

    /** This is the Main method. */
    public static void main(String[] args) {
        launch(args);
    }
    /** This method populates populates the Parts and product.
     This adds the initial data in the Parts and Product table views when application in ran.
     @param inv This passes the Inventory model so new data can be passed
     */
    public void addTestData(Inventory inv){
        //Add InHouse Parts
        Part a1 = new InHouse(1, "Part A1", 2.99, 10, 5, 100, 101);
        Part a2 = new InHouse(3, "Part A2", 4.99, 11, 5, 100, 103);
        Part b = new InHouse(2, "Part B", 3.99, 9, 5, 100, 102);
        inv.addPart(a1);
        inv.addPart(b);
        inv.addPart(a2);
        inv.addPart(new InHouse(4, "Part A3", 5.99, 15, 5, 100, 104));
        inv.addPart(new InHouse(5, "Part A4", 6.99, 5, 5, 100, 105));
        //Add OutSourced Parts
        Part o1 = new Outsourced(6, "Part O1", 2.99, 10, 5, 100, "ACME Co.");
        Part p = new Outsourced(7, "Part P", 3.99, 9, 5, 100, "ACME Co.");
        Part q = new Outsourced(8, "Part Q", 2.99, 10, 5, 100, "FLORIDA Co.");
        inv.addPart(o1);
        inv.addPart(p);
        inv.addPart(q);
        inv.addPart(new Outsourced(9, "Part R", 2.99, 10, 5, 100, "FLORIDA Co."));
        inv.addPart(new Outsourced(10, "Part O2", 2.99, 10, 5, 100, "NY Co."));
        //Add allProducts
        Product product1 = new Product(1, "ProductOne", 9.99, 15, 10, 20);
        product1.addAssociatedPart(o1);
        product1.addAssociatedPart(p);
        Product product2 = new Product(2, "ProductTwo", 9.99, 15, 10, 20);
        product2.addAssociatedPart(b);
        product2.addAssociatedPart(a2);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);

    }
}
