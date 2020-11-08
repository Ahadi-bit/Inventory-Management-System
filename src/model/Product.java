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

    public static ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public static void setAssociatedParts(ObservableList<Part> associatedParts) {
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

    public static void addAssociatedPart(Part part){ associatedParts.add(part);}

    public static void deleteAssociatedPart(Part part){ associatedParts.remove(part);}

    public static ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }


}
