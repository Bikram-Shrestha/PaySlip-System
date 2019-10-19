package PaySlipSystem.Controller;

/**
 * DateController check the user pay period and make sure that it is in correct format
 * and is valid date. This class provide various method to validate teh date, convert the date
 * in fraction to ease the calculation of the tax and method to extract information from the
 * user input and data provided to the application.
 */

public class DateController {
    private  int firstDay,
                lastDay,
                monthIndex,
                firstMonthIndex,
                lastMonthIndex;

    private final  String[] MONTH = {"JANUARY","FEBRUARY","MARCH","APRIL",
            "MAY","JUNE","JULY","AUGUST",
            "SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
    private final  int[] DAYS_IN_MONTH = {31,28,31,30,31,30,31,31,30,31,30,31};


    /**
     * This method extract the information from the argument into relevant information.
     * This method check whether the string is valid by splitting the string into various parts
     * and comparing with the information that is valid for irregularities. This method will
     * generate first day, last day, first month and last month form the pay duration.
     * @param duration
     * @return
     */
    public  boolean extractDate(String duration){
        boolean success = false;
        String[] separator = duration.split(" ");
        try{
            String testMonth = separator[1].toUpperCase().trim();
            if(validMonth(testMonth)){
                firstMonthIndex = monthIndex;
                firstDay = Integer.parseInt(separator[0]);
                if(firstDay > DAYS_IN_MONTH[firstMonthIndex]){
                    System.err.println(firstDay + " is not a valid number of days in " +testMonth);
                }
                else{
                    testMonth = separator[4].toUpperCase().trim();
                    if(validMonth(testMonth)){
                        lastMonthIndex = monthIndex;
                        lastDay = Integer.parseInt(separator[3]);
                        if(lastDay > DAYS_IN_MONTH[lastMonthIndex]){
                            System.err.println( lastDay + " is not a valid number of days in " +testMonth);
                        }
                        else{
                            success = true;
                        }
                    }
                    else{}


                }
            }
            else{}

        }
        catch (Exception e){
            System.err.println(e + " The duration is in wrong format.\n" +
                    "Please use \"DAY MONTH - DAY MONTH\" format");
        }
        return success;
    }


    /**
     * THis method check whether the stirng provided is the correct month. If it is
     * corect it will return true else it will return false.
     * @param testMonth
     * @return
     */
    public  boolean validMonth(String testMonth){
        for (int i = 0; i < MONTH.length; i++) {
            if(testMonth.equals(MONTH[i])){
                monthIndex = i;
                return true;
            }
        }
        System.err.println("Not a valid month: " + testMonth);
        return false;
    }

    /**
     * This method take all the information that was extracted successfully by extractDate() method and convert
     * it into fraction so that payslip information can be generated form the monthly information generated.
     * @param duration
     * @return
     */
    public  double totalMonthInFraction(String duration){
        double monthInFraction = 0;
        extractDate(duration);
        if(firstMonthIndex==lastMonthIndex && firstDay < lastDay){

            monthInFraction =  ((lastDay-firstDay)+1.0)/(DAYS_IN_MONTH[firstMonthIndex]);
        }
        else{
            double numberOfDaysInFirstMonth = DAYS_IN_MONTH[firstMonthIndex];
            double numberOfDaysInLastMonth = DAYS_IN_MONTH[lastMonthIndex];
            double firstMonthDuration = ((numberOfDaysInFirstMonth - firstDay)+1)/numberOfDaysInFirstMonth;
            double lastMonthDuration = ((numberOfDaysInLastMonth - lastDay)+1)/numberOfDaysInLastMonth;
            int monthInBetween = (lastMonthIndex-firstMonthIndex)-1;
            if(monthInBetween < 0 ){
                monthInBetween = monthIndex + 12;
            }
            monthInFraction = firstMonthDuration+lastMonthDuration + monthInBetween;
        }
        return monthInFraction;
    }
}
