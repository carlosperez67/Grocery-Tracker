package model;

import eventlog.Event;
import eventlog.EventLog;
import org.json.JSONObject;

// Represents grocery items that aren't perishable: example, rice, spices, sugar etc...
public class NonPerishable extends GroceryItem {

    // Required:  -PriceInDollars in form (dollars.cents), example: 10.25
    //            -value after "." must be <100
    //            - priceInDollars> 0
    //            - ServingSize >0
    // Modifies: this
    // Effects: creates non-perishable item with given label, a price in cents and given servingSize
    //          and with the storing method of pantry
    public NonPerishable(String label, Money price, int servingSize) {
        super(label,price,servingSize);
        this.storingMethod = StoringMethod.pantry;
        EventLog.getInstance().logEvent(new Event("Created new non-perishable item: " + label + "."));
    }

    // Taken from JsonSerialization Demo
    // Effects: creates JSON object to store with null date
    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("expiryDay", -1);
        json.put("expiryMonth", -1);
        json.put("expiryYear", -1);
        return json;
    }


}
