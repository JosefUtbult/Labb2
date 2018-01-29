package lab2;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

import java.awt.*;
import java.time.Instant;
import java.util.Date;

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
        //Level level = new Level();
        /*
        System.out.println(level.place(r1, 400, 400) ? "Added room " + r1.toString() : "Could not add room " + r1.toString());
        System.out.println(level.place(r2, 200, 130) ? "Added room " + r2.toString() : "Could not add room " + r2.toString());
        */
        levelGUI = new LevelGUI(new Level(), "Killing floor");
        levelGUI.getLv().addObserver(levelGUI);

        addRooms(levelGUI);

        levelGUI.getLv().firstLocation(levelGUI.getLv().getRooms().get(0));

        //levelGUI.getLv().getRooms().get(0).connectEastTo(levelGUI.getLv().getRooms().get(1));
        //levelGUI.getLv().getRooms().get(1).connectWestTo(levelGUI.getLv().getRooms().get(0));
        System.out.println("Connected rooms");



        zero = String.format("Zero is: %d", getZero());

        System.out.println(zero);

    }

    private void addRooms(LevelGUI levelGUI){

        int nrOfRooms = 6;

        Color roomColors[] = new Color[]{Color.blue, Color.yellow, Color.green, Color.red, Color.pink, Color.CYAN};

        int dx[] = new int[]{300, 430, 400, 300, 600, 300};
        int dy[] = new int[]{400, 300, 300, 400, 300, 500};

        int posX[] = new int[]{100, 500, 20, 800, 1250, 1300};
        int posY[] = new int[]{10, 200, 700, 600, 700, 100};

        for(int i = 0; i < nrOfRooms; i++ ){
            System.out.println(levelGUI.getLv().place(new Room(dx[i], dy[i], roomColors[i]), posX[i], posY[i]) ? "Added room" : "Could not add");
        }

        levelGUI.getLv().getRooms().get(0).connectEastTo(levelGUI.getLv().getRooms().get(1));
        levelGUI.getLv().getRooms().get(1).connectEastTo(levelGUI.getLv().getRooms().get(5));
        levelGUI.getLv().getRooms().get(5).connectSouthTo(levelGUI.getLv().getRooms().get(4));
        levelGUI.getLv().getRooms().get(1).connectSouthTo(levelGUI.getLv().getRooms().get(3));
        levelGUI.getLv().getRooms().get(3).connectEastTo(levelGUI.getLv().getRooms().get(4));
        levelGUI.getLv().getRooms().get(3).connectWestTo(levelGUI.getLv().getRooms().get(2));

        /*
        Room r1 = new Room(100, 120, Color.blue);
        Room r2 = new Room(430, 150, Color.yellow);
        Room r3 = new Room(50, 120, Color.green);
        Room r4 = new Room(600, 100, Color.red);
        Room r5 = new Room(200, 200, Color.pink);
        Room r6 = new Room(300, 100, Color.CYAN);


        Room r3 = new Room(20, 15, Color.red);

        r1.connectWestTo(r2);


        System.out.println(levelGUI.getLv().place(r2, 200, 130) ? "Added room" : "Could not add");
        System.out.println(levelGUI.getLv().place(r1, 500, 500) ? "Added room" : "Could not add");
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
