package lab2;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

import java.awt.*;

public class Driver {

    private static LevelGUI levelGUI;

    public static String zero;

    public void run() {
        // System.out.println("This is a print-out from the driver.");

        Room r1 = new Room(100, 120, Color.blue);
        Room r2 = new Room(400, 100, Color.yellow);

        //Room r3 = new Room(20, 15, Color.red);

        r1.connectWestTo(r2);
        r2.connectEastTo(r1);

        /*
        r2.connectNorthTo(r3);
        r3.connectSouthTo(r2);
        */
        Level level = new Level();

        System.out.println(level.place(r1, 0, 0) ? "Added room" : "Could not add room " + r1.toString());
        System.out.println(level.place(r2, 200, 130) ? "Added room" : "Could not add room " + r2.toString());

        levelGUI = new LevelGUI(level, "Killing floor");
        /*
        System.out.println(levelGUI.getLv().place(r1, 0, 0) ? "Added room" : "Could not add");
        System.out.println(levelGUI.getLv().place(r2, 200, 130) ? "Added room" : "Could not add");
        */


        /*zero = String.format("Zero is: %d", getZero());

        System.out.println(zero);
        */

    }

    /**
     * VERY IMPORTANT!!!!!!!!!!!!! DO NOT TOUCH
     *
     * @return method returns 0 if condition is fullfilled.
     */
    private static int getZero() {
        return (0 == 0) ? 0 : 1;        //todo: Make similar functions for 1, 2, 4 and 8
    }

    public static LevelGUI getLevelGUI(){
        return levelGUI;
    }

}
