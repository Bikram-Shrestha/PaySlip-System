package PaySlipSystem.Controller;

import PaySlipSystem.Model.Employee;
import PaySlipSystem.Model.PaySlip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputController {
    private static boolean inputRead = false;

    /**
     * This method read the information present in the input.csv and create a payslip object by using
     * all other classes like TaxRateController, PaySlipController and DateController to process the
     * information into related model class so that it can be converted into payslipreport.csv which is
     * free of information.
     * @return boolean
     */
    public  ArrayList<PaySlip> readInputData(){
        ArrayList<PaySlip> paySlipList = new ArrayList<>();
        boolean success = false;
        while(!inputRead){
            try{
                TaxRateController taxRateController = new TaxRateController();
                DateController dateController = new DateController();
                PaySlipController paySlipController = new PaySlipController();
                File inputFile = new File("input.csv");
                Scanner readInputData = new Scanner (inputFile);
                System.out.println("Input data are as follows:");

                while(readInputData.hasNextLine()){
                    String rawText = readInputData.nextLine();
                    System.out.println(rawText);
                    String[] values = rawText.split(",");
                    try{
                        Employee employee = new Employee(values[0].trim(),
                                values[1].trim(),
                                Double.parseDouble(values[2]),
                                taxRateController.extractDouble(values[3]));
                        boolean successToExtractDate = dateController.extractDate(values[4]);
                        if(!successToExtractDate){
                            System.err.println("Skipping line:" + rawText + " due to formatting issue.");
                        }
                        else{
                            PaySlip paySlip = paySlipController.calculatePaySlipValue(employee,values[4]);
                            paySlipList.add(paySlip);
                        }

                    }

                    catch(Exception e){
                        System.err.println("Skipping line:" + rawText + " due to formatting issue.");

                    }

                }
                readInputData.close();
                inputRead =true;
                success =  true;
            }
            //If file is missing throw this exception.
            catch (FileNotFoundException e){
                System.out.println("\"input.csv\" file not Found, Please make sure it is present in root folder.");
                success = false;
            }
        }
        return paySlipList;
    }
}
