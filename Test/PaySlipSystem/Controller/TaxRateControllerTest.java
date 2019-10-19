package PaySlipSystem.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxRateControllerTest {

    TaxRateController taxRateController;

    @BeforeEach
    void setUp() {
        taxRateController = new TaxRateController();
    }

    @AfterEach
    void tearDown() {
        taxRateController = null;

    }

    @Test
    void readTaxRate() {
    }

    @Test
    void extractDouble() {
        String[] extractString = {"$12cent","13%","for14lj","w15dd","jk16.5jj","lk19-="};
        double[] assertValues = {12,13,14,15,16.5,19};
        for(int i = 0; i < extractString.length;i++){
            assertEquals(assertValues[i], taxRateController.extractDouble(extractString[i]));
        }

    }
}