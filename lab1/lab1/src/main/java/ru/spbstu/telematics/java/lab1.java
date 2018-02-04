package ru.spbstu.telematics.java;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;






class App {


    StringBuffer run(String file1,String file2) throws FileNotFoundException {


        Scanner scan = new Scanner(new File(file1));
        Scanner scan2 = new Scanner(new File(file2));

        //выводим по очереди
        StringBuffer testString = new StringBuffer();
        while(scan.hasNextLine()&&scan2.hasNextLine()){
            String line = scan.nextLine();
            System.out.println(line);
            testString.append(line);
            line = scan2.nextLine();
            testString.append(line);
            System.out.println(line);
        }

        // выводим то, что осталось
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println(line);
            testString.append(line);

        }
        while(scan2.hasNextLine()){
            String line = scan2.nextLine();
            System.out.println(line);
            testString.append(line);
        }
        return testString;

    }
}







 class Main {

    public static void main(String[] args) throws FileNotFoundException {
        if(args.length!=2){
            System.out.println("ERROR: NOT ENOUGH ARGUMENTS!");
        }
        App a = new App();
        a.run(args[0], args[1]);

    }

}
