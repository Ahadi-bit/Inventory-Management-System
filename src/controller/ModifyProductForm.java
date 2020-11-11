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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ModifyProductForm  implements Initializable {
    Stage stage;
    Parent scene;
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
    private ObservableList<Part> assocPartList = FXCollections.observableArrayList();


    public void sendSelectedItem(Product product){
        idtxt.setText(String.valueOf(product.getId()));
        Nametxt.setText(product.getName());
        Invtxt.setText(String.valueOf(product.getStock()));
        Pricetxt.setText(String.valueOf(product.getPrice()));
        Maxtxt.setText(String.valueOf(product.getMax()));
        mintxt.setText(String.valueOf(product.getMin()));

        assocPartList.setAll(product.getAllAssociatedParts());
        associatedPartsTable.setItems(assocPartList);

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

    }

    @FXML
    void OnActionSave(ActionEvent event) {

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
