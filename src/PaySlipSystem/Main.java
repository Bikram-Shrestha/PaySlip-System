/**
 * Main.java holds main method to launch the application.  It provide method for changing the state
 * according to the user interaction so that related functionality like generating the pay slip for
 * the input file or from the user input can be carried out. All the method has error handling
 * whenever date is read from either the input.csv file or user input.
 */

package PaySlipSystem;

import java.util.Scanner;
import PaySlipSystem.Controller.DataInputOutputController;
import PaySlipSystem.Controller.DateController;
import PaySlipSystem.Controller.InputController;
import PaySlipSystem.Controller.PaySlipController;
import PaySlipSystem.Model.Employee;
import PaySlipSystem.Model.PaySlip;
import jdk.internal.util.xml.impl.Input;


public class Main {

    public  enum State{GENERATE_PAYSLIP_FROM_FILE, GENERATE_PAYSLIP_FROM_INPUT,EXIT,ERROR}
    private static State state;
    private static boolean loopCondition = true;
    private static Scanner userInput;

    public static void main(String[] args) {
        userInput = new Scanner(System.in);
        System.out.println("Welcome to PaySlip System\n");	// Displaying a greeting.
        while (loopCondition) {
            System.out.print("Please select one of the following options:\n" +
                    "1. Generate Payslip from the input.csv data\n" +
                    "2. Generate Payslip from user input\n" +
                    "3. Exit\n" +
                    "Type your selection (eg. 1): ");

            String userSelection = userInput.next();    // Get user input
            changeState(userSelection);                 // Change the state acccording to user input.
            generatePaySlipFromFile(state);             // Generate the payslipreport.csv if state is correct.
            generatePaySlipFromInput(state);            // Generate the username.txt if state is correct.
            exitFromApp(state);                         // Exit from the application if state is correct.
            errorInInput(state);                        // Handle wrong input if state is ERROR.
        }
        userInput.close();                              // Once the application is selected to be close, scanner is closed.
    }

    /**
     * This method take string and convert the string to relevent enum State.
     * @param string
     * @return State
     */
    public static State changeState(String string){
        if(string.equals("1")){
            state = State.GENERATE_PAYSLIP_FROM_FILE;
        }
        else if (string.equals("2")){
            state = State.GENERATE_PAYSLIP_FROM_INPUT;
        }
        else if (string.equals("3")){
            state = State.EXIT;
        }
        else{
            state = State.ERROR;
        }
        return state;

    }

    /**
     * This method will run if the state is correct and will call DataInputOutController which has method
     * to read the content of input.csv and convert into pay slip object and store as payslipreport.csv
     * @param state
     */
    public static void generatePaySlipFromFile(State state){
        DataInputOutputController dataInputOutputController = new DataInputOutputController();
        InputController inputController = new InputController();
        if (state == State.GENERATE_PAYSLIP_FROM_FILE){
            dataInputOutputController.updatePaySlipReportFile(inputController.readInputData());
            System.out.println("\nThe data is already processed, Please check " +
                    "\"payslipreport.csv\" in root folder of the application.\n");
            System.out.println("Press any key to continue");
            userInput.nextLine();
            userInput.nextLine();
        }
        else{}
    }


    /**
     * This method will run if the state is correct. After that it will ask the user for various input as
     * required and check for error before processing it using the dataInputOutputController. After running this
     * method username.txt is created in the root folder of the application.
     * @param state
     */
    public static void generatePaySlipFromInput(State state){
        if (state == State.GENERATE_PAYSLIP_FROM_INPUT){
            DateController dateController = new DateController();
            DataInputOutputController dataInputOutputController = new DataInputOutputController();
            boolean loopConditionCalculate = true;	// control the loop condition.
            while(loopConditionCalculate){
                System.out.print("Please enter the first name of employee: ");
                String firstName = userInput.next();

                System.out.print("Please enter the last name of employee: ");
                String lastName = userInput.next();


                double annualSalary = 0;
                double superRate = 0;
                String duration = "";

                while(true){
                    System.out.print("Please enter the annual salary: ");
                    String annualSalaryStr = userInput.next();

                    try {
                        annualSalary = Double.parseDouble(annualSalaryStr);
                        if (annualSalary < 0){
                            System.out.println("Annual Salary cannot be negative, Please try again.");
                        }
                        else{
                            break;

                        }
                        break;
                    }
                    catch(NumberFormatException ex){	// Catch error while converting String.
                        System.out.println("Wrong format, try again.");
                    }
                }
                while(true){
                    System.out.print("Please enter the super rate: ");
                    String superRateStr = userInput.next();
                    userInput.nextLine();

                    try {
                        superRate = Double.parseDouble(superRateStr);
                        if (superRate > 50 || superRate < 0){
                            System.out.println("Super rate should be between 0 and 50 inclusive, Please try again.");
                        }
                        else{
                            break;

                        }
                    }
                    catch(NumberFormatException ex){	// Catch error while converting String.
                        System.out.println("Wrong format, try again.");
                    }
                }
                while(true){
                    System.out.print("Please enter the duration of Payslip(dd Month - dd Month): ");

                    duration = userInput.nextLine();
                    if (dateController.extractDate(duration)){
                        loopConditionCalculate = false;
                        break;

                    }
                }
                Employee employee = new Employee(firstName,lastName,annualSalary,superRate);
                PaySlipController paySlipController = new PaySlipController();
                PaySlip paySlip = paySlipController.calculatePaySlipValue(employee,duration);
                System.out.println("\n" +paySlip + "\n");
                dataInputOutputController.createIndividualTaxReport(paySlip);
                System.out.println("Please check \"" + firstName+lastName+".txt\"" + " for Payslip.");
                System.out.println("Press any key to continue");
                userInput.nextLine();
            }
        }
    }

    /**
     * If the state is exit, this method will change the loop condition, closing the application in the process.
     * @param state
     */
    public static void exitFromApp(State state){
        if(state == State.EXIT){
            System.out.println("Thank you for using the Service.");	// Display farewell.
            loopCondition = false;
        }

    }

    /**
     * If there is error this method will ask the user to input the valid option till condition is satisfied.
     * @param state
     */
    public static void errorInInput(State state){
        if (state == State.ERROR){
            System.out.println("\nWrong selection, Please select from given option.\n");

        }
    }
}
