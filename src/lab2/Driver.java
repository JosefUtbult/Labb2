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

        /*
        r2.connectNorthTo(r3);
        r3.connectSouthTo(r2);
        */
        levelGUI = new LevelGUI(new Level(), "Killing floor");
        levelGUI.getLv().addObserver(levelGUI);

        addRooms(levelGUI);

        levelGUI.getLv().firstLocation(levelGUI.getLv().getRooms().get(0));

        System.out.println("Connected rooms");



        zero = String.format("Zero is: %d", getZero());

        System.out.println(zero);

    }

    private void addRooms(LevelGUI levelGUI){

        levelGUI.getLv().place(new Room(300, 300, Color.blue), 650, 400);

        levelGUI.getLv().place(new Room(300, 100, Color.yellow), levelGUI.getLv().getRooms().get(0), 'w');
        levelGUI.getLv().place(new Room(300, 100, Color.yellow), levelGUI.getLv().getRooms().get(0), 'e');
        levelGUI.getLv().place(new Room(100, 300, Color.yellow), levelGUI.getLv().getRooms().get(0), 'n');
        levelGUI.getLv().place(new Room(100, 300, Color.yellow), levelGUI.getLv().getRooms().get(0), 's');

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
