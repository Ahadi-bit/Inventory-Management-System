package controller;


import javafx.beans.Observable;
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


public class ModifyProductForm  implements Initializable {
    Stage stage;
    Parent scene;

    private Product selectProduct;
    private Inventory inv;




    @FXML
    private TextField idtxt;
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
    private TableView<Part> allPartsTable;

    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    private TableColumn<Part, Integer> partIdcol;

    @FXML
    private TableColumn<Part, String> partNamecol;

    @FXML
    private TableColumn<Part, Integer> invLvlcol;

    @FXML
    private TableColumn<Part, Double> pricecol;

    @FXML
    private TableColumn<Part,Integer> associatedPartIDcol;

    @FXML
    private TableColumn<Part, String> associatedPartNamecol;

    @FXML
    private TableColumn<Part, Integer> associatedInvLvlcol;

    @FXML
    private TableColumn<Part, Double> AssociatedPriceCol;


    @FXML
    private TextField searchtxt;




    @FXML
    void OnActionAdd(ActionEvent event) {

    }
    private ObservableList<Part> associatedList = FXCollections.observableArrayList();
    private ObservableList<Part>  allPartsList= FXCollections.observableArrayList();
    private ObservableList<Part> searchList = FXCollections.observableArrayList();




    public void sendSelectedItem(Product product){
        idtxt.setText(String.valueOf(product.getId()));
        Nametxt.setText(product.getName());
        Invtxt.setText(String.valueOf(product.getStock()));
        Pricetxt.setText(String.valueOf(product.getPrice()));
        Maxtxt.setText(String.valueOf(product.getMax()));
        mintxt.setText(String.valueOf(product.getMin()));

        associatedList.setAll(product.getAllAssociatedParts());
        associatedPartsTable.setItems(associatedList);

        associatedPartIDcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInvLvlcol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete item?");
        alert.setTitle("Confirm");
        if(associatedPartsTable.getSelectionModel().getSelectedItem() != null){
            Optional<ButtonType> result = alert.showAndWait();
            if(((Optional<?>)result).isPresent() && result.get() == ButtonType.OK){
                associatedList.remove(associatedPartsTable.getSelectionModel().getSelectedItem());
            }
        }
    }

    @FXML
    void OnActionSave(ActionEvent event) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("error");
        try {
            String partName = Nametxt.getText();
            int id = Integer.parseInt(idtxt.getText().trim());
            int stock = Integer.parseInt(Invtxt.getText().trim());
            double price = Double.parseDouble(Pricetxt.getText().trim());
            int max = Integer.parseInt(Maxtxt.getText().trim());
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

                Inventory.updateProduct(newItem);
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allPartsTable.setItems(Inventory.getAllParts());
        partIdcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLvlcol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }


}
