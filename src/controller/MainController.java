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

    // Parts Pane
    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, Integer> PartIdCol;
    @FXML private TableColumn<Part, String> PartNameCol;
    @FXML private TableColumn<Part, Integer> InvLvlCol;
    @FXML private TableColumn<Part, Double> PartsPerUnitCol;
    @FXML private TextField searchParttxt;

    //Products Pane
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, Integer> ProductIdCol;
    @FXML private TableColumn<Product, String> ProductNameCol;
    @FXML private TableColumn<Product, Integer> productInvLvlCol;
    @FXML private TableColumn<Product, Double> productPerUnitCol;
    @FXML private TextField SearchProdtxt;

    /** This method opens loads the appPartForm scene*/
    @FXML void OnActionAddParts(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method opens loads the appProductForm scene*/
    @FXML void OnActionAddProducts(ActionEvent event) throws IOException{
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**This method removes selected part.
     * This method removes selected part from partsTable and will also remove the item from the inventory* */
    @FXML void OnActionDeletePart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete item?");
        alert.setTitle("Confirm");
        if(partsTable.getSelectionModel().getSelectedItem() != null){
            Optional<ButtonType> result = alert.showAndWait();
            if( result.get() == ButtonType.OK){
                Inventory.deletePart(partsTable.getSelectionModel().getSelectedItem());
            }
        }

    }

    /**This method removes selected Product.
     * This method removes selected Product from productsTable and will also remove the item from the inventory* */
    @FXML void OnActionDeleteProducts(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete item?");
        alert.setTitle("Confirm");
        if(productsTable.getSelectionModel().getSelectedItem() != null){
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                Inventory.deleteProduct(productsTable.getSelectionModel().getSelectedItem());
            }
        }
    }


    /** This method opens the modify parts scene.
     * This method also calls the sendSelectedItem method to populate txtfields.* */
    @FXML public void OnActionModifyParts(ActionEvent event) throws IOException{

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

    /** This method opens the modify products scene.
     * This method also calls the sendSelectedItem method to populate txtfields and associates parts table.* */
    @FXML public void OnActionModifyProducts(ActionEvent event) throws IOException{

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
            MDFController.sendSelectedItem(productsTable.getSelectionModel().getSelectedItem());

            Scene scene = new Scene(root);
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }



    }

    /** Search Functionality for Parts Table* */
    @FXML public void OnActionSearchParts(ActionEvent event) {
        ObservableList<Part> partToSearch = FXCollections.observableArrayList();
            try{
                int idToSearch = Integer.parseInt(searchParttxt.getText());
                if(Inventory.lookupPart(idToSearch) == null){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText("Item does not exist");
                    error.showAndWait();
                    partsTable.setItems(Inventory.getAllParts());
                }else {
                    partToSearch.add(Inventory.lookupPart(idToSearch));
                    if(partToSearch.size() == 0){
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setTitle("error");
                        error.setContentText("Item is empty");
                        error.show();
                        partsTable.setItems(Inventory.getAllParts());

                    }else{
                        partsTable.setItems(partToSearch);
                        partsTable.refresh();
                    }
                }

            }catch (Exception e){
                String nameToSearch = searchParttxt.getText();
                partToSearch = Inventory.lookupPart(nameToSearch);

                if(partToSearch.size() == 0){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("error");
                    error.setContentText("Item is empty");
                    error.show();
                    partsTable.setItems(Inventory.getAllParts());

                }else{
                    partsTable.setItems(partToSearch);
                    partsTable.refresh();
                }
            }
    }

    /** Search Functionality for Products Table* */
    @FXML public void OnActionSearchProd(ActionEvent event) {
        ObservableList<Product> productToSearch = FXCollections.observableArrayList();
        try{
            int idToSearch = Integer.parseInt(SearchProdtxt.getText());
            if(Inventory.lookupProduct(idToSearch) == null){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Item does not exist");
                error.showAndWait();
                productsTable.setItems(Inventory.getAllProducts());
            }else {
                    productToSearch.add(Inventory.lookupProduct(idToSearch));
                if(productToSearch.size() == 0){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("error");
                    error.setContentText("Item is empty");
                    error.show();
                    productsTable.setItems(Inventory.getAllProducts());

                }else{
                    productsTable.setItems(productToSearch);
                    productsTable.refresh();
                }
            }

        }catch (Exception e){
            String nameToSearch = SearchProdtxt.getText();
            productToSearch = Inventory.lookupProduct(nameToSearch);

            if(productToSearch.size() == 0){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("error");
                error.setContentText("Item is empty");
                error.show();
                productsTable.setItems(Inventory.getAllProducts());

            }else{
                productsTable.setItems(productToSearch);
                productsTable.refresh();
            }
        }
    }

    /** Exits progra,*/
    @FXML public void OnActionExit(ActionEvent event) {
        System.exit(0);
    }

    /** Used to initialize the part and products table */
    @Override public void initialize(URL url, ResourceBundle rb) {
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
