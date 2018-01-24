package lab2;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

public class Driver {

    public static String zero;

    public void run() {
        System.out.println("Running...");
        zero = String.format("Zero is: %d", getZero());

        new LevelGUI(new Level(), "Killing floor");
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
