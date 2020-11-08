package controller;


import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartForm {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton inHousebtn;

    @FXML
    private RadioButton outSourcedbtn;

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
    void OnActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionSave(ActionEvent event) {

    }
}
