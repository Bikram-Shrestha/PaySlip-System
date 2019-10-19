package PaySlipSystem.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaySlipTest {
    PaySlip paySlip;
    int grossIncome,
        taxAmount,
        superAmount,
        netIncome;
    String payPeriod;

    @Mock
    Employee employee = mock(Employee.class);


    @BeforeEach
    void setUp() {
        grossIncome = 3000;
        taxAmount = 200;
        superAmount = 150;
        netIncome = 2800;
        payPeriod = "1 May - 31 May";
        paySlip = new PaySlip(employee,
                grossIncome,taxAmount,netIncome,
                superAmount,payPeriod);
    }

    @AfterEach
    void tearDown() {
        grossIncome = 0;
        taxAmount = 0;
        superAmount = 0;
        netIncome = 0;
        payPeriod = null;
    }

    @Test
    void getEmployee() {
        assertEquals(employee, paySlip.getEmployee());
    }

    @Test
    void getGrossIncome() {
        assertEquals(grossIncome,paySlip.getGrossIncome());
    }

    @Test
    void getIncomeTax() {
        assertEquals(taxAmount,paySlip.getIncomeTax());
    }

    @Test
    void getNetIncome() {
        assertEquals(netIncome,paySlip.getNetIncome());
    }

    @Test
    void getSuperAmount() {
        assertEquals(superAmount,paySlip.getSuperAmount());
    }

    @Test
    void getPayPeriod() {
        assertEquals(payPeriod,paySlip.getPayPeriod());
    }

    @Test
    void toString1() {
        when(employee.getFirstName()).thenReturn("Bikram");
        when(employee.getLastName()).thenReturn("Shrestha");
        assertEquals("Bikram Shrestha,1 May - 31 May,3000,200,2800,150",paySlip.toString());
    }
}