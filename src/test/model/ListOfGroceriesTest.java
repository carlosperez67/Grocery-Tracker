package model;

import model.ItemTracking.NonPerishable;
import model.ItemTracking.Perishable;
import model.ItemTracking.StoringMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfGroceriesTest {
    private ListOfGroceries testLOG;
    private Perishable testP;
    private NonPerishable testNP;
    private Money testMoney;
    private Date testDate;

    @BeforeEach
    public void setUp() {
        testLOG = new ListOfGroceries();
        testDate = new Date(2022, Calendar.OCTOBER, 20);
        testMoney = new Money(100);
        testP = new Perishable("Perishable", testMoney, 2,
                StoringMethod.Fridge, testDate);
        testNP = new NonPerishable("Non Perishable", testMoney, 2);
    }

    @Test
    public void ListOfGroceriesTest() {
        assertEquals(0,testLOG.getSizeLoG());
    }

    @Test
    public void addOne() {
        testLOG.addGrocery(testP);
        assertEquals(1,testLOG.getSizeLoG());
        assertTrue(testLOG.getListOfGroceries().contains(testP));
    }

    @Test
    public void addMultiple() {
        testLOG.addGrocery(testP);
        assertEquals(1,testLOG.getSizeLoG());
        assertTrue(testLOG.getListOfGroceries().contains(testP));

        testLOG.addGrocery(testNP);
        assertEquals(2,testLOG.getSizeLoG());
        assertTrue(testLOG.getListOfGroceries().contains(testP));
        assertTrue(testLOG.getListOfGroceries().contains(testNP));
    }

    @Test
    public void removeOne() {
        testLOG.addGrocery(testP);
        assertEquals(1,testLOG.getSizeLoG());
        assertTrue(testLOG.getListOfGroceries().contains(testP));

        testLOG.removeGrocery("Perishable");
        assertEquals(0,testLOG.getSizeLoG());
        assertFalse(testLOG.getListOfGroceries().contains(testP));
    }

    @Test
    public void removeMultiple() {
        testLOG.addGrocery(testP);
        testLOG.addGrocery(testNP);
        assertEquals(2,testLOG.getSizeLoG());
        assertTrue(testLOG.getListOfGroceries().contains(testP));
        assertTrue(testLOG.getListOfGroceries().contains(testNP));

        testLOG.removeGrocery("Non Perishable");
        assertEquals(1,testLOG.getSizeLoG());
        assertFalse(testLOG.getListOfGroceries().contains(testNP));
        assertTrue(testLOG.getListOfGroceries().contains(testP));

        testLOG.removeGrocery("Perishable");
        assertEquals(0,testLOG.getSizeLoG());
        assertFalse(testLOG.getListOfGroceries().contains(testNP));
        assertFalse(testLOG.getListOfGroceries().contains(testP));
    }

    @Test
    public void addAndRemoveMultiple() {
        testLOG.addGrocery(testP);
        testLOG.addGrocery(testNP);
        assertEquals(2,testLOG.getSizeLoG());
        assertTrue(testLOG.getListOfGroceries().contains(testP));
        assertTrue(testLOG.getListOfGroceries().contains(testNP));

        testLOG.removeGrocery("Non Perishable");
        assertEquals(1,testLOG.getSizeLoG());
        assertFalse(testLOG.getListOfGroceries().contains(testNP));
        assertTrue(testLOG.getListOfGroceries().contains(testP));

        testLOG.addGrocery(testNP);
        assertEquals(2,testLOG.getSizeLoG());
        assertTrue(testLOG.getListOfGroceries().contains(testP));
        assertTrue(testLOG.getListOfGroceries().contains(testNP));

        testLOG.removeGrocery("Perishable");
        assertEquals(1,testLOG.getSizeLoG());
        assertFalse(testLOG.getListOfGroceries().contains(testP));
        assertTrue(testLOG.getListOfGroceries().contains(testNP));

        testLOG.removeGrocery("Non Perishable");
        assertEquals(0,testLOG.getSizeLoG());
        assertFalse(testLOG.getListOfGroceries().contains(testNP));
        assertFalse(testLOG.getListOfGroceries().contains(testP));
    }


}
