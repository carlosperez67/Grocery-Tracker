package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(testBudgetMoney, testBudget.getMonthlyBudget());
        assertEquals(testBudgetMoney, testBudget.getAmtLeft());
        assertEquals(m1, testBudget.getAmtSpent());
    }

    @Test
    public void spendBudgetTest() {
        Money m1 = new Money("50.00");
        Money m2 = new Money("450.00");

        testBudget.spendBudget(m1);
        assertEquals(m2, testBudget.getAmtLeft());
        assertEquals(m1, testBudget.getAmtSpent());
    }

    @Test
    public void spendBudgetTestMultiple() {
        Money m1 = new Money(1);
        Money m2 = new Money("499.99");

        testBudget.spendBudget(m1);
        assertEquals(m2, testBudget.getAmtLeft());
        assertEquals(m1, testBudget.getAmtSpent());

        Money m3 = new Money("300.00");
        Money m4 = new Money("199.99");
        Money m5 = new Money("300.01");
        testBudget.spendBudget(m3);
        assertEquals(m4, testBudget.getAmtLeft());
        assertEquals(m5, testBudget.getAmtSpent());

    }


}
