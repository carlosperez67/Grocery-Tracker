package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// tests for budget class
public class BudgetTest {
    private Budget testBudget;
    private Money testBudgetMoney;

    @BeforeEach
    public void setUp(){
        testBudgetMoney = new Money("500.00");
        testBudget = new Budget(testBudgetMoney);
    }

    @Test
    public void budgetTest() {
        Money m1 = new Money(0);
        testBudget.setBudget(50000);
        assertEquals(testBudgetMoney, testBudget.getMonthlyBudget());
        assertEquals(testBudgetMoney.getAmtCents(), testBudget.getAmtLeft().getAmtCents());
        assertEquals(m1.getAmtCents(), testBudget.getAmtSpent().getAmtCents());
    }

    @Test
    public void spendBudgetTest() {
        Money m1 = new Money("50.00");
        Money m2 = new Money("450.00");

        testBudget.spendBudget(m1);
        assertEquals(m2.getAmtCents(), testBudget.getAmtLeft().getAmtCents());
        assertEquals(m1.getAmtCents(), testBudget.getAmtSpent().getAmtCents());
    }

    @Test
    public void spendBudgetTestMultiple() {
        Money m1 = new Money(1);
        Money m2 = new Money("499.99");

        testBudget.spendBudget(m1);
        assertEquals(m2.getAmtCents(), testBudget.getAmtLeft().getAmtCents());
        assertEquals(m1.getAmtCents(), testBudget.getAmtSpent().getAmtCents());

        Money m3 = new Money("300.00");
        Money m4 = new Money("199.99");
        Money m5 = new Money("300.01");
        testBudget.spendBudget(m3);
        assertEquals(m4.getAmtCents(), testBudget.getAmtLeft().getAmtCents());
        assertEquals(m5.getAmtCents(), testBudget.getAmtSpent().getAmtCents());

    }


}
