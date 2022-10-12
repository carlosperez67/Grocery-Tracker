package model;

import model.ItemTracking.Perishable;
import model.ItemTracking.StoringMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PerishableTest {
    private Perishable testP;
    private Money testMilkPrice;
    private Date testExpiryDate;
    private Date testToday;

    @BeforeEach
    public void setUp() {
        testMilkPrice = new Money("3.50");
        testExpiryDate = new Date(2022, Calendar.OCTOBER, 20);
        testToday = new Date(2022, Calendar.OCTOBER, 10);
        testP = new Perishable("Test Soy Milk", testMilkPrice,
                12, StoringMethod.Fridge, testExpiryDate);
    }

    @Test
    public void pTest() {
        assertEquals("Test Soy Milk", testP.getLabel());
        assertEquals(testMilkPrice, testP.getPrice());
        assertEquals(12, testP.getServingsLeft());
        assertEquals(StoringMethod.Fridge, testP.getStoringMethod());
        assertEquals(testExpiryDate, testP.getExpiryDate());
    }

    @Test
    public void daysUntilExpiredTest() {
        assertEquals(10, testP.daysUntilExpired());
    }

    @Test
    public void isExpiredTestFalseTest() {
        assertFalse(testP.isExpired());
    }

    @Test
    public void decreaseDaysUntilExpiredTest() {
        testToday = new Date(2022, Calendar.OCTOBER, 12);
        assertEquals(8, testP.daysUntilExpired());
        assertFalse(testP.isExpired());

        testToday = new Date(2022, Calendar.OCTOBER, 19);
        assertEquals(1, testP.daysUntilExpired());
        assertFalse(testP.isExpired());
    }

    @Test
    public void makeExpiredSlowly() {
        testToday = new Date(2022, Calendar.OCTOBER, 12);
        assertEquals(8, testP.daysUntilExpired());
        assertFalse(testP.isExpired());

        testToday = new Date(2022, Calendar.OCTOBER, 19);
        assertEquals(1, testP.daysUntilExpired());
        assertFalse(testP.isExpired());

        testToday = new Date(2022, Calendar.OCTOBER, 20);
        assertEquals(0, testP.daysUntilExpired());
        assertFalse(testP.isExpired());

        testToday = new Date(2022, Calendar.OCTOBER, 21);
        assertTrue(testP.isExpired());

        testToday = new Date(2022, Calendar.OCTOBER, 23);
        assertTrue(testP.isExpired());
    }


}

