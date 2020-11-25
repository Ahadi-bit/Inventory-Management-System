package controller;
/**
 * Add Product Form Controller
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
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductForm implements Initializable {
    Stage stage;
    Parent scene;

    //Text fields
    @FXML private TextField nametxt;
    @FXML private TextField invtxt;
    @FXML private TextField maxtxt;
    @FXML private TextField pricetxt;
    @FXML private TextField mintxt;
    @FXML private TextField searchtxt;

    //Top TableView
    @FXML private TableView<Part> allPartsTable;
    @FXML private TableColumn<Part, Integer> partIdCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, Integer> partIventoryLevelCol;
    @FXML private TableColumn<Part, Double> priceCol;

    //Bottom TableView
    @FXML private TableView<Part> associatedPartsTable;
    @FXML private TableColumn<Part, Integer> associatedPartICol;
    @FXML private TableColumn<Part, String> associatedPartNamecol;
    @FXML private TableColumn<Part, Integer> associatedInvLvlcol;
    @FXML private TableColumn<Part, Double> associatedPricecol;

    /** These Observable list are used to temporarily hold items before the item saves */
    private ObservableList<Part> associatedList = FXCollections.observableArrayList();
    private ObservableList<Part> allPartsList = FXCollections.observableArrayList();


    /** This method cancel the AddProductForm and then sends the user back to MainScreenController*/
    @FXML
    void OnActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method removes the the associated part to the*/
    @FXML
    void OnActionRemoveAssociatedPart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete item?");
        Alert error = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Confirm");
        if(associatedPartsTable.getSelectionModel().getSelectedItem() != null){

            Optional<ButtonType> result = alert.showAndWait();
            if(((Optional<?>)result).isPresent() && result.get() == ButtonType.OK){
                associatedList.remove(associatedPartsTable.getSelectionModel().getSelectedItem());
                associatedPartsTable.refresh();

            }
        }
        else{
            error.setContentText("Nothing Selected");
            error.show();
        }
    }

    /** This method saves the form
     *  Here is where the application saves and validates the form entry.
     *  This method also creates a new ID based on how many items are inside of the products observable list
     * */
    @FXML
    void OnActionSave(ActionEvent event) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("error");

        int counter = 1;
        int id = 1;
        Inventory inv = new Inventory();


        for(Product prod: inv.getAllProducts()){
            counter = prod.getId();
            counter++;
        }
        id = counter;

        try {
            String partName = nametxt.getText().trim();
            int stock = Integer.parseInt(invtxt.getText().trim());
            double price = Double.parseDouble(pricetxt.getText().trim());
            int max = Integer.parseInt(maxtxt.getText().trim());
            int min = Integer.parseInt(mintxt.getText().trim());
            Product newItem = new Product(id,partName,price,stock,min,max);

            if(partName.isEmpty()){
                error.setContentText("empty!");
                error.show();
                return;
            }
            else if(min>max){
                error.setContentText("Min cannot be greater than Max");
                error.show();
                return;
            }
            else if(stock > max || stock < min){
                error.setContentText("stock must be between min and max");
                error.show();
                return;
            }
            else{

                for(Part parts: associatedList){
                    newItem.addAssociatedPart(parts);
                }
                Inventory.addProduct(newItem);


                scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }catch (Exception e){
            error.setContentText("Invalid Entry");
            error.show();
            return;
        }

    }

    /**This methods adds and moves the item into associatedPartsTable*/
    @FXML
    public void OnActionAddItem(ActionEvent actionEvent) {
        Part selectedItem = allPartsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to Add?");
        alert.setTitle("Confirm");

        if(allPartsTable.getSelectionModel().getSelectedItem() != null){
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                associatedList.add(selectedItem);
                associatedPartsTable.setItems(associatedList);

                allPartsList.remove(selectedItem);
                allPartsTable.refresh();
            }
        }
        else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Nothing Selected");
            error.show();
        }

    }

    /** Search Funtionality
     * This method searches the allPartsTable(Top TableView)
     * */
    @FXML
    void OnActionSearch(ActionEvent event) {
        ObservableList<Part> partToSearch = FXCollections.observableArrayList();
        try{
            int idToSearch = Integer.parseInt(searchtxt.getText());
            if(Inventory.lookupPart(idToSearch) == null){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Item does not exist");
                error.showAndWait();
                allPartsTable.setItems(Inventory.getAllParts());
            }else {
                partToSearch.add(Inventory.lookupPart(idToSearch));
                if(partToSearch.size() == 0){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("error");
                    error.setContentText("Item is empty");
                    error.show();
                    allPartsTable.setItems(Inventory.getAllParts());

                }else{
                    allPartsTable.setItems(partToSearch);
                    allPartsTable.refresh();
                }
            }

        }catch (Exception e){
            String nameToSearch = searchtxt.getText();
            partToSearch = Inventory.lookupPart(nameToSearch);

            if(partToSearch.size() == 0){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("error");
                error.setContentText("Item is empty");
                error.show();
                allPartsTable.setItems(Inventory.getAllParts());

            }else{
                allPartsTable.setItems(partToSearch);
                allPartsTable.refresh();
            }
        }
    }

    /** Initializes the allPartsTable and associatedPartsTable columns*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allPartsList.setAll(Inventory.getAllParts());
        allPartsTable.setItems(allPartsList);
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partIventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));



        associatedPartICol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInvLvlcol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPricecol.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}
