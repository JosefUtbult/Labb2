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


        if(args.length >= 1 && Objects.equals(args[0], "--rave-mode")){
            Driver.getLevelGUI().setRaveMode(true);
        }

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





}


