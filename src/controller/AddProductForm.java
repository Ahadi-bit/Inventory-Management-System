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

public class AddProductForm {
    Stage stage;
    Parent scene;

    @FXML
    private TextField productIdTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField productInvTxt;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TableColumn<?, ?> PartIdCol;

    @FXML
    private TableColumn<?, ?> PartNameCol;

    @FXML
    private TableColumn<?, ?> partIventoryLevelCol;

    @FXML
    private TableColumn<?, ?> PartPricePerUnitCol;

    @FXML
    private TableColumn<?, ?> associatedPartIDCol;

    @FXML
    private TableColumn<?, ?> associatedPartNamecol;

    @FXML
    private TableColumn<?, ?> associatedInvLvlCol;

    @FXML
    private TableColumn<?, ?> AssociatedPartPricePerUnitCol;

    @FXML
    private TextField Searchtxt;

    @FXML
    void AddItem(ActionEvent event) {

    }

    @FXML
    void OnActionCancelProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionRemoveAssociatedPart(ActionEvent event) {

    }

    @FXML
    void OnActionSaveProducts(ActionEvent event) {

    }

    public void OnActionAddItem(ActionEvent actionEvent) {
    }
}
