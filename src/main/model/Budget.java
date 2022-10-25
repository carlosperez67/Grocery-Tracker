package model;

import org.json.JSONObject;
import persistance.Writable;

// This class represents the monthly budget allowance to be spent on grocery items.
public class Budget implements Writable {
    private Money monthlyBudget;
    private Money amtSpent;
    private Money amtLeft;

    // Requires: monthlyBudget monetary value >0
    // Modifies: this
    // Effects: creates budget with given monthlyBudget and
    //          no money spent
    //          and with all your money in the budget left unspent
    public Budget(Money monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
        this.amtLeft = new Money(monthlyBudget.getAmtCents());
        this.amtSpent = new Money(0);
    }

    //setter
    public void setAmtSpent(int cents) {
        this.amtSpent.setAmt(cents);
    }

    //setter
    public void setAmtLeft(int cents) {
        this.amtLeft.setAmt(cents);
    }

    //setter
    public void setBudget(int cents) {
        this.monthlyBudget.setAmt(cents);
    }


    //getter
    public Money getAmtSpent() {
        return amtSpent;
    }

    //getter
    public Money getAmtLeft() {
        return amtLeft;
    }

    //getter
    public Money getMonthlyBudget() {
        return monthlyBudget;
    }


    // REQUIRES: Money(m) has monetary value >0. m <= amtLeft
    // Modifies: this
    // Effects: Subtracts the amount of money m from the budget left and
    //        add the amount represented by m to amtSpent
    public void spendBudget(Money m) {
        amtLeft.subtractAmt(m.getAmtCents());
        amtSpent.addAmt(m.getAmtCents());
    }

//    @Override
//    public JSONObject toJason() {
//        JSONObject json = new JSONObject();
//        json.put("monthlyBudget", monthlyBudget.getAmtCents());
//        json.put("amtSpent", amtSpent.getAmtCents());
//        json.put("amtLeft", amtLeft.getAmtCents());
//        return json;
//    }

    // Taken from JsonSerialization Demo
    // Effects: Creates JSON object to store
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("monthlyBudget", monthlyBudget.getAmtCents());
        json.put("amtSpent", amtSpent.getAmtCents());
        json.put("amtLeft", amtLeft.getAmtCents());
        return json;
    }
}
