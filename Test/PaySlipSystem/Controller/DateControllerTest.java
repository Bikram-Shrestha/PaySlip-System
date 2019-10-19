package PaySlipSystem.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateControllerTest {
    DateController dateController;

    @BeforeEach
    void setUp() {
        dateController = new DateController();
    }

    @AfterEach
    void tearDown() {
        dateController = null;
    }


    @Test
    void extractDate() {
        String[] validDateRange = {"1 January - 29 January", "1 February - 28 February",
                              "1 March - 12 March", "12 February - 10 January"};
        for (String dateRange :validDateRange){
            assertEquals(true,dateController.extractDate(dateRange));
        }
        for (String dateRange: validDateRange){
            assertFalse(dateController.extractDate(dateRange+"i"));
        }
    }

    @Test
    void validMonth(){
        String[] validMonth = {"JANUARY","FEBRUARY","MARCH","APRIL",
                "MAY","JUNE","JULY","AUGUST",
                "SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
        for (String month : validMonth){
            assertTrue(dateController.validMonth(month));
        }
        for (String month: validMonth){
            String inValidMonth = month+"LY";
            assertFalse(dateController.validMonth(inValidMonth));
        }
    }

    @Test
    void totalMonthInFraction() {
        String[] validDateRange = {"1 January - 29 January", "1 February - 28 February",
                "1 March - 12 March", "12 February - 10 January"};
        double[] timeInFraction = {0.9354838709677419,1.0,0.3870967741935484,13.316820276497696};
        for (int i = 0; i < validDateRange.length; i++){
            assertEquals(timeInFraction[i],dateController.totalMonthInFraction(validDateRange[i]));
        }

    }
}