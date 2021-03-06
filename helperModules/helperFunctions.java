package UoKCovid19TestBookingSystem.helperModules;

import java.util.Scanner;

public class helperFunctions {

    /**
     * Allows user to input a string, saves time while coding by reducing repeated code
     * @param prompt The string prompt that will be printed to tell the user what to input
     * @return the string version of the user's input
     */
    public static String inputSTR(String prompt){

        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.nextLine();
    }

    /**
     * Prints data provided, saves time while coding by reducing repeated code
     * @param data the string, int or double that needs to be printed
     */
    public static void print(String data){System.out.println(data);  }
    public static void print(int data){System.out.println(data);}
    public static void print(double data){System.out.println(data); }

}
