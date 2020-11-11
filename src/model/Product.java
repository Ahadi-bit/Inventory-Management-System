package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    int id;
    String name;
    double price;
    int stock;
    int max;
    int min;

    public Product(int id, String name, double price, int stock, int max, int min) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.max = max;
        this.min = min;
    }

    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        Product.associatedParts = associatedParts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void addAssociatedPart(Part part){ associatedParts.add(part);}

    public Part lookupAssociatedPart(int partassociated){
        for(int i=0; i<associatedParts.size(); i++){
            if(associatedParts.get(i).getId() == partassociated){
                return associatedParts.get(i);
            }
        }
        return null;
    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        for(Part part : Inventory.getAllParts()){
            if(part.getId() == selectedAssociatedPart.getId()){
                return Inventory.getAllParts().remove(selectedAssociatedPart);
            }
        }
        return false;
    }
}
