package controller;
/**
 * Main Controller
 */

/**
 *
 * @author Jonathan Payarers
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> PartIdCol;

    @FXML
    private TableColumn<Part, String> PartNameCol;

    @FXML
    private TableColumn<Part, Integer> InvLvlCol;

    @FXML
    private TableColumn<Part, Double> PartsPerUnitCol;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, Integer> ProductIdCol;

    @FXML
    private TableColumn<Product, String> ProductNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvLvlCol;

    @FXML
    private TableColumn<Product, Double> productPerUnitCol;

    @FXML
    private TextField searchProducttxt;

    @FXML
    private TextField searchParttxt;

    Inventory inv;

//    public MainController(Inventory inv) {
//        this.inv = inv;
//    }


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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete item?");
        alert.setTitle("Confirm");
        if(partsTable.getSelectionModel().getSelectedItem() != null){
            Optional<ButtonType> result = alert.showAndWait();
            if( result.get() == ButtonType.OK){
                Inventory.deletePart(partsTable.getSelectionModel().getSelectedItem());
            }
        }

    }

    @FXML
    void OnActionDeleteProducts(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete item?");
        alert.setTitle("Confirm");
        if(productsTable.getSelectionModel().getSelectedItem() != null){
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                Inventory.deleteProduct(productsTable.getSelectionModel().getSelectedItem());
            }
        }
    }

    @FXML
    void OnActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void OnActionModifyParts(ActionEvent event) throws IOException{

        boolean ifNotSelected = partsTable.getSelectionModel().isEmpty();

        if(ifNotSelected){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("error");
            error.setContentText("No item selected");
            error.show();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyPartForm.fxml"));
        loader.load();
        ModifyPartForm MDFController = loader.getController();
        MDFController.sendSelectedItem(partsTable.getSelectionModel().getSelectedItem());


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML
    public void OnActionModifyProducts(ActionEvent event) throws IOException{

        boolean ifNotSelected = productsTable.getSelectionModel().isEmpty();
        if(ifNotSelected){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("error");
            error.setContentText("No item selected");
            error.show();
        }
        else{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProductForm.fxml"));
            Parent root = loader.load();
            ModifyProductForm MDFController = loader.getController();
            MDFController.sendSelectedItem(productsTable.getSelectionModel().getSelectedItem(),inv);

            Scene scene = new Scene(root);
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }



    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partsTable.setItems(Inventory.getAllParts());
        PartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartsPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTable.setItems(Inventory.getAllProducts());
        ProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void OnActionSearchParts(ActionEvent event) {
        ObservableList searchedParts = Inventory.lookupPart(searchParttxt.getText());
        if(!searchParttxt.getText().trim().isEmpty()){
            partsTable.setItems(searchedParts);
        }
    }

    @FXML
    void OnActionSearchProducts(ActionEvent event) {
        ObservableList searchedProd = Inventory.lookupPart(searchProducttxt.getText());
        if(!searchProducttxt.getText().trim().isEmpty()){
            productsTable.setItems(searchedProd);
        }
    }
}
