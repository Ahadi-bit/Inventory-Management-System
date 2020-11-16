package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
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
    private ToggleGroup partType;

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
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("error");


        Inventory inv = new Inventory();


        if(partType.getSelectedToggle().equals(modifyPartInhousebtn)){

            try {
                String partName = partNametxt.getText();
                int id = Integer.parseInt(PartIdxt.getText().trim());
                int stock = Integer.parseInt(partInvtxt.getText().trim());
                double price = Double.parseDouble(pricetxt.getText().trim());
                int max = Integer.parseInt(maxtxt.getText().trim());
                int min = Integer.parseInt(mintxt.getText().trim());
                int machineId = Integer.parseInt(dynamictxt.getText());
                Part ModifiedItem = new InHouse(id,partName,price,stock,min,max,machineId);

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


                    inv.updatePart(ModifiedItem);
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
        else if(partType.getSelectedToggle().equals(modifyPartOutSourcedbtn)) {
            try {
                String partName = partNametxt.getText();
                int id = Integer.parseInt(PartIdxt.getText().trim());
                int stock = Integer.parseInt(partInvtxt.getText().trim());
                double price = Double.parseDouble(pricetxt.getText().trim());
                int max = Integer.parseInt(maxtxt.getText().trim());
                int min = Integer.parseInt(mintxt.getText().trim());
                String CompanyName = dynamictxt.getText();
                Part newItem = new Outsourced(id,partName,price,stock,min,max,CompanyName);

                if(partName.isEmpty()){
                    error.setContentText("empty part name");
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

                    inv.updatePart(newItem);
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



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
