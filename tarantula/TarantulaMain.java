package TarantulaCalculator;

import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

/**************************************************************************
 * Main for tarantula calculation, takes 2 command line arguments
 * arg[1] - compact-cov-matrix.json file
 * arg[2] - package.name of test program
 * examples of the args are in comments next to the args indexes below 
 **************************************************************************/
 
public class TarantulaMain {
	 
	    public static void main(String[] args) throws ClassNotFoundException {
	    	
	        // create the Matrix Reader to get Test -- Stmt information
	        File f = new File(args[0]); // "/abs/path/to/triangle-mvn-compact-cov-matrix.json"*
		 reader = new covMatrixReader(f);
	        Long testCountLong = reader.getTestCount();
	        String testCountString = testCountLong.toString();
	        int testCountInt = Integer.parseInt(testCountString);
	        
	        // create Runner class to get the failing cases from JUnit tests
	        Class testClass = Class.forName(args[1]); //"TarantulaCalculator.TriangleTest"
	        Runner r = new Runner(testClass);
	        r.determineFailCases();
	        ArrayList<Integer> fails = r.getFailCases();
	     
	        // set coverage matrix -- [test] [stmt]
	        boolean[][] M = reader.getM();
	        TarantulaSuspiciousnessCalculation calc = new TarantulaSuspiciousnessCalculation(M);
	         
	        // set coverable statements -- [stmt]
	        boolean[] C = reader.getC();
	        calc.setC(C);
	         
	        // set failing test cases -- [test]
	        boolean[] F =new boolean[testCountInt];
	        Arrays.fill(F, false);
	        for(Integer i : fails) {
	            F[i - 1] = true;
	        }
	        calc.setF(F);
	         
	        // set live test cases -- [test]
	        boolean[] L = new boolean[testCountInt];
	        Arrays.fill(L, true);
	        calc.setL(L);
	         
	        calc.compute();
	         
	        System.out.println("For program: " + reader.getFullName());
	        displayConf(calc.getConfidence(), reader.getFirstLine());
	        System.out.println("------------------------------------------------------------------");
	        displaySusp(calc.getSuspiciousness(), reader.getFirstLine());
	    }
	    // display the confidence array for the program	     
	    public static void displayConf(double[] a, Long firstLine){
	        System.out.println("Confidence Array");
	        for(int i = 0; i < a.length; i++) {
	            System.out.println("line " + (i+firstLine) + ": " + a[i]);
	        }
	    }
	     
	    // dispaly the susp. array for the program
	    public static void displaySusp(double[] a, Long firstLine){
	        System.out.println("Suspicion Array");
	        for(int i = 0; i < a.length; i++) {
	            System.out.println("line " + (i+firstLine) + ": " + a[i]);
	        }
	    }

}
