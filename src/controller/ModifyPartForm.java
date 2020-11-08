package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyPartForm {
    Stage stage;
    Parent scene;

    @FXML
    private RadioButton modifyPartInhousebtn;

    @FXML
    private RadioButton modifyPartOutSourcedbtn;

    @FXML
    private TextField modifyPart;

    @FXML
    private TextField modifyPartInvtxt;

    @FXML
    private TextField modifyPartPricetxt;

    @FXML
    private TextField modifyPartMaxtxt;

    @FXML
    private TextField modifyPartMachineIdtxt;

    @FXML
    private TextField modifyPartMintxt;

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
