package ru.spbstu.telematics.java;

import java.util.Iterator;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    HashSet<Integer> testHash;
    java.util.HashSet<Integer> javaHash;


    public AppTest(){
        testHash = new HashSet<>();
        javaHash = new java.util.HashSet<Integer>();
        for(int i=0;i<=7;i++){
            testHash.add(i);
            javaHash.add(i);
        }
    }










    @org.junit.Test
    public void test(){
        Iterator<Integer> testiter =  testHash.iterator();
        Iterator<Integer> javaiter =  javaHash.iterator();


        assertEquals("IteratorHasNext Fail!",javaiter.hasNext(),testiter.hasNext());
        assertEquals("IteratorNext Fail!",javaiter.next(),testiter.next());

        //идем к след.элементу
        javaiter.next();
        testiter.next();

        //удаляем
        javaiter.remove();
        testiter.remove();
        assertEquals("IteratorRemove Fail!",javaiter.next(), testiter.next());

        assertEquals("IsEmpty Fail!",javaHash.isEmpty(), testHash.isEmpty());

        assertEquals("Contains Fail!",javaHash.contains(3), testHash.contains(3));







    }
}
