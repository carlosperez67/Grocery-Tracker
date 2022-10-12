package model.ItemTracking;

import model.Money;

import java.util.Date;

public class Perishable extends GroceryItem {

    private final Date expiryDate;                    // expiry date for grocery item

    // Required:  -PriceInDollars in form (dollars.cents), example: 10.25
    //            -value after "." must be <100
    //            - priceInDollars> 0
    //            - ServingSize >0
    //            - storingMethod is one-of: Freezer, Fridge, Pantry
    //            - expiry Date is in the future
    //                  - Requires: month in [1,12], day in [1,31],
    //                  - format of MONTH,DATE,YEAR . example : 10,31,2022
    // Modifies: this
    // Effects: creates perishable item with given label, a price in cents and given servingSize
    public Perishable(String label, Money price, int servingSize, StoringMethod storingMethod, Date expiryDate) {
        super(label, price, servingSize);
        this.storingMethod = storingMethod;
        this.expiryDate = expiryDate;
    }

    // getter
    public Date getExpiryDate() {
        return expiryDate;
    }


    // Requires: Expiry date has not passed
    // Effects: Returns num of days until perishable item is expired
    public int daysUntilExpired() {
        return 0; //stub
    }


    // Effects: Returns true if item is expired, else false
    public Boolean isExpired() {
        return false; //stub
    }


}

