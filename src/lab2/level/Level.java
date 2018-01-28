package lab2.level;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Class creates a 2D-level for use in a simple game.
 *
 * @author Håkan Jonsson (Course instructor)
 * @author Josef Utbult
 * @author Oscar Brink
 */
public class Level extends Observable {

    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Observable> observers = new ArrayList<>();

    private Room currentRoom;

    /**
     * Method takes in a Room object and tries to place it at the point
     * (x, y) in the level.
     *
     * @param r Room Room object to place in the level.
     * @param x int x-position to place at.
     * @param y int y-position to place at.
     * @return  boolean Returns true if room doesn't intersect with any other
     *          room, false otherwise.
     */
    public boolean place(Room r, int x, int y)  {

        r.setPos(x, y);

        if(this.rooms.size() != 0){
            for (Room currentRoom : rooms) {
                if (collisionDetection(r, currentRoom)){
                    return false;
                }
            }
        }

        rooms.add(r);
        return true;

    }

    public void firstLocation(Room r) {

    }

    /**
     * Checks that one Room doesn't intersect with another.
     *
     * @param room1 Room room1 to check.
     * @param room2 Room room2 to check.
     * @return boolean Returns true if the rooms intersect, false otherwise.
     */
    private boolean collisionDetection(Room room1, Room room2) {
        return room1.getRectangleObject().intersects(room2.getRectangleObject());
    }

    private void roomArea() {
        //TODO maybe
    }
/*
    private boolean collisionDetecton(Room room1, Room room2){
        return lowerCollisionDetecton(room1, room2) || lowerCollisionDetecton(room2, room1);
    }

    private boolean lowerCollisionDetecton(Room room1, Room room2){

        return (room1.getPosX() < room2.getPosX() && room2.getPosX() < room1.getPosX() + room1.getWidth()) ||
               (room1.getPosY() < room2.getPosY() && room2.getPosY() < room1.getPosY() + room1.getHeight());

    }
*/

    /**
     * Returns the array list of rooms, contained in the level.
     * @return
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * "observer" are an array of observer objects, that contains every object that have to be updated
     * every time something changes, that has to be rendered.s
     * @param observer
     */
    public void addObserver(Observable observer){
        observers.add(observer);
    }


    /**
     * Overides notifyObserver from Observable, going through every object in "observers" and runs notify.
     */
    public void notifyObserver(){
        for (Observable currentObserver : observers) {
            currentObserver.notify();
        }
    }

    public void setChanged(){

    }

    /**
     * Method executes movement from one room in the level to another.
     *
     * @param direction char that gives the direction of the movement
     * @return boolean true if movement succeeded, false otherwise.
     */
    public boolean move(char direction){

        notifyObserver();

        switch (direction) {
        case 'n':
            if(currentRoom.getNorth() != null){
                currentRoom = currentRoom.getNorth();
                return true;
            }
            break;

        case 's':
            if(currentRoom.getSouth() != null){
                currentRoom = currentRoom.getSouth();
                return true;
            }
            break;

        case 'w':
            if(currentRoom.getWest() != null){
                currentRoom = currentRoom.getWest();
                return true;
            }
            break;

        case 'e':
            if(currentRoom.getEast() != null){
                currentRoom = currentRoom.getEast();
                return true;
            }
            break;
        default:
            break;
        }

        return false;
    }

}