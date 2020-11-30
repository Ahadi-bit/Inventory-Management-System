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



    /**
     * @param newPart adds an part allparts list
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     * @param newProduct adds an product allProducts list
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     * @param selectedPart removes selected Part from the allparts list
     */
    public static boolean deletePart(Part selectedPart){
        return  allParts.remove(selectedPart);
    }

    /**
     * @param selectedProduct removes selected Product from the allproducts list
     */
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    /**
     * @return part based on its part ID
     */
    public static Part lookupPart(int partid){
        for(int i=0; i< allParts.size();i++){
            if(allParts.get(i).getId() == partid){
                return allParts.get(i);
            }
        }
        return null;
    }

    /**
     * @return product based on its product ID
     */
    public static Product lookupProduct(int productid){
        for(int i=0; i< allProducts.size();i++){
            if(allProducts.get(i).getId() == productid){
                return allProducts.get(i);
            }
        }
        return null;
    }

    /**
     * @return part based on partName
     */
    public static ObservableList<Part> lookupPart(String partName){
        partToSearch.clear();
        for(int i=0; i< allParts.size();i++){
            if(allParts.get(i).getName().toLowerCase().equals(partName.toLowerCase())){
                partToSearch.add(allParts.get(i));
            }
        }
        return partToSearch;
    }

    /**
     * @return product based on productName
     */
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

    /**
     * @return all the Parts
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * @return all the Products
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
