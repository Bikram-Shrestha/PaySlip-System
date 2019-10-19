package PaySlipSystem.Controller;

/**
 * This class is responsible for converting the taxrates.txt into TaxRate object so that the
 * related information can be easily accessed as required.
 */

import PaySlipSystem.Model.TaxRate;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class TaxRateController {

    public  ArrayList<TaxRate> taxRateList = new ArrayList <TaxRate>();
    private  ArrayList <String> rawTextList = new ArrayList<String>();
    private  boolean  taxRateRead = false;

    /**
     * This method read the content of the taxrates.txt and extract information from it
     * in order to create a TaxRate object ArrayList. This method verify the informtion
     * and only extract relevent information.
     * @return
     */
    public  boolean readTaxRate(){
        boolean success = false;
        while(!taxRateRead){
            try{
                File taxRateFile = new File("taxrates.txt");
                Scanner readTaxRate = new Scanner (taxRateFile);
                while(readTaxRate.hasNextLine()){
                    String rawText = readTaxRate.nextLine();
                    rawTextList.add(rawText);
                }
                readTaxRate.close();

                for ( int i = 1; i < rawTextList.size(); i++){
                    String[] rawTextArray = rawTextList.get(i).split("\\s+");

                    //All variable are declared zero so that it can be used for calculation
                    // if no information was found regarding it in taxrates.txt.
                    double lowerLimit = 0;
                    double taxRate = 0;
                    double fixTaxAmount = 0;

                    // Find the lower limit
                    lowerLimit = extractDouble(rawTextArray[0]);

                    // Analysing String Array for pattern taxrate in cent.
                    for (int j = 0; j < rawTextArray.length - 1; j++ ){
                        // Find the tax rate
					/*
					As we know tax rate is followed by for each string.
					*/
                        if (rawTextArray[j].equals("for") && rawTextArray[j+1].equals("each")){
                            taxRate = (extractDouble(rawTextArray[j-1]))/100;
                        }
                        // Find the fix amount
					/*
					Fix tax amount is followed by plus string.
					*/
                        if (rawTextArray[j].equals("plus")){
                            fixTaxAmount = extractDouble(rawTextArray[j-1]);
                        }
                    }
                    //Create a TaxRate object and store into taxRateList.
                    TaxRate taxRateObj = new TaxRate(lowerLimit, fixTaxAmount, taxRate);
                    taxRateList.add(taxRateObj);
                }
                taxRateRead = true;
                success = true;
            }
            //If file is missing throw this exception.
            catch (FileNotFoundException e){
                System.out.println("File not Found, Please edit is present in root folder.");
                success=  false;
            }
        }
        return success;

    }

    // Custom Method to extract numerical value stored in String to Double.
    public  double extractDouble(String string){
        String doubleString = "";
        for (int i = 0; i < string.length(); i++){
            if(Character.isDigit(string.charAt(i)) || string.charAt(i)== '.'){
                doubleString += Character.toString(string.charAt(i));
            }
        }
        try{
            double doubleValue = Double.valueOf(doubleString);
            return doubleValue;
        }
        catch(Exception e){
            return -1;
        }
    }
}
