package PaySlipSystem.Controller;

import PaySlipSystem.Model.Employee;
import PaySlipSystem.Model.PaySlip;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PaySlipControllerTest {

    PaySlipController paySlipController;
    Employee employee;
    PaySlip paySlip;

    private String payPeriod;


    @BeforeEach
    void setUp() {
        paySlipController = new PaySlipController();

    }

    @AfterEach
    void tearDown() {
        paySlipController = null;
        payPeriod = null;
        employee = null;
    }


    @Mock
    DateController dateController = mock(DateController.class);
    TaxRateController taxRateController = mock(TaxRateController.class);



    @Test
    void calculatePaySlipValue() {
        payPeriod = "1 March - 31 March";
        employee = new Employee("David","Rudd",60050,9);
        paySlip = new PaySlip(employee,5004,922,4082,450,payPeriod);

        assertTrue(paySlip.toString().matches(paySlipController.calculatePaySlipValue(employee,payPeriod).toString()));

    }


    @Test
    void roundDouble() {
        double[] roundToInt = {12.445,13.54,12.5,1.12,1.54,1234.32123,1321.453,978.7,67.566,454.4};
        int[] expectedInt = {12,14,13,1,2,1234,1321,979,68,454};

        for(int i = 0; i < roundToInt.length; i++){
            assertEquals(expectedInt[i],paySlipController.roundDouble(roundToInt[i]));
        }
    }
}