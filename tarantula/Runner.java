package tarantula;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * This class stores all the failed tests from a Test class
 */

public class Runner {
	/**Holds a list of all the tests numbers that fail **/
    private ArrayList<Integer> failCases;
    
    /**Holds the results from the test program**/
    Result result;
     
    public Runner(){}
     
    @SuppressWarnings("rawtypes")
    /**Initialize a Runner object**/
    public Runner(Class testClass){
        failCases = new ArrayList<Integer>();
        result = JUnitCore.runClasses(testClass);
    }
    
    /**Loops through all the failures in result and gets the test header
     * pre: all the test headers must in the format: test# e.g: test1, test 10 
     * post: adds the test header integer segment to the failCases **/
    public void determineFailCases(){
        for (Failure failure : result.getFailures()) {
            String header = failure.getTestHeader();
            String header_segment = header.substring(0, header.indexOf("("));
            String test_case_num = header.substring(4, header_segment.length());
            failCases.add(Integer.parseInt(test_case_num));
        }
    }   
     
    /**Return the list of the fail cases**/
    public ArrayList<Integer> getFailCases(){
        return failCases;
    }
}