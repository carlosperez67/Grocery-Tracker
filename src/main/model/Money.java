package model;

public class Money {
    private int amtCents;
    private String amtDollars;


    // Required:  amtCents>= 0
    // Modifies: this
    // Effects: creates money object and stores amtCents and amtDollars
    public Money(int amtCents) {
        // ToDo
        //stub
    }

    // Required:  -PriceInDollars in form (dollars.cents), example: 10.25
    //            -value after "." must be <100
    //            - priceInDollars> 0.00
    // Modifies: this
    // Effects: creates money object and stores amtCents and amtDollars
    public Money(String amtDollars) {
        // ToDo
        //stub
    }

    // Requires: amtCents >=0 and amtDollars >= 0.00 and amtDollars is in
    //           correct form of dollars.cents
    public void convertForm() {
        //stub
    }

    // Effects: takes in cents > 0 and adds value to both amtCents and amtDollars
    //          to represent the given value
    public void addAmt(int cents) {
        // stub;
    }

    // Effects: takes in dollars > 0.00 and adds value to both amtCents and amtDollars
    //          to represent the given value
    public void addAmt(String dollars) {
        // stub;
    }

    // getter
    public String getAmtDollars() {
        return amtDollars;
    }

    // getter
    public int getAmtCents() {
        return amtCents;
    }
}
