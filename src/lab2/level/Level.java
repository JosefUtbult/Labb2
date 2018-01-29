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

    private static ArrayList<Room> rooms = new ArrayList<>();

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
        currentRoom = r;
    }

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
    }


    /**
     * Method executes movement from one room in the level to another.
     *
     * @param direction char that gives the direction of the movement
     * @return boolean true if movement succeeded, false otherwise.
     */
    public boolean move(char direction){

        switch (direction) {
        case 'w':
            if(currentRoom.getNorth() != null){
                currentRoom = currentRoom.getNorth();

                update();
                System.out.println("You know da wae");

                return true;
            }
            break;

        case 's':
            if(currentRoom.getSouth() != null){
                currentRoom = currentRoom.getSouth();

                update();
                System.out.println("You know da wae");

                return true;
            }
            break;

        case 'a':
            if(currentRoom.getWest() != null){
                currentRoom = currentRoom.getWest();

                update();
                System.out.println("You know da wae");

                return true;
            }
            break;

        case 'd':
            if(currentRoom.getEast() != null){
                currentRoom = currentRoom.getEast();

                update();
                System.out.println("You know da wae");

                return true;
            }
            break;
        default:
			break;
        }
        System.out.println("That is not tha wae. Do you know da wae?");
        return false;
    }

    public void update(){
        setChanged();
        notifyObservers();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}