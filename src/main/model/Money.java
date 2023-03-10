package model;

import java.util.Arrays;

// Class that represents a monetary value in two forms. One in cents to do easy integer addition
//   and another form in String that is easy to read
public class Money {
    private int amtCents;   // int represnting amount of cents
    private String amtDollars;  // in format Dollars.xx where xx is cents


    // Required:  amtCents>= 0
    // Modifies: this
    // Effects: creates money object and stores amtCents and amtDollars
    //          amtCents and amtDollars always represent the same monetary value
    public Money(int amtCents) {
        this.amtCents = amtCents;
        this.amtDollars = convertForm(amtCents);
    }

    // Required:  -PriceInDollars in form (dollars.cents), example: 10.25
    //            -value after "." must be <100
    //            - priceInDollars> 0.00
    // Modifies: this
    // Effects: creates money object and stores amtCents and amtDollars
    //          amtCents and amtDollars always represent the same monetary value
    public Money(String amtDollars) {
        this.amtCents = convertForm(amtDollars);
        this.amtDollars = amtDollars;
    }

    // Requires: amtCents >=0 and amtDollars >= 0.00 and amtDollars is in
    //           correct form of dollars.cents
    // Modifies: None
    // Effects: Converts from cent integers to string format of Dollar(s).cent(s).
    //          - example 100 cents -> 1.00
    //           returns string form of money
    private String convertForm(int cents) {
        int dollars = 0;
        while (cents >= 100) {
            cents -= 100;
            dollars += 1;
        }
        if (cents < 10) {
            return String.valueOf(dollars) + ".0" + String.valueOf(cents);
        } else {
            return String.valueOf(dollars) + "." + String.valueOf(cents);
        }
    }

    // Requires: amtCents >=0 and amtDollars >= 0.00 and amtDollars is in
    //           correct form of dollars.cents
    // Modifies: None
    // Effects: Converts from string format of Dollar(s).cent(s). to cents
    //           example: 1.00 -> 100 cents
    //           returns money represented as int in cents
    private int convertForm(String value) {
        String[] splitValue = value.split("\\.");
        int dollars = Integer.parseInt(splitValue[0]);
        int cents = Integer.parseInt(splitValue[1]);
        return (dollars * 100 + cents);
    }

    // Requires: cents>0
    // Modifies: this
    // Effects: takes in cents and adds value to both amtCents and sets amtDollars
    //          to represent the given value
    public void addAmt(int cents) {
        amtCents += cents;
        amtDollars = convertForm(amtCents);
    }

    // Requires: dollars monetary value > 0.00
    // Modifies: this
    // Effects: takes in string representing monetary value and adds value to amtCents and sets amtDollars
    //          to represent the given value
    public void addAmt(String dollars) {
        amtCents += convertForm(dollars);
        amtDollars = convertForm(amtCents);
    }

    // Requires: cents>0 AND amtCents > cents
    // Modifies: this
    // Effects: takes in cents and subtracts value to both amtCents and amtDollars
    //          to represent the given value
    public void subtractAmt(int cents) {
        amtCents -= cents;
        amtDollars = convertForm(amtCents);
    }

    // Requires: dollars monetary value > 0.00 AND amtDollars(monetary value) > dollars(monetary value)
    // Modifies: this
    // Effects: takes in string representing monetary value and subtracts value to both amtCents and amtDollars
    //          to represent the given value
    public void subtractAmt(String dollars) {
        amtCents -= convertForm(dollars);
        amtDollars = convertForm(amtCents);
    }

    // getter
    public String getAmtDollars() {
        return amtDollars;
    }

    // getter
    public int getAmtCents() {
        return amtCents;
    }

    // setter
    public void setAmt(int amtCents) {
        this.amtCents = amtCents;
        this.amtDollars = convertForm(amtCents);
    }
}
