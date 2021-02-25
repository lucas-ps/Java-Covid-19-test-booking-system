package UoKCovid19TestBookingSystem;

import java.util.Scanner;

public class helperFunctions {

    public static double inputDBL(String prompt){

        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.nextDouble();
    }

    public static String inputSTR(String prompt){

        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.nextLine();
    }

    public static int inputINT(String prompt){
        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.nextInt();
    }

    public static boolean inputBOOL(String prompt){
        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.nextBoolean();
    }

    public static void print(String data){System.out.println(data);  }
    public static void print(int data){System.out.println(data);}
    public static void print(double data){System.out.println(data); }

    public static String line(){
        String line = ("----------");
        return line;
    }

}
