package model;

public class Budget {
    private Money monthlyBudget;
    private Money amtSpent;
    private Money amtLeft;

    // Requires: monthlyBudget monetary value >0
    // Modifies: this
    // Effects: creates budget with given monthlyBudget and
    //          no money spent
    public Budget(Money monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
        this.amtSpent = new Money(0);
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


    // REQUIRES: Money has monetary value >0. m >= amtLeft
    // Modifies: this
    // Effects: Subtracts the amount of money m from the budget left and
    //        add the amount represented by m to amtSpent
    public void spendBudget(Money m) {
        //stub
    }
}
