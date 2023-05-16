import P1.Client1;
import P2.Client2;
import P3.Server;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Main
{
    public static void main(String args[]){
        //Creating File
        File f1 = null;
        FileOutputStream fw1 = null;
        try {
            f1 = new File("P1/F1");
            if (f1.createNewFile()) {
                System.out.println("F1 created");
            } else {
                if (f1.delete()) {
                    f1 = new File("P1/F1");
                    System.out.println("Existing F1 deleted, new one created");
                } else {
                    System.out.println("Unable to delete existing F1");
                }
            }
        } catch (IOException a) {
            System.out.println("ERROR with F1");
            a.printStackTrace();
        }

        //Generating 300 bytes of randomness for F1
        Random rd = new Random();
        byte[] array = new byte[300];
            rd.nextBytes(array);
        try
        {
            fw1 = new FileOutputStream(f1,true); //Opened in append mode
            fw1.write(array);
        }
        catch (IOException a)
        {
            System.out.println("ERROR with writing F1");
            a.printStackTrace();
        }

        //Creating File F2
        File f2 = null;
        FileOutputStream fw2 = null;
        try {
            f2 = new File("P2/F2");
            if (f2.createNewFile()) {
                System.out.println("F2 created");
            } else {
                if (f2.delete()) {
                    f2 = new File("P2/F2");
                    System.out.println("Existing F2 deleted, new one created");
                } else {
                    System.out.println("Unable to delete existing F2");
                }
            }
        } catch (IOException a)
        {
        System.out.println("ERROR with F2");
        a.printStackTrace();
        }

        //Generating 300 bytes of randomness for F1
        rd = new Random();
        array = new byte[300];
            rd.nextBytes(array);
            try
        {
            fw2 = new FileOutputStream(f2,true); //Opened in append mode
            fw2.write(array);
        }
            catch (IOException a)
        {
            System.out.println("ERROR with writing F2");
            a.printStackTrace();
        }
    }
}