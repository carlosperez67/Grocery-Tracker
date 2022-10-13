package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfGroceriesTest {
    private ListOfGroceries testLOG;
    private Perishable testP;
    private NonPerishable testNP;

    @BeforeEach
    public void setUp() {
        testLOG = new ListOfGroceries();
        Date testDate = new Date(2022, Calendar.OCTOBER, 20);
        Money testMoney = new Money(100);
        testP = new Perishable("Perishable", testMoney, 2,
                StoringMethod.fridge, testDate);
        testNP = new NonPerishable("Non Perishable", testMoney, 2);
    }

    @Test
    public void listOfGroceriesTest() {
        assertEquals(0,testLOG.getSizeLoG());
    }

    @Test
    public void addOneTest() {
        testLOG.addGrocery(testP);
        assertEquals(1,testLOG.getSizeLoG());
        assertTrue(testLOG.getListOfGroceries().contains(testP));
    }

    @Test
    public void addMultipleTest() {
        testLOG.addGrocery(testP);
        assertEquals(1,testLOG.getSizeLoG());
        assertTrue(testLOG.getListOfGroceries().contains(testP));

        testLOG.addGrocery(testNP);
        assertEquals(2,testLOG.getSizeLoG());
        assertTrue(testLOG.getListOfGroceries().contains(testP));
        assertTrue(testLOG.getListOfGroceries().contains(testNP));
    }

    @Test
    public void removeOneTest() {
        testLOG.addGrocery(testP);
        assertEquals(1,testLOG.getSizeLoG());
        assertTrue(testLOG.getListOfGroceries().contains(testP));

        testLOG.removeGrocery("Perishable");
        assertEquals(0,testLOG.getSizeLoG());
        assertFalse(testLOG.getListOfGroceries().contains(testP));
    }

    @Test
    public void removeMultipleTest() {
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
    public void addAndRemoveMultipleTest() {
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

        testLOG.removeGrocery("DNE");
        testLOG.removeGrocery("Non Perishable");
        assertEquals(0,testLOG.getSizeLoG());
        assertFalse(testLOG.getListOfGroceries().contains(testNP));
        assertFalse(testLOG.getListOfGroceries().contains(testP));
    }

    @Test
    public void findInMultipleTest() {
        testLOG.addGrocery(testP);
        testLOG.addGrocery(testNP);
        assertEquals(testNP, testLOG.findGrocery("Non Perishable"));
        // specified that this cannot happen in requires, but it is needed for code coverage
        assertNull(testLOG.findGrocery("DNE"));

    }


}
