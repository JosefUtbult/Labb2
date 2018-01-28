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
                if (r.intersectsWith(currentRoom)){
                    return false;
                }
            }
        }

        rooms.add(r);
        return true;

    }

    public void firstLocation(Room r) {

    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addObserver(Observable observer){
        observers.add(observer);
    }

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