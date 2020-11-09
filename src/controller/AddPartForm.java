package controller;


import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AddPartForm{

    Stage stage;
    Parent scene;
    Random rand = new Random();

    @FXML private RadioButton inHousebtn;
    @FXML private ToggleGroup partType;
    @FXML private RadioButton outSourcedbtn;
    @FXML private TextField IdAutoGenTXT;
    @FXML private TextField addPartNametxt;
    @FXML private TextField addPartInvtxt;
    @FXML private TextField addPartPricetxt;
    @FXML private TextField addPartTypetxt;
    @FXML private TextField addPartMaxtxt;
    @FXML private TextField addPartMintxt;
    @FXML private Label dynamiclbl;


    /** Changes dynamiclbl to In-House**/
    @FXML
    void OnClickInHouse(MouseEvent event) {
        this.dynamiclbl.setText("Machine ID");
        System.out.println("in house btn");
    }

    /** Changes dynamiclbl to Out-Source**/
    @FXML
    void OnClickOutSource(MouseEvent event) {
        this.dynamiclbl.setText("Company Name");
        System.out.println("Outsourced");
    }





    @FXML
    void OnActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }



    @FXML
    void OnActionSave(ActionEvent event) throws IOException{
        String partName = addPartNametxt.getText();
        int stock = Integer.parseInt(addPartInvtxt.getText());
        double price = Double.parseDouble(addPartPricetxt.getText());
        int max = Integer.parseInt(addPartMaxtxt.getText());
        int min = Integer.parseInt(addPartMintxt.getText());

        Alert error = new Alert(Alert.AlertType.ERROR);

        int counter = 1;
        int id = 1;
        Inventory inv = new Inventory();


        for(Part part: inv.getAllParts()){
            counter = part.getId();
            counter++;
        }
        id = counter;
        /** Exception handling for min and mix  **/
        try{
            if(min<=max){
                /** Exception handling for unselected part type  **/
                try{
                    if(partType.getSelectedToggle().equals(inHousebtn)){
                        /** Exception handling for In-house entry**/
                        try{
                            int machineId = Integer.parseInt(addPartTypetxt.getText());
                            Part newItem = new InHouse(id,partName,price,stock,min,max,machineId);

                            inv.addPart(newItem);

                            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                            scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                        }
                        catch (Exception e){
                            error.show();
                            error.setTitle("Problem!");
                            error.setContentText("Invalid Entry please try again:"+ e.getMessage());
                        }

                    }
                    else if(partType.getSelectedToggle().equals(outSourcedbtn)){
                        /** Exception handling for Out-Source entry**/
                        try{
                            String CompanyName = addPartInvtxt.getText();
                            Part newItem = new Outsourced(id,partName,price,stock,min,max,CompanyName);

                            inv.addPart(newItem);

                            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                            scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                        }
                        catch(Exception e){
                            error.show();
                            error.setTitle("Problem!");
                            error.setContentText("Invalid Entry please try again:"+ e.getMessage());
                        }

                    }

                }
                catch (Exception e){
                    error.show();
                    error.setTitle("Problem!");
                    error.setContentText("Radio button not selected");
                }

            }
            else{
                throw new Exception();
            }
        }
        catch (Exception e){
            error.show();
            error.setTitle("Problem!");
            error.setContentText("your min is greater than your max!");
        }


    }
}
