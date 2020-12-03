package controller;
/**
 * Modify Product Form Controller
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


public class ModifyProductForm  implements Initializable {
    Stage stage;
    Parent scene;

    @FXML private TextField idtxt;
    @FXML private TextField nametxt;
    @FXML private TextField invtxt;
    @FXML private TextField pricetxt;
    @FXML private TextField maxtxt;
    @FXML private TextField mintxt;
    @FXML private TableView<Part> allPartsTable;
    @FXML private TableView<Part> associatedPartsTable;
    @FXML private TableColumn<Part, Integer> partIdcol;
    @FXML private TableColumn<Part, String> partNamecol;
    @FXML private TableColumn<Part, Integer> invLvlcol;
    @FXML private TableColumn<Part, Double> pricecol;
    @FXML private TableColumn<Part,Integer> associatedPartIDcol;
    @FXML private TableColumn<Part, String> associatedPartNamecol;
    @FXML private TableColumn<Part, Integer> associatedInvLvlcol;
    @FXML private TableColumn<Part, Double> associatedPriceCol;
    @FXML private TextField searchtxt;


    /** These Observable list are used to temporarily hold items before the item saves */
    private ObservableList<Part> associatedList = FXCollections.observableArrayList();
    private ObservableList<Part>  allPartsList= FXCollections.observableArrayList();
    private Product prod;
    Product modifiedItem = new Product(6,"filler",3.99,5,1,15);


    /**This method passes the selected selected item from the main screen
     * passes the selected item information to there appropriate text field and populates the associated table view. Here we also remove the associated parts from the allparts list.
     * This was my solution to prevent users from adding duplicate.
     *
     * @param product sends selected item data
     * */
    public void sendSelectedItem(Product product){
        prod = product;
        idtxt.setText(String.valueOf(prod.getId()));
        nametxt.setText(prod.getName());
        invtxt.setText(String.valueOf(prod.getStock()));
        pricetxt.setText(String.valueOf(prod.getPrice()));
        maxtxt.setText(String.valueOf(prod.getMax()));
        mintxt.setText(String.valueOf(prod.getMin()));

        associatedList = prod.getAllAssociatedParts();
        allPartsList.setAll(Inventory.getAllParts());
        allPartsList.removeAll(associatedList);
        associatedPartsTable.setItems(prod.getAllAssociatedParts());
    }



    /** Search functionality for Modify Product Form
     * this methods performs a search for the top tableview. similar to the Mainscreen method.
     *
     * @param event event for when the search button is clicked which should perform this method
     * */
    @FXML private void OnActionSearch(ActionEvent event) {
        ObservableList<Part> partToSearch = FXCollections.observableArrayList();
        try{
            int idToSearch = Integer.parseInt(searchtxt.getText());
            if(Inventory.lookupPart(idToSearch) == null){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Item does not exist");
                error.showAndWait();
                allPartsTable.setItems(allPartsList);
            }else {
                partToSearch.add(Inventory.lookupPart(idToSearch));
                if(partToSearch.size() == 0){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("error");
                    error.setContentText("Item is empty");
                    error.show();
                    allPartsTable.setItems(allPartsList);

                }else{
                    allPartsTable.setItems(partToSearch);
                    allPartsTable.refresh();
                }
            }

        }catch (Exception e){
            String nameToSearch = searchtxt.getText();
            partToSearch = Inventory.lookupPart(nameToSearch);

            if(partToSearch.size() == 0 || nameToSearch.isEmpty()){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("error");
                error.setContentText("Item does not exist or Empty");
                error.show();
                allPartsTable.setItems(allPartsList);

            }else{
                allPartsTable.setItems(partToSearch);
                allPartsTable.refresh();
            }
        }
    }

    /** This method adds the selected part from the the allPartsTable to the associatedPartsTable
     *
     * @param event event for when the Add button is clicked which should perform this method
     * */
    @FXML private void OnActionAdd(ActionEvent event) {
        Part selectedItem = allPartsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to Add?");
        alert.setTitle("Confirm");

        if(allPartsTable.getSelectionModel().getSelectedItem() != null){
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                associatedList.add(selectedItem);

                associatedPartsTable.setItems(associatedList);
                allPartsList.remove(selectedItem);
            }
        }
        else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Nothing Selected");
            error.show();
        }

    }

    /** This method cancels the form from save and send the user back to the Main Screen.
     *
     *@param event event for when the cancel button is clicked which should perform this method
     *@throws IOException for transition to new scene
     * */
    @FXML private void OnActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This method removes the Associated part from the bottom table view and removes from the associatedList
     * This function slightly different from the add products controller.
     * this was because of a logical error causing items to still delete even if the user would cancel modification. my solution to this was to create a temporary ObservableList that will hold
     * deleted items until item is saved.
     *
     * @param event event for when the remove associated parts button is clicked it should perform this method.
     * */
    @FXML private void OnActionRemoveAssociated(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete item?");
        alert.setTitle("Confirm");
        Part selectedItem = associatedPartsTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
               associatedList.remove(selectedItem);
               allPartsList.add(selectedItem);

            }
        }
    }

    /** This method adds the populated form the the products list
     * This method updates the item and sends the user back to the mainscreen. this performs a similar validation from what is on addproductform.
     * We also loop through the temporary associatedpartsList to add the items into the ObservableList inside the products model.
     *
     * @param event event for when the save button is clicked it should perform this method.
     * */
    @FXML private void OnActionSave(ActionEvent event) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("error");
        try {
            String partName = nametxt.getText().trim();
            int id = Integer.parseInt(idtxt.getText().trim());
            int stock = Integer.parseInt(invtxt.getText().trim());
            int max = Integer.parseInt(maxtxt.getText().trim());
            double price = Double.parseDouble(pricetxt.getText().trim());
            int min = Integer.parseInt(mintxt.getText().trim());

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
                    modifiedItem.addAssociatedPart(parts);
                }

                modifiedItem.setName(partName);
                modifiedItem.setId(id);
                modifiedItem.setMax(max);
                modifiedItem.setMin(min);
                modifiedItem.setPrice(price);
                modifiedItem.setStock(stock);

                Inventory.updateProduct(modifiedItem);

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

    /**Initializes the allpartstable
     * boths table columns from the allpartsTable and the associatedPartsTable are initialized here.
     * allpartsList is set here and set in the allPartsTable.
     *
     * 
     * @param url url is used for resolving the path for the root object
     * @param resourceBundle rb is used to localize the root object
     * */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

        allPartsTable.setItems(allPartsList);

        partIdcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLvlcol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartIDcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInvLvlcol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }


}
