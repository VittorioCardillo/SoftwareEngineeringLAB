package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    
    public static void main(String[] args) {


        System.out.println("\n - - - - - - - \n");
        System.out.println(" - Esecuzione ListAdapterTester.java -\n");

        Result result = JUnitCore.runClasses(ListAdapterTester.class);

        System.out.println("\n - - - - - - - \n");
        System.out.println(" - Esecuzione ListAdapterTester.java -\n");

        Result result2 = JUnitCore.runClasses(MapAdapterTester.class);



        System.out.println("\n - - - - - - - \n");

        System.out.println(" - TEST RUNNER RESUME - ");

        System.out.println("\n - - - - - - - \n");



        System.out.println(" - ListAdapterTester.java -\n");
        System.out.println("The total number of tests is: " + result.getRunCount() + ".");
        System.out.println("There are " + result.getFailureCount() + " failed tests.");
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("ListAdapterTester.java is a success: " + result.wasSuccessful());
        System.out.println("The ListAdapterTest.java lasted: " + result.getRunTime() + " milliseconds");
        
        
        
        
        System.out.println("\n - - - - - - - \n");



        System.out.println(" - MapAdapterTester.java -\n");
        System.out.println("The total number of tests is: " + result2.getRunCount() + ".");
        System.out.println("There are " + result2.getFailureCount() + " failed tests.");
        for (Failure failure : result2.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("MapAdapterTester.java is a success: " + result2.wasSuccessful());
        System.out.println("The MapAdapterTest.java lasted: " + result2.getRunTime() + " milliseconds");



        System.out.println("\n - - - - - - - \n");
    }
}
