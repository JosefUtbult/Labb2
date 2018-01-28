package lab2;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

import java.awt.*;

/**
 * Class runs a simple game with a top-down 2D-level.
 *
 * @author Håkan Jonsson (Course instructor)
 * @author Josef Utbult
 * @author Oscar Brink
 */
public class Driver {

    private static LevelGUI levelGUI;

    public static String zero;

    /**
     * Method runs the game.
     */
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
        //Level level = new Level();
        /*
        System.out.println(level.place(r1, 400, 400) ? "Added room " + r1.toString() : "Could not add room " + r1.toString());
        System.out.println(level.place(r2, 200, 130) ? "Added room " + r2.toString() : "Could not add room " + r2.toString());
        */
        levelGUI = new LevelGUI(new Level(), "Killing floor");

        System.out.println(levelGUI.getLv().place(r1, 500, 300) ? "Added room" : "Could not add");
        System.out.println(levelGUI.getLv().place(r2, 400, 50) ? "Added room" : "Could not add");



        /*zero = String.format("Zero is: %d", getZero());

        System.out.println(zero);
        */

    }

    /**
     * Conditional getter for 0.
     *
     * @return int method returns 0 if condition is fulfilled.
     */
    private static int getZero() {
        return (0 == 0) ? 0 : 1;        //todo: Make similar functions for 1, 2, 4 and 8
    }

    /**
     * @return LevelGUI Returns the LevelGUI object for the program.
     */
    public static LevelGUI getLevelGUI(){
        return levelGUI;
    }

}
