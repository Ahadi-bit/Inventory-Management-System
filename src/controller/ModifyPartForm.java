package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartForm implements Initializable {
    Stage stage;
    Parent scene;


    @FXML
    private RadioButton modifyPartInhousebtn;

    @FXML
    private ToggleGroup PartType;

    @FXML
    private RadioButton modifyPartOutSourcedbtn;

    @FXML
    private TextField PartIdxt;

    @FXML
    private TextField partNametxt;

    @FXML
    private TextField partInvtxt;

    @FXML
    private TextField pricetxt;

    @FXML
    private TextField maxtxt;

    @FXML
    private TextField dynamictxt;

    @FXML
    private TextField mintxt;

    @FXML
    private Text dynamiclbl;


    public void sendSelectedItem(Part part){
        PartIdxt.setText(String.valueOf(part.getId()));
        partNametxt.setText(part.getName());
        partInvtxt.setText(String.valueOf(part.getStock()));
        pricetxt.setText(String.valueOf(part.getPrice()));
        maxtxt.setText(String.valueOf(part.getMax()));
        mintxt.setText(String.valueOf(part.getMin()));


        if(part instanceof InHouse){
            modifyPartInhousebtn.setSelected(true);
            this.dynamiclbl.setText("Machine ID");
            dynamictxt.setText(String.valueOf(((InHouse) part).getMachineId()));
        }
        if(part instanceof Outsourced){
            modifyPartOutSourcedbtn.setSelected(true);
            this.dynamiclbl.setText("Company Name");
            dynamictxt.setText(((Outsourced)part).getCompanyName());
        }

    }



    @FXML
    void OnActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionSave(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
