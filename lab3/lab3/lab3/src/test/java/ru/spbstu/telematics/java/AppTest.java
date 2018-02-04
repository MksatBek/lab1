package ru.spbstu.telematics.java;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }


    public void testApp()
    {

        ThreadApp a = new ThreadApp();
        double high = 3.3;
        double low = 3.0;
        double testPi  = a.compute(3, 50000);
        assertTrue("Error, computed pi is too high", high >= testPi);
        assertTrue("Error, computed pi too low",  low  <= testPi);



        testPi  = a.compute(3, 10000);
        assertTrue("Error, computed pi is too high", high >= testPi);
        assertTrue("Error, computed pi too low",  low  <= testPi);



        testPi  = a.compute(8, 1000);
        assertTrue("Error, computed pi is too high", high >= testPi);
        assertTrue("Error, computed pi too low",  low  <= testPi);



    }
}
