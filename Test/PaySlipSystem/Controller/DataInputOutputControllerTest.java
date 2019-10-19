package PaySlipSystem.Controller;

import PaySlipSystem.Model.Employee;
import PaySlipSystem.Model.PaySlip;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class DataInputOutputControllerTest {
    DataInputOutputController dataInputOutputController;
    String payPeriod;

    @Mock
    PaySlip paySlip = mock(PaySlip.class);
    Employee employee = mock (Employee.class);

    @BeforeEach
    void setUp() {
        payPeriod = "1 March - 31 March";
        dataInputOutputController = new DataInputOutputController();

    }


    @AfterEach
    void tearDown() {
        payPeriod = null;
    }



    @Test
    void updateTaxReportFile() {
    }

    @Test
    void createIndividualTaxReport() {

        when(paySlip.getEmployee()).thenReturn(employee);
        when(employee.getFirstName()).thenReturn("");
        when(employee.getLastName()).thenReturn("");
        when(paySlip.getGrossIncome()).thenReturn(1200);
        when(paySlip.getIncomeTax()).thenReturn(120);
        when(paySlip.getNetIncome()).thenReturn(1080);
        when(paySlip.getSuperAmount()).thenReturn(120);
        when(paySlip.getPayPeriod()).thenReturn(payPeriod);
        dataInputOutputController.createIndividualTaxReport(paySlip);
        verify(paySlip,atLeast(1)).getEmployee();
        verify(paySlip,atLeast(1)).getGrossIncome();
        verify(paySlip,atLeast(1)).getIncomeTax();
        verify(paySlip,atLeast(1)).getNetIncome();
        verify(paySlip,atLeast(1)).getPayPeriod();
        verify(paySlip,atLeast(1)).getSuperAmount();

    }
}

