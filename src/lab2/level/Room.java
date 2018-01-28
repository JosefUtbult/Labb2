package lab2.level;

import java.awt.*;
import java.sql.SQLOutput;

/**
 * Class describes a 2D-room for use in a top-down game level.
 *
 * @author Håkan Jonsson (Course instructor)
 * @author Josef Utbult
 * @author Oscar Brink
 */
public class Room {

    private Color color;
    private Room north, south, east, west;

    private int posX, posY, width, height;

    /**
     * Constructor.
     *
     * @param dx int Width of the Room object.
     * @param dy int Height of the Room object.
     * @param color Color Color of the Room object.
     */
    public Room(int dx, int dy, Color color) {
        System.out.println(toString());

        north = null;
        south = null;
        east = null;
        west = null;

        this.width = dx;
        this.height = dy;
        this.posX = -1;
        this.posY = -1;
        this.color = color;
    }

    /**
     * Checks that this Room doesn't intersect with another.
     *
     * @param other Room other room to check with.
     * @return boolean Returns true if the rooms intersect, false otherwise.
     */
    public boolean intersectsWith(Room other) {
        int thisLeftX = this.getPosX();
        int thisLeftY = this.getPosY();
        int thisRightX = this.getPosX() + this.getWidth();
        int thisRightY = this.getPosY() + this.getHeight();

        int otherLeftX = other.getPosX();
        int otherLeftY = other.getPosY();
        int otherRightX = other.getPosX() + other.getWidth();
        int otherRightY = other.getPosY() + other.getHeight();

        if ((otherRightX < thisLeftX            // other is to the left of this.
                || thisRightX < otherLeftX)     // this is to the left of other.
                || (otherRightY < thisLeftY     // other is above this.
                || thisRightY < otherLeftY)) {  // this is above other.
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param r Room Room to set north of this.
     */
    public void connectNorthTo(Room r) {
        north = r;
    }
    /**
     * @param r Room Room to set east of this.
     */
    public void connectEastTo(Room r) {
        east = r;
    }
    /**
     * @param r Room Room to set south of this.
     */
    public void connectSouthTo(Room r) {
        south = r;
    }
    /**
     * @param r Room Room to set west of this.
     */
    public void connectWestTo(Room r) {
        west = r;
    }

    /**
     * @return Room Gets Room north of this.
     */
    public Room getNorth() {
        return north;
    }
    /**
     * @return Room Gets Room east of this.
     */
    public Room getEast() {
        return east;
    }
    /**
     * @return Room Gets Room south of this.
     */
    public Room getSouth() {
        return south;
    }
    /**
     * @return Room Gets Room west of this.
     */
    public Room getWest() {
        return west;
    }


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Sets the position of this Room.
     *
     * @param posX int x-position.
     * @param posY int y-position.
     */
    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColorObject() {
        return this.color;
    }

    public String toString() {
        return "x:" + width + " y:" + height + " " + color;
    }


}
