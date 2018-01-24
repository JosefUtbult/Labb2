package lab2.level;

import java.awt.Color;
import java.sql.SQLOutput;


public class Room {

    Room north, south, east, west;

    public Room(int dx, int dy, Color color) {
        System.out.println("x:" + dx + " y:" + dy + " " + color);
        north = null;
        south = null;
        east = null;
        west = null;
    }

    public void connectNorthTo(Room r) {
        north = r;
    }
    public void connectEastTo(Room r) {
        east = r;
    }
    public void connectSouthTo(Room r) {
        south = r;
    }
    public void connectWestTo(Room r) {
        west = r;
    }
}
