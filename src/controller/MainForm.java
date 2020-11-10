package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainForm implements Initializable {

    Stage stage;
    Parent scene;
    Inventory inv = new Inventory();

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

    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private ObservableList<Product> productInventory = FXCollections.observableArrayList();
    private ObservableList<Part> partsInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Product> productInventorySearch = FXCollections.observableArrayList();


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
        partsTable.getItems().removeAll(partsTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void OnActionDeleteProducts(ActionEvent event) {
        Product removeProduct = productsTable.getSelectionModel().getSelectedItem();

        inv.deleteProduct(removeProduct.getId());
        productInventory.remove(removeProduct);
        productsTable.setItems(productInventory);
        productsTable.refresh();

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



}
