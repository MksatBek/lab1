package ru.spbstu.telematics.java;

import java.io.FileNotFoundException;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{





    @org.junit.Test
    public void test() throws FileNotFoundException{

        App a = new App();
        StringBuffer test = a.run("1.txt","2.txt");
        assertEquals("a1b2c3d4e5", test.toString());
    }
}
