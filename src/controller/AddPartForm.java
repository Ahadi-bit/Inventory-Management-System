package controller;


import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Outsourced;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AddPartForm implements Initializable {

    Stage stage;
    Parent scene;
    Random rand = new Random();

    @FXML
    private RadioButton inHousebtn;

    @FXML
    private ToggleGroup partType;

    @FXML
    private RadioButton outSourcedbtn;

    @FXML
    private TextField IdAutoGenTXT;

    @FXML
    private TextField addPartNametxt;

    @FXML
    private TextField addPartInvtxt;

    @FXML
    private TextField addPartPricetxt;

    @FXML
    private TextField addPartMaxtxt;

    @FXML
    private TextField addPartMachineIdtxt;

    @FXML
    private TextField addPartMintxt;

    @FXML
    private Label dynamiclbl;

    @FXML
    void OnClickInHouse(MouseEvent event) {
        this.dynamiclbl.setText("Machine ID");
        System.out.println("in house btn");
    }

    @FXML
    void OnClickOutSource(MouseEvent event) {
        this.dynamiclbl.setText("Company Name");
        System.out.println("Outsourced");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partType = new ToggleGroup();
        inHousebtn.setToggleGroup(partType);
        outSourcedbtn.setToggleGroup(partType);
    }



    @FXML
    void OnActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    public int assignPartNumber(){
        int randPartID = rand.nextInt();
        if (randPartID > 1 || randPartID < 999999){
            randPartID = rand.nextInt();
        }
        return randPartID;
    }

    @FXML
    void OnActionSave(ActionEvent event) {


    }
}
