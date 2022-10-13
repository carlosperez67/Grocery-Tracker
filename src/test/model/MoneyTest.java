package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// tests for Money Class
class MoneyTest {
    private Money testMoney;
    private Money testMoneyA;
    private Money testMoneyB;

    @BeforeEach
    public void setUp() {
        testMoneyA = new Money(0);
        testMoneyB = new Money("0.00");
    }

    @Test
    public void moneyTest() {
        assertEquals(0, testMoneyA.getAmtCents());
        assertEquals("0.00", testMoneyA.getAmtDollars());

        assertEquals(0, testMoneyB.getAmtCents());
        assertEquals("0.00", testMoneyB.getAmtDollars());
    }

    @Test
    public void moneyAddAmtOneCentTest() {
        testMoneyA.addAmt(1);
        assertEquals(1, testMoneyA.getAmtCents());
        assertEquals("0.01", testMoneyA.getAmtDollars());

        testMoneyB.addAmt("0.01");
        assertEquals(1, testMoneyB.getAmtCents());
        assertEquals("0.01", testMoneyB.getAmtDollars());
    }

    @Test
    public void moneyAddAmtOneDollarTest() {
        testMoneyA.addAmt(100);
        assertEquals(100, testMoneyA.getAmtCents());
        assertEquals("1.00", testMoneyA.getAmtDollars());

        testMoneyB.addAmt("1.00");
        assertEquals(100, testMoneyB.getAmtCents());
        assertEquals("1.00", testMoneyB.getAmtDollars());
    }

    @Test
    public void moneyAddAmtDollarAndCentTest() {
        testMoneyA.addAmt(101);
        assertEquals(101, testMoneyA.getAmtCents());
        assertEquals("1.01", testMoneyA.getAmtDollars());

        testMoneyB.addAmt("1.01");
        assertEquals(101, testMoneyB.getAmtCents());
        assertEquals("1.01", testMoneyB.getAmtDollars());
    }

    @Test
    public void moneyAddAmtHighValueTest() {
        testMoneyA.addAmt(1151);
        assertEquals(1151, testMoneyA.getAmtCents());
        assertEquals("11.51", testMoneyA.getAmtDollars());

        testMoneyB.addAmt("11.51");
        assertEquals(1151, testMoneyB.getAmtCents());
        assertEquals("11.51", testMoneyB.getAmtDollars());
    }

    @Test
    public void moneySubtractAmtHighValueTest() {
        testMoneyA.addAmt(1151);
        assertEquals(1151, testMoneyA.getAmtCents());
        assertEquals("11.51", testMoneyA.getAmtDollars());

        testMoneyA.subtractAmt("10.51");
        assertEquals(100, testMoneyA.getAmtCents());
        assertEquals("1.00", testMoneyA.getAmtDollars());

        testMoneyB.addAmt("11.51");
        assertEquals(1151, testMoneyB.getAmtCents());
        assertEquals("11.51", testMoneyB.getAmtDollars());

        testMoneyB.subtractAmt(1051);
        assertEquals(100, testMoneyB.getAmtCents());
        assertEquals("1.00", testMoneyB.getAmtDollars());
    }

    // adding multiple amounts and switching which constructor is used
    @Test
    public void moneyAddAmtMultipleSwitchTest() {
        testMoneyA.addAmt(1050);
        assertEquals(1050, testMoneyA.getAmtCents());
        assertEquals("10.50", testMoneyA.getAmtDollars());

        testMoneyA.addAmt("12.50");
        assertEquals(2300, testMoneyA.getAmtCents());
        assertEquals("23.00", testMoneyA.getAmtDollars());

        testMoneyB.addAmt("10.50");
        assertEquals(1050, testMoneyB.getAmtCents());
        assertEquals("10.50", testMoneyB.getAmtDollars());

        testMoneyB.addAmt(1250);
        assertEquals(2300, testMoneyB.getAmtCents());
        assertEquals("23.00", testMoneyB.getAmtDollars());
    }


}