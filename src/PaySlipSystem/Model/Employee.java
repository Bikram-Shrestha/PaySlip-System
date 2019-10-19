package PaySlipSystem.Model;

/**
 * This is a model class that describe typical employee. The employee has
 * first name, last name, annual salary and super rate. Constructor is provided
 * to create an employee object. In order to protect the data only getter
 * is provided. There is to string method to check the detail of the employee
 */


public class Employee {
    private String  firstName,
                    lastName;
    private double  annualSalary,
                    superRate;

    public Employee(String firstName, String lastName, double annualSalary, double superRate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualSalary = annualSalary;
        this.superRate = superRate;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public double getAnnualSalary() {
        return annualSalary;
    }


    public double getSuperRate() {
        return superRate;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", annualSalary=" + annualSalary +
                ", superRate=" + superRate +
                '}';
    }
}
