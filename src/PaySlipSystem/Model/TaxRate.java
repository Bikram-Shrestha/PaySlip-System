package PaySlipSystem.Model;

/**
 * This is a model class that describe information provided in TaxRate. The TaxRate has
 * lowerRange,fixTaxAmount,and taxRate. Constructor is provided
 * to create an TaxRate object. In order to protect the data only getter
 * is provided. There is to string method to check the detail of the taxRate object.
 */


public class TaxRate {
    private double lowerRange,
            fixTaxAmount,
            taxRate;


    public TaxRate(double lowerRange, double fixTaxAmount, double taxRate) {
        this.lowerRange = lowerRange;
        this.fixTaxAmount = fixTaxAmount;
        this.taxRate = taxRate;
    }

    public double getLowerRange() {
        return lowerRange;
    }

    public double getFixTaxAmount() {
        return fixTaxAmount;
    }

    public double getTaxRate() {
        return taxRate;
    }


    @Override
    public String toString() {
        return "TaxRate{" +
                "lowerRange=" + lowerRange +
                ", fixTaxAmount=" + fixTaxAmount +
                ", taxRate=" + taxRate +
                '}';
    }
}
