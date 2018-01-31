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
        Level level = new Level();
        level.getRooms().add(new Room(1500, 100, Color.ORANGE));
        level.firstLocation(level.getRooms().get(0));

        levelGUI = new LevelGUI(level, "Killing floor");
        levelGUI.getLv().setDoorwaySize(60);
        levelGUI.getLv().setWallWidth(10);
        levelGUI.getLv().addObserver(levelGUI);

        addRooms(levelGUI);
        //levelGUI.getLv().firstLocation(levelGUI.getLv().getRooms().get(0));
        levelGUI.getLv().update();

        System.out.println("Connected rooms");



        zero = String.format("Zero is: %d", getZero());

        System.out.println(zero);

    }

    private void addRooms(LevelGUI levelGUI){

        //levelGUI.getLv().place(new Room(1500, 100, Color.ORANGE), 100, 100);
        levelGUI.getLv().place(levelGUI.getLv().getRooms().get(0), 100, 100);
        levelGUI.getLv().place(new Room(100, 100, Color.ORANGE), levelGUI.getLv().getRooms().get(0), 'e');

        levelGUI.getLv().place(new Room(100, 600, Color.ORANGE), levelGUI.getLv().getRooms().get(1), 's');
        levelGUI.getLv().place(new Room(100, 100, Color.ORANGE), levelGUI.getLv().getRooms().get(2), 's');

        levelGUI.getLv().place(new Room(1400, 100, Color.ORANGE), levelGUI.getLv().getRooms().get(3), 'w');
        levelGUI.getLv().place(new Room(100, 100, Color.ORANGE), levelGUI.getLv().getRooms().get(4), 'w');

        levelGUI.getLv().place(new Room(100, 400, Color.RED), levelGUI.getLv().getRooms().get(5), 'n');
        levelGUI.getLv().place(new Room(100, 100, Color.ORANGE), levelGUI.getLv().getRooms().get(6), 'n');

        levelGUI.getLv().place(new Room(1200, 100, Color.ORANGE), levelGUI.getLv().getRooms().get(7), 'e');
        levelGUI.getLv().place(new Room(100, 100, Color.ORANGE), levelGUI.getLv().getRooms().get(8), 'e');

        levelGUI.getLv().place(new Room(100, 250, Color.ORANGE), levelGUI.getLv().getRooms().get(9), 's');
        levelGUI.getLv().place(new Room(100, 100, Color.ORANGE), levelGUI.getLv().getRooms().get(10), 's');

        levelGUI.getLv().place(new Room(500, 100, Color.ORANGE), levelGUI.getLv().getRooms().get(11), 'w');
        levelGUI.getLv().place(new Room(100, 100, Color.ORANGE), levelGUI.getLv().getRooms().get(12), 'w');

        levelGUI.getLv().place(new Room(300, 150, Color.BLUE), levelGUI.getLv().getRooms().get(13), 'n');

        levelGUI.getLv().place(new Room(70, 120, Color.GREEN), 0, 0);
        levelGUI.getLv().getRooms().get(0).setOneWayPortal(levelGUI.getLv().getRooms().get(15), 'w');
        levelGUI.getLv().getRooms().get(15).setOneWayPortal(levelGUI.getLv().getRooms().get(6), 's');
        levelGUI.getLv().getRooms().get(0).connectSouthTo(levelGUI.getLv().getRooms().get(14));


        /*
        levelGUI.getLv().place(new Room(300, 300, Color.blue), 650, 400);

        levelGUI.getLv().place(new Room(300, 100, Color.yellow), levelGUI.getLv().getRooms().get(0), 'w');
        levelGUI.getLv().place(new Room(300, 100, Color.yellow), levelGUI.getLv().getRooms().get(0), 'e');
        levelGUI.getLv().place(new Room(100, 300, Color.yellow), levelGUI.getLv().getRooms().get(0), 'n');
        levelGUI.getLv().place(new Room(100, 300, Color.yellow), levelGUI.getLv().getRooms().get(0), 's');
        levelGUI.getLv().place(new Room(300, 300, Color.red), levelGUI.getLv().getRooms().get(3), 'w');
        levelGUI.getLv().place(new Room(80, 80, Color.magenta), levelGUI.getLv().getRooms().get(3), 'e');
        levelGUI.getLv().place(new Room(20, 100, Color.orange), levelGUI.getLv().getRooms().get(1), 'w');

        levelGUI.getLv().place(new Room(200, 100, Color.darkGray), levelGUI.getLv().getRooms().get(3), 'n');
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
