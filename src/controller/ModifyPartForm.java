package controller;
/**
 * Modify Part Form Controller
 */

/**
 *
 * @author Jonathan Payarers
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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


public class ModifyPartForm{
    Stage stage;
    Parent scene;


    @FXML private RadioButton modifyPartInhousebtn;
    @FXML private ToggleGroup partType;
    @FXML private RadioButton modifyPartOutSourcedbtn;
    @FXML private TextField partIdxt;
    @FXML private TextField partNametxt;
    @FXML private TextField partInvtxt;
    @FXML private TextField pricetxt;
    @FXML private TextField maxtxt;
    @FXML private TextField dynamictxt;
    @FXML private TextField mintxt;
    @FXML private Text dynamiclbl;

    /** Changes dynamiclbl to In-House.
     *in In house button is clicked change the dyanmiclbl to Machine ID
     *
     * @param event event for when the In-House button is clicked which should perform this method.
     * */
    @FXML private void OnClickInHouse(MouseEvent event) {
        this.dynamiclbl.setText("Machine ID");
        System.out.println("in house btn");
    }

    /** Changes dynamiclbl to Out-Source.
     * when out-sourced button is clicked change the dynamiclbl to company name
     *
     * @param event event for when the Out-Source button is clicked which should perform this method.
     * */
    @FXML private void OnClickOutSource(MouseEvent event) {
        this.dynamiclbl.setText("Company Name");
        System.out.println("Outsourced");
    }

    /** This method grabs the selected item to be modified
     * method that sends the data to the modify part form. here we are filling in the different text fields.
     * also a conditional is performed to check which instanceof part the selected part is and will change the dynamiclbl based on that.
     *
     * @param part selected Part to modify
     *
     * */
    public void sendSelectedItem(Part part){
        partIdxt.setText(String.valueOf(part.getId()));
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

    /** This method cancels this scene and takes you back to the Mainscreen.
     * This send the user back to the mainscreen when an action is peroform on the cancel button.
     *
     * @throws IOException for Scene transition
     * @param event event for when the cancel button is clicked which should perform this method.
     * */
    @FXML private void OnActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This method updates the item for the selected item
     * This method also performs the same validation similar to the OnActionSave for the Addparts form.
     * With the exception of calling the update method instead of the add method
     *
     * @param event event for when the cancel button is clicked which should perform this method.
     * */
    @FXML private void OnActionSave(ActionEvent event) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("error");


        Inventory inv = new Inventory();


        if(partType.getSelectedToggle().equals(modifyPartInhousebtn)){

            try {
                String partName = partNametxt.getText();
                int id = Integer.parseInt(partIdxt.getText().trim());
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
                int id = Integer.parseInt(partIdxt.getText().trim());
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

}
