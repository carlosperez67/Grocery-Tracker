package persistance;

import model.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

// helper method to test classes inside jsontests
public class JsonTest {

    protected void checkGroceryItem(String label, int price, int servingSize, NonPerishable groceryItem) {
        assertEquals(label, groceryItem.getLabel());
        assertEquals(price, groceryItem.getPrice().getAmtCents());
        assertEquals(servingSize, groceryItem.getServingsLeft());
    }

    protected void checkGroceryItem(String label, int price, int servingSize, StoringMethod storingMethod,
                                    Date expiryDate, Perishable groceryItem) {
        assertEquals(label, groceryItem.getLabel());
        assertEquals(price, groceryItem.getPrice().getAmtCents());
        assertEquals(servingSize, groceryItem.getServingsLeft());
        assertEquals(storingMethod, groceryItem.getStoringMethod());
        assertEquals(expiryDate, groceryItem.getExpiryDate());
    }

    protected void checkBudget(int monthlyBudget, int amtSpent, int amtLeft, Budget budget) {
        assertEquals(amtSpent, budget.getAmtSpent().getAmtCents());
        assertEquals(monthlyBudget, budget.getMonthlyBudget().getAmtCents());
        assertEquals(amtLeft, budget.getAmtLeft().getAmtCents());
    }
}
