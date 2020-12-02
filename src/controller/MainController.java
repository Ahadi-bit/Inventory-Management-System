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
    @FXML private TableColumn<Part, Integer> partIdCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, Integer> invLvlCol;
    @FXML private TableColumn<Part, Double> partsPerUnitCol;
    @FXML private TextField searchParttxt;

    //Products Pane
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, Integer> productIdCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, Integer> productInvLvlCol;
    @FXML private TableColumn<Product, Double> productPerUnitCol;
    @FXML private TextField searchProdtxt;


    /** This method opens loads the appPartForm scene
     * when action is performed on add button on the parts pane user will open the addpart scene
     *
     * @throws IOException for Scene transition
     * @param event event for when the add button in the parts pane is clicked which should perform this method.
     * */
    @FXML private void OnActionAddParts(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method opens loads the appProductForm scene
     * when action is performed on add button on the products pane user will open the addproduct scene.
     *
     * @throws IOException for Scene transition
     * @param event event for when the add button in the product pane is clicked which should perform this method.
     * */
    @FXML private void OnActionAddProducts(ActionEvent event) throws IOException{
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**This method removes selected part.
     * when action is performed on the delete parts pane it will remove the item that is selected.
     * User will first hit a dialog to confirm delete of said item. once confirmed item will be deleted.
     *
     *@param event event for when the delete button in the product pane is clicked which should perform this method*/
    @FXML private void OnActionDeletePart(ActionEvent event) {
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
     * This method removes selected Product from productsTable and will also remove the item from the inventory.
     * User will first hit a dialog to confirm delete of said item. once confirmed item will be deleted.
     *
     *@param event event for when the delete button in the products pane is clicked which should perform this method */
    @FXML private void OnActionDeleteProducts(ActionEvent event) {
        Product prod;
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
     * when action is performed on modify button located on the parts pane, user will open the modify part scene. User will face a dialog if not item is selected prior to hitting this button.
     *
     * @throws IOException for Scene transition
     *@param event event for when the modify button in the parts pane is clicked which should perform this method* */
    @FXML private void OnActionModifyParts(ActionEvent event) throws IOException{

        boolean ifNotSelected = partsTable.getSelectionModel().isEmpty();
        if(ifNotSelected){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("error");
            error.setContentText("No item selected");
            error.show();
        }else{
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



    }

    /** This method opens the modify products scene.
     * when action is performed on modify button located on the products pane, user will open the modify product scene. User will face a dialog if not item is selected prior to hitting this button.
     *
     *  @throws IOException for Scene transition
     *  @param event event for when the modify button in the products pane is clicked which should perform this method * */
    @FXML private void OnActionModifyProducts(ActionEvent event) throws IOException{

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

    /** Search Functionality for Parts Table
     * when the item is clicked the user will be able to search item by part ID or by part name. if the user types incorrectly than an error dialog will pop up reading
     *"Item does not exist". If user clicks the button with nothing in the txtfield, then the dialog box will read "Item is empty".I decided to wrap everything with a try.
     *This is how I am handling if the item is either a productname vs a productID.
     *
     * @param event event for when the search button in the parts pane is clicked which should perform this method.
     * * */
    @FXML private void OnActionSearchParts(ActionEvent event) {
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

    /** Search Functionality for Products Table*
     * when the item is clicked the user will be able to search item by product ID or by product name. if the user types incorrectly than an error dialog will pop up reading
     *"Item does not exist". If user clicks the button with nothing in the txtfield, then the dialog box will read "Item is empty". I decided to wrap everything with a try.
     * This is how I am handling if the item is either a productname vs a productID.
     *
     *@param event event for when the search button in the products pane is clicked which should perform this method.
     * */
    @FXML private void OnActionSearchProd(ActionEvent event) {
        ObservableList<Product> productToSearch = FXCollections.observableArrayList();
        try{
            int idToSearch = Integer.parseInt(searchProdtxt.getText());
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
            String nameToSearch = searchProdtxt.getText();
            productToSearch = Inventory.lookupProduct(nameToSearch);

            if(productToSearch.size() == 0){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("error");
                error.setContentText("Not Item Found");
                error.show();
                productsTable.setItems(Inventory.getAllProducts());

            }else{

                productsTable.setItems(productToSearch);
                productsTable.refresh();
            }
        }


    }

    /**Exits program
     *
     * @param event event for when the search exit button is clicked which should perform this method.
     * */
    @FXML private void OnActionExit(ActionEvent event) {
        System.exit(0);
    }

    /** Used to initialize the part and products table
     * @param url url is used for resolving the path for the root object
     * @param rb rb is used to localize the root object
     * */
    @Override public void initialize(URL url, ResourceBundle rb) {
        partsTable.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTable.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}
