package controller;
/**
 * Add Part Form Controller
 */

/**
 *
 * @author Jonathan Payarers
 */

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import java.io.IOException;


public class AddPartForm{

    Stage stage;
    Parent scene;

    @FXML private RadioButton inHousebtn;
    @FXML private ToggleGroup partType;
    @FXML private RadioButton outSourcedbtn;
    @FXML private TextField addPartNametxt;
    @FXML private TextField addPartInvtxt;
    @FXML private TextField addPartPricetxt;
    @FXML private TextField addPartTypetxt;
    @FXML private TextField addPartMaxtxt;
    @FXML private TextField addPartMintxt;
    @FXML private Label dynamiclbl;


    /** Changes dynamiclbl to In-House.*/
    @FXML private void OnClickInHouse(MouseEvent event) {
        this.dynamiclbl.setText("Machine ID");
        System.out.println("in house btn");
    }

    /** Changes dynamiclbl to Out-Source. */
    @FXML private void OnClickOutSource(MouseEvent event) {
        this.dynamiclbl.setText("Company Name");
        System.out.println("Outsourced");
    }

    /** This saves the item.
     This method adds the items to the parts list which is then displayed on the partsTableView.
     Here you will also find the validation for the items. I was also getting a runtime error which was caused by invalid datatypes
     trying to be passed so I added a try catch to validate those errors.
     * */
    @FXML private void OnActionSave(ActionEvent event){

        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("error");

        int counter = 1;
        int id = 1;
        Inventory inv = new Inventory();


        for(Part part: inv.getAllParts()){
            counter = part.getId();
            counter++;
        }
        id = counter;

        if(partType.getSelectedToggle().equals(inHousebtn)){

            try {
                String partName = addPartNametxt.getText().trim();
                int stock = Integer.parseInt(addPartInvtxt.getText().trim());
                double price = Double.parseDouble(addPartPricetxt.getText().trim());
                int max = Integer.parseInt(addPartMaxtxt.getText().trim());
                int min = Integer.parseInt(addPartMintxt.getText().trim());
                int machineId = Integer.parseInt(addPartTypetxt.getText());
                Part newItem = new InHouse(id,partName,price,stock,min,max,machineId);

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

                    inv.addPart(newItem);
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
        else if(partType.getSelectedToggle().equals(outSourcedbtn)) {
            try {
                String partName = addPartNametxt.getText().trim();
                int stock = Integer.parseInt(addPartInvtxt.getText().trim());
                double price = Double.parseDouble(addPartPricetxt.getText().trim());
                int max = Integer.parseInt(addPartMaxtxt.getText().trim());
                int min = Integer.parseInt(addPartMintxt.getText().trim());
                String CompanyName = addPartTypetxt.getText();
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

                    inv.addPart(newItem);
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

    /** This method cancels this scene and takes you back to the Mainscreen. */
    @FXML private void OnActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


}

