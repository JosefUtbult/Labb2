package lab2;

//import lab2.Driver;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Driver().run();
        System.out.println("Hej");

        Scanner s = new Scanner(System.in);
        if (s.next() != "") {

        }

        return;
    }

    private static boolean userTerminates(){
        Scanner scanner = new Scanner(System.in);

        //If you want that user terminates it with 'c' char
        return scanner.nextLine().equals("c");
    }


}


