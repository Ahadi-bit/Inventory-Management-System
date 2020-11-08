package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyProductForm {
    Stage stage;
    Parent scene;

    @FXML
    private TextField Nametxt;

    @FXML
    private TextField Invtxt;

    @FXML
    private TextField Pricetxt;

    @FXML
    private TextField Maxtxt;

    @FXML
    private TextField mintxt;

    @FXML
    private TableColumn<?, ?> partIdcol;

    @FXML
    private TableColumn<?, ?> partNamecol;

    @FXML
    private TableColumn<?, ?> invLvlcol;

    @FXML
    private TableColumn<?, ?> pricecol;

    @FXML
    private TableColumn<?, ?> associatedPartIDcol;

    @FXML
    private TableColumn<?, ?> associatedPartNamecol;

    @FXML
    private TableColumn<?, ?> associatedInvLvlcol;

    @FXML
    private TableColumn<?, ?> AssociatedPriceCol;

    @FXML
    private TextField searchtxt;

    @FXML
    void OnActionAdd(ActionEvent event) {

    }

    @FXML
    void OnActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionRemoveAssociated(ActionEvent event) {

    }

    @FXML
    void OnActionSave(ActionEvent event) {

    }

}
