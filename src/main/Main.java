package main;
/**
 * Main application
 */

/**
 *
 * @author Jonathan Payarers
 */



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;


/**Main class**/
public class Main extends Application {


    /**This method is the main entry point of my javafx application.
     here is where my application is telling my mainform is display first. it is also where my data is initially populated.
     * */
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /** This is the Main method.
     Launches the application
     * */
    public static void main(String[] args) {
        launch(args);
    }

}
