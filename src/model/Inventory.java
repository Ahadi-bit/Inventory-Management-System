package model;

/**
 * Inventory Model
 */

/**
 *
 * @author Jonathan Payarers
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> partToSearch = FXCollections.observableArrayList();
    private static ObservableList<Product> productToSearch = FXCollections.observableArrayList();




    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    public static boolean deletePart(Part selectedPart){
        for(Part part : Inventory.getAllParts()){
            if(part.getId() == selectedPart.getId()){
                return Inventory.getAllParts().remove(selectedPart);
            }
        }
        return false;
    }
    public static boolean deleteProduct(Product selectedProduct){
        for(Product product : Inventory.getAllProducts()){
            if(product.getId() == selectedProduct.getId()){
                return Inventory.getAllProducts().remove(selectedProduct);
            }
        }
        return false;
    }


    public static Part lookupPart(int partid){
        for(int i=0; i< allParts.size();i++){
            if(allParts.get(i).getId() == partid){
                return allParts.get(i);
            }
        }
        return null;
    }
    public static Product lookupProduct(int productid){
        for(int i=0; i< allProducts.size();i++){
            if(allProducts.get(i).getId() == productid){
                return allProducts.get(i);
            }
        }
        return null;
    }
    public static ObservableList<Part> lookupPart(String partName){
        partToSearch.clear();
        for(int i=0; i< allParts.size();i++){
            if(allParts.get(i).getName().toLowerCase().equals(partName.toLowerCase())){
                partToSearch.add(allParts.get(i));
            }
        }
        return partToSearch;
    }
    public  static ObservableList<Product> lookupProduct(String productName){
        productToSearch.clear();
        for(int i=0; i< allProducts.size();i++){
            if(allProducts.get(i).getName().toLowerCase().equals(productName.toLowerCase())){
                productToSearch.add(allProducts.get(i));
            }
        }
        return productToSearch;
    }


    public static void updatePart(Part selectedPart){
        for(int i = 0; i<allParts.size(); i++){
            if(allParts.get(i).getId() == selectedPart.getId()){
                allParts.set(i,selectedPart);
                break;

            }
        }
    }
    public  static void updateProduct(Product selectedProduct){
        for(int i = 0; i<allProducts.size(); i++){
            if(allProducts.get(i).getId() == selectedProduct.getId()){
                allProducts.set(i,selectedProduct);
                break;
            }
        }

    }


    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
