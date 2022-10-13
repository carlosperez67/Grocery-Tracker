package model;

// This class represents the monthly budget allowance to be spent on grocery items.
public class Budget {
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
}
