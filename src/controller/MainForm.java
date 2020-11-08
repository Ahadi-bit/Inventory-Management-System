package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainForm {

    Stage stage;
    Parent scene;

    @FXML
    private TableView<?> partsTable;

    @FXML
    private TableColumn<?, ?> PartIdCol;

    @FXML
    private TableColumn<?, ?> PartNameCol;

    @FXML
    private TableColumn<?, ?> InvLvlCol;

    @FXML
    private TableColumn<?, ?> PartsPerUnitCol;

    @FXML
    private TableView<?> productsTable;

    @FXML
    private TableColumn<?, ?> ProductIdCol;

    @FXML
    private TableColumn<?, ?> ProductNameCol;

    @FXML
    private TableColumn<?, ?> productInvLvlCol;

    @FXML
    void OnActionAddParts(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionAddProducts(ActionEvent event) throws IOException{
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionDeletePart(ActionEvent event) {

    }

    @FXML
    void OnActionDeleteProducts(ActionEvent event) {

    }

    @FXML
    void OnActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void OnActionModifyParts(ActionEvent event) throws IOException{
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyPartForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionModifyProducts(ActionEvent event) throws IOException{
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
}
