package lab2.level;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class Level extends Observable {

    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Observable> observers = new ArrayList<>();

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

    private boolean collisionDetection(Room room1, Room room2) {
        return room1.getRectangleObject().intersects(room2.getRectangleObject());
    }

    private void area() {
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



}