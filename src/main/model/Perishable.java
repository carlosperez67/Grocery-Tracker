package model;

import java.util.Date;

// represents grocery items that are perishable ie have an expiry date, like milk, eggs etc
public class Perishable extends GroceryItem {

    private final Date expiryDate;                    // expiry date for grocery item

    // Required:  -price.getAmtCents >0;
    //            - storingMethod is one-of: Freezer, Fridge, Pantry
    //            - expiry Date is in the future
    //                  - Requires: month in [0,11], day in [1,31],
    //                            (also can use CALENDER.MONTH)
    //                  - format of YEAR,MONTH,DATE,. example : 2022,10,31
    // Modifies: this
    // Effects: creates perishable item with given label, a price in cents and given servingSize and expiryDate
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
    // Modifies:
    // Effects: Returns num of days until perishable item is expired
    public int daysUntilExpired(Date todayDate) {
        long time1 = todayDate.getTime();   //gets time in milliseconds
        long time2 = expiryDate.getTime();

        long milliDifference = time2 - time1;  // difference in milliseconds
        // converting from milliseconds -> seconds -> minutes ->hours -> days
        return (int) (milliDifference / (1000 * 60 * 60 * 24));
    }


    // Effects: Returns true if item is expired, else false
    public Boolean isExpired(Date todayDate) {
        return expiryDate.before(todayDate);
    }


}

