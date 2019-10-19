package PaySlipSystem;

import PaySlipSystem.Main.State;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MainTest {
    Main main;


    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @AfterEach
    void tearDown() {
        main = null;
    }

    @Test
    void changeState() {
        String[] error = {"4", "5", "6", "7", "8", "8", " ", "one"};

        assertEquals(State.GENERATE_PAYSLIP_FROM_FILE, main.changeState("1"));
        assertEquals(State.GENERATE_PAYSLIP_FROM_INPUT, main.changeState("2"));
        assertEquals(State.EXIT, main.changeState("3"));
        for (String err : error) {
            assertEquals(State.ERROR, main.changeState(err));
        }
    }
}