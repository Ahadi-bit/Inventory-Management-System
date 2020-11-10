package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


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
        for(Part searchedpart: allParts){
            if(searchedpart.getId() == partid){
                return searchedpart;
            }
        }
        return null;
    }
    public static Product lookupProduct(int productid){
        for(Product searchedProd: allProducts){
            if(searchedProd.getId() == productid){
                return searchedProd;
            }
        }
        return null;
    }
    public static ObservableList<Part> lookupPart(String partName){
        for(Part part:allParts){
            if(part.getName().toLowerCase().equals(partName.toLowerCase())){
                return (ObservableList<Part>) part;
            }
        }
        return null;
    }
    public  static ObservableList<Product> lookupProduct(String productName){
        for(Product product:allProducts){
            if(product.getName().toLowerCase().equals(productName.toLowerCase())){
                return (ObservableList<Product>) product;
            }
        }
        return null;
    }


    public static void updatePart(int index, Part selectedPart){
        for(int i = 0; i<allParts.size(); i++){
            if(allParts.get(i).getId() == selectedPart.getId()){
                allParts.set(i,selectedPart);

            }
        }
    }
    public  static void updateProduct(int index, Product selectedProduct){
        for(int i = 0; i<allProducts.size(); i++){
            if(allProducts.get(i).getId() == selectedProduct.getId()){
                allProducts.set(i,selectedProduct);

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
