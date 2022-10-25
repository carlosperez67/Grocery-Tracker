package model;

import org.json.JSONObject;
import persistance.Writable;

import java.util.Optional;

// This class represents the general(abstract) grocery item that can be bought from a grocery store
public abstract class GroceryItem implements Writable {
    protected String label;                 // name of grocery item
    protected Money price;                    // price of grocery item
    protected int servingsLeft;              // represents how many meals this item can make
    protected StoringMethod storingMethod; // represents where the item will be stored


    // Required:  - price.getAmtCents > 0
    //            - servingsLeft >0
    // Modifies: this
    // Effects: creates grocery store item with given label, a price represented by Money and given servingSize
    public GroceryItem(String label, Money price, int servingsLeft) {
        this.label = label;
        this.price = price;
        this.servingsLeft = servingsLeft;
    }

    //getter
    public String getLabel() {
        return label;
    }

    //getter
    public int getServingsLeft() {
        return servingsLeft;
    }

    //getter
    public Money getPrice() {
        return price;
    }

    //getter
    public StoringMethod getStoringMethod() {
        return storingMethod;
    }

    //Requires: servingsLeft >= 0
    // Modifies: this
    // Effects: sets new servingSize to the given one
    public void setServingsLeft(int servingsLeft) {
        this.servingsLeft = servingsLeft;
    }

    // Requires:
    // Modifies: this
    // Effects: if there's at least one serving left, subtract a serving size then return true
    //           -else return false
    public Boolean useOneServing() {
        if (servingsLeft > 0) {
            servingsLeft -= 1;
            return true;
        } else {
            return false;
        }
    }

    // Requires: numUsed > 0
    // Modifies: this
    // Effects: if there's at least numUsed servings left, subtract numUsed from servingSizeLeft then return true
    //           -else return false
    public Boolean useMultipleServing(int numUsed) {
        if (servingsLeft >= numUsed) {
            servingsLeft -= numUsed;
            return true;
        } else {
            return false;
        }
    }

    // Taken from JsonSerialization Demo
    // Effects: creates JSON object to store
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("label", label);
        json.put("servingsLeft", servingsLeft);
        json.put("storingMethod",storingMethod);
        json.put("price", price.getAmtCents());
        return json;
    }

}
