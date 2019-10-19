package PaySlipSystem.Model;

/**
 * This is a model class that describe typical PaySlip. The Payslip has employee detail,
 * gross income, income tax, net income, super amount and pay period. Constructor is provided
 * to create PaySlip object. In order to protect the data only getter
 * is provided. There is to string method to check the detail of the information stored in PaySlip.
 */

public class PaySlip {
    private Employee employee;
    private int grossIncome,
                incomeTax,
                netIncome,
                superAmount;
    private String payPeriod;

    public PaySlip(Employee employee, int grossIncome, int incomeTax, int netIncome, int superAmount, String payPeriod) {
        this.employee = employee;
        this.grossIncome = grossIncome;
        this.incomeTax = incomeTax;
        this.netIncome = netIncome;
        this.superAmount = superAmount;
        this.payPeriod = payPeriod;
    }

    public Employee getEmployee() {
        return employee;
    }



    public int getGrossIncome() {
        return grossIncome;
    }



    public int getIncomeTax() {
        return incomeTax;
    }



    public int getNetIncome() {
        return netIncome;
    }



    public int getSuperAmount() {
        return superAmount;
    }



    public String getPayPeriod() {
        return payPeriod;
    }



    @Override
    public String toString() {
        return  employee.getFirstName() + " " +
                employee.getLastName() + "," +
                payPeriod + "," +
                grossIncome + "," +
                incomeTax + "," +
                netIncome +"," +
                superAmount;
    }
}
