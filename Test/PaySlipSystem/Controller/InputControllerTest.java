package PaySlipSystem.Controller;

import PaySlipSystem.Model.PaySlip;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InputControllerTest {
    InputController inputController;

    @BeforeEach
    void setUp() {
        inputController = new InputController();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readInputData() {
//        assertEquals(,inputController.readInputData());
        ArrayList<PaySlip> paySlipArrayList = inputController.readInputData();
        assertEquals(3,paySlipArrayList.size());

    }
}