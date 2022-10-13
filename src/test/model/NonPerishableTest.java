package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NonPerishableTest {
    private NonPerishable testNP;
    private Money beanPrice;

    @BeforeEach
    public void setUp() {
        beanPrice = new Money(150);
        testNP = new NonPerishable("Test Canned Beans", beanPrice, 1);
    }

    @Test
    public void npTest() {
        assertEquals("Test Canned Beans", testNP.getLabel());
        assertEquals(1, testNP.getServingsLeft());
        assertEquals(StoringMethod.pantry, testNP.getStoringMethod());
        assertEquals(beanPrice, testNP.getPrice());
    }

    @Test
    public void testUseOneServing() {
        assertTrue(testNP.useOneServing());
        assertEquals(0,testNP.getServingsLeft());
    }

    @Test
    public void testUseOneServingMultipleFail() {
        assertTrue(testNP.useOneServing());
        assertEquals(0,testNP.getServingsLeft());

        assertFalse(testNP.useOneServing());
        assertEquals(0,testNP.getServingsLeft());
    }

    @Test
    public void testUseOneServingMultipleSuccess() {
        testNP.setServingsLeft(3);
        assertTrue(testNP.useOneServing());
        assertEquals(2,testNP.getServingsLeft());

        assertTrue(testNP.useOneServing());
        assertEquals(1,testNP.getServingsLeft());

        assertTrue(testNP.useOneServing());
        assertEquals(0,testNP.getServingsLeft());
    }

    @Test
    public void testUseMultipleServingComplex() {
        testNP.setServingsLeft(10);
        assertTrue(testNP.useOneServing());
        assertEquals(9,testNP.getServingsLeft());

        assertTrue(testNP.useMultipleServing(5));
        assertEquals(4,testNP.getServingsLeft());

        assertFalse(testNP.useMultipleServing(5));
        assertEquals(4,testNP.getServingsLeft());

        assertTrue(testNP.useMultipleServing(4));
        assertEquals(0,testNP.getServingsLeft());
    }
}
