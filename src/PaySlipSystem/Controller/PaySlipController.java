package PaySlipSystem.Controller;

/**
 * This class is responsible in converting all the information provided by the user using various controller and
 * model class into payslip information. This class has method to calculate the monthly income tax from the annual
 * income tax so that the information generated can be easily converted to the desired pay period.
 */

import PaySlipSystem.Model.Employee;
import PaySlipSystem.Model.PaySlip;

public class PaySlipController {
    private Employee employee;
    private DateController dateController = new DateController();
    private TaxRateController taxRateController = new TaxRateController();
    private int monthlyTaxAmount,
                monthlyGrossIncome,
                monthlySuper,
                monthlyNetIncome;
    private PaySlip paySlip;

    /**
     * This method calculateMonthlyIncomeAndTax() extract information stored in taxrates.txt by using the
     * TaxController. After extracting all the relevant information related to the employee and calcualate
     * monthly income tax information of each field so that it can be used by calculatePaySlipValue()
     */
    public void calculateMonthlyIncomeAndTax(){
        taxRateController.readTaxRate();
        this.monthlyGrossIncome = roundDouble(employee.getAnnualSalary()/12);

        for( int i = (taxRateController.taxRateList.size()-1); i > 0; i--){
            double lowerRange = taxRateController.taxRateList.get(i).getLowerRange();
            double fixAmount = taxRateController.taxRateList.get(i).getFixTaxAmount();
            double taxRate = taxRateController.taxRateList.get(i).getTaxRate();
            if (employee.getAnnualSalary() >= lowerRange) {
                double yearlyTaxAmount =(fixAmount + ((employee.getAnnualSalary() - (lowerRange - 1)) * taxRate));
                this.monthlyTaxAmount = roundDouble(
                        (fixAmount + (employee.getAnnualSalary() - (lowerRange - 1)) * taxRate)/12);
                break;
            }
        }
        this.monthlyNetIncome = monthlyGrossIncome-monthlyTaxAmount;
        this.monthlySuper = roundDouble((employee.getSuperRate()/100)*monthlyGrossIncome);
    }

    /**
     * This method use the value generated by the calculateMonthlyIncomeAndTax() to calculate all the information
     * required to calculate payslip for the given duration. After creating an object PaySlip it is returned, when
     * this method is called by provided required information in the parameter.
     * @param employee
     * @param payPeriod
     * @return
     */
    public PaySlip calculatePaySlipValue(Employee employee, String payPeriod){
        this.employee = employee;
        if (!dateController.extractDate(payPeriod)){
            System.err.println("Error while converting duration, Pay period is in wrong format.");
        }
        else{
            double timeInFraction = dateController.totalMonthInFraction(payPeriod);
            calculateMonthlyIncomeAndTax();
            int grossIncome = roundDouble(monthlyGrossIncome*timeInFraction);
            int incomeTax = roundDouble(monthlyTaxAmount*timeInFraction);
            int netIncome = roundDouble(monthlyNetIncome*timeInFraction);
            int superAmount= roundDouble(monthlySuper*timeInFraction);
            paySlip = new PaySlip(employee,grossIncome,incomeTax,netIncome,superAmount,payPeriod);
        }

    return paySlip;
    }

    /**
     * This method simply round up the double if  >= 50 cents else round down the value to int.
     * @param value
     * @return
     */
    public int roundDouble(double value){
        return (int)Math.round(value);
    }



}
