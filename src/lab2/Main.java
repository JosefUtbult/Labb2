package lab2;

//import lab2.Driver;

import lab2.level.LevelGUI;


import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.run();

        System.out.println("Hej");

        if(args.length >= 1 && Objects.equals(args[0], "--rave-mode")){
            raveMode();
        }

    }

    private static void raveMode(){

        Date date = Date.from(Instant.now());
        long lastTime = date.getTime();

        while (true){

            date = Date.from(Instant.now());
            if(date.getTime() - lastTime >= 100){
                Driver.getLevelGUI().updateFlashingColor();
                Driver.getLevelGUI().getLv().update();
                lastTime = date.getTime();
            }

        }
    }

    private static boolean userTerminates(){
        Scanner scanner = new Scanner(System.in);

        //If you want that user terminates it with 'c' char
        return scanner.nextLine().equals("c");
    }




}


