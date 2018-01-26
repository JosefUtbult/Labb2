package lab2.level;

import java.awt.*;
import java.sql.SQLOutput;


public class Room {

    private Color color;
    private Room north, south, east, west;
    private Rectangle rectangle;

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

        rectangle = new Rectangle();
        rectangle.setSize(dx, dy);

        this.width = dx;
        this.height = dy;
        this.posX = -1;
        this.posY = -1;
        this.color = new Color(color.getRGB());

    }

    /**
     * Method connects this Room to another Room.
     *
     * @param r Room Other Room for this Room to be connected to.
     */
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

    /**
     * Getters for the Room objects adjacent to the current Room object.
     *
     * @return Room Returns an adjacent Room object
     */
    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

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
        rectangle.setLocation(posX, posY);
        /*
        this.posX = posX;
        this.posY = posY;
        */
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

    public Rectangle getRectangleObject() {
        return rectangle;
    }

    public String toString() {
        return "x:" + width + " y:" + height + " " + color;
    }

}
