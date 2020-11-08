package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();



    public static void addPart(Part part){
        allParts.add(part);
    }

    public static void addProduct(Product product){
        allProducts.add(product);
    }

//    public static Part lookupPart(int partid){
//
//    }

//    public static Product lookupProduct(int productid){
//
//    }

//    public static ObservableList<Part> lookupPart(String partName){
//
//    }

//      public  static ObservableList<Product> lookupProduct(String name){
//
//      }
    public static void updatePart(int index, Part selectedPart){

    }

    public  static void updateProduct(int index, Product selectedProduct){

    }

//    public static boolean deletePart(Part selectedPart){
//
//    }

//    public static boolean deleteProduct(Product selectedProduct){
//
//    }

    public ObservableList<Part> getAllParts(){
        return allParts;
    }
    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
