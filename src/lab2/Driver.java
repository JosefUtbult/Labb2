package lab2;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

import java.awt.*;

public class Driver {

    public static String zero;

    public void run() {
        // System.out.println("This is a print-out from the driver.");

        Room r1 = new Room(0, 0, Color.blue);
        Room r2 = new Room(1, 0, Color.yellow);
        Room r3 = new Room(1, 1, Color.red);

        r1.connectWestTo(r2);
        r2.connectEastTo(r1);

        r2.connectNorthTo(r3);
        r3.connectSouthTo(r2);

        /*
        zero = String.format("Zero is: %d", getZero());

        new LevelGUI(new Level(), "Killing floor");
        System.out.println(zero);
        */
    }

    /**
     * VERY IMPORTANT!!!!!!!!!!!!! DO NOT TOUCH
     *
     * @return method returns 0 if condition is fullfilled.
     */
    private static int getZero() {
        return (0 == 0) ? 0 : 1;
    }

}
