package PaySlipSystem.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("Bikram","Shrestha",123456,9);
    }

    @AfterEach
    void tearDown() {
        employee = null;
    }

    @Test
    void getFirstName() {
        assertEquals("Bikram", employee.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Shrestha", employee.getLastName());

    }

    @Test
    void getAnnualSalary() {
        assertEquals(123456, employee.getAnnualSalary());

    }

    @Test
    void getSuperRate() {
        assertEquals(9, employee.getSuperRate());

    }

    @Test
    void toString1() {
        assertEquals("Employee{firstName='Bikram', lastName='Shrestha', annualSalary=123456.0, superRate=9.0}", employee.toString());

    }
}