package PaySlipSystem.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxRateTest {
    TaxRate taxRate;

    @BeforeEach
    void setUp() {
        taxRate = new TaxRate(0,35000,15);
    }

    @AfterEach
    void tearDown() {
        taxRate = null;
    }

    @Test
    void getLowerRange() {
        assertEquals(0,taxRate.getLowerRange());
        assertFalse(taxRate.getLowerRange()!= 0);
    }

    @Test
    void getFixTaxAmount() {
        assertEquals(35000,taxRate.getFixTaxAmount());
    }

    @Test
    void getTaxRate() {
        assertEquals(15, taxRate.getTaxRate());
    }

    @Test
    void toString1() {
        assertEquals("TaxRate{" +
                "lowerRange=0.0, fixTaxAmount=35000.0, taxRate=15.0}",taxRate.toString());
    }
}