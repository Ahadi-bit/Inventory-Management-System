package model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Product.java
 */

/**
 *
 * @author Jonathan Payares
 */
public class Product {
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    int id;
    String name;
    double price;
    int stock;
    int max;
    int min;
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.max = max;
        this.min = min;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }


    /**
     * @param part adds an associated part to a product
     */
    public void addAssociatedPart(Part part){ associatedParts.add(part);}

    /**
     * @param selectedAssociatedPart deletes the associates part
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * @return all the associated parts
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

    /**
     * @param associatedParts the associatedPart to set
     */
//    public void setAssociatedParts(ObservableList<Part> associatedParts) {
//        Product.associatedParts = associatedParts;
//    }

}
