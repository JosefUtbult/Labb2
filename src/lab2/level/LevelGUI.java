package lab2.level;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Class that implements a GUI for a simple 2D-game level with rooms.
 *
 * @author Håkan Jonsson (Course instructor)
 * @author Josef Utbult
 * @author Oscar Brink
 */
public class LevelGUI implements Observer{

    private Level lv;
    private Display d;
    private Color[] flashingColors;
    private int currentFlashingColor = -1;

    private boolean raveMode = false;

    /**
     * Constructor.
     *
     * @param level Level Level that will be presented on the GUI.
     * @param name String Name of the level and window.
     */
    public LevelGUI(Level level, String name) {

        this.lv = level;

        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: You should change 200 to a value
        // depending on the size of the level
        d = new Display(lv,200,200);

        frame.getContentPane().add(d);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        lv.addObserver(this);

        flashingColors = new Color[]{Color.blue, Color.red, Color.green, Color.yellow};

    }

    public void paint(){
        d.repaint();
    }


    /**
     * Repains the level when
     * @param arg0
     * @param arg1
     */
    public void update(Observable arg0, Object arg1) {

        if(lv.getCurrentRoom().getColorObject() == Color.BLUE){
            raveMode = true;
        }

        d.repaint();
    }

    public void updateFlashingColor(){

        if(this.currentFlashingColor != -1){
            currentFlashingColor = new Random().nextInt(flashingColors.length);
        }
        int temp = this.currentFlashingColor;

        while (this.currentFlashingColor == temp){
            this.currentFlashingColor = new Random().nextInt(flashingColors.length);
        }
    }

    public Level getLv(){
        return lv;
    }

    public void setRaveMode(boolean raveMode){
        this.raveMode = raveMode;
    }


    /**
     * A class extended from JPanel
     *
     */
    private class Display extends JPanel {

        private Level fp;

        /**
         * Constructor.
         *
         * @param fp Level Level that will be drawn.
         * @param x int Size of the level-x.
         * @param y int Size of the level-y.
         */
        public Display(Level fp, int x, int y) {

            this.fp = fp;
            addKeyListener(new Listener());

            setBackground(Color.darkGray);
            setPreferredSize(new Dimension(x+20,y+20));
            setFocusable(true);
        }

        public void repaint(Graphics g){
            paintComponent(g);
        }

        /**
         * Overrides paintComponent from JComponent to draw rooms.
         *
         * @param g Graphics Java Graphics object.
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (Room room : fp.getRooms()) {
                paintRoom(g, room);
            }

            paintPlayer(g);

            if(raveMode){
                paintFlashingLights(g);
            }
        }

        private void paintFlashingLights(Graphics g){

            if(currentFlashingColor != -1){
                g.setColor(new Color(flashingColors[currentFlashingColor].getRed(), flashingColors[currentFlashingColor].getGreen(), flashingColors[currentFlashingColor].getBlue(), 100));
                g.fillRect(0, 0, (int)getBounds().getWidth(), (int)getBounds().getHeight());
            }

        }

        /**
         * Draws a rectangular room on the display.
         *
         * @param g Graphics Java Graphics object.
         * @param room Room The Room that is to be painted.
         */
        private void paintRoom(Graphics g, Room room) {

            int wallWidth = fp.getWallWidth();

            if(room.getColorObject() != Color.darkGray || lv.getCurrentRoom() == room){

                g.setColor(room.getColorObject().darker().darker());
                g.fillRect(
                        room.getPosX(),
                        room.getPosY(),
                        room.getWidth(),
                        room.getHeight()
                );

                g.setColor(room.getColorObject());
                g.fillRect(
                        room.getPosX() + wallWidth,
                        room.getPosY() + wallWidth,
                        room.getWidth() - wallWidth * 2,
                        room.getHeight() - wallWidth * 2
                );
            }
            else{
                g.setColor(room.getColorObject());
                g.fillRect(
                        room.getPosX(),
                        room.getPosY(),
                        room.getWidth(),
                        room.getHeight()
                );
            }
            paintDoorways(g, room);
        }

        private void paintDoorways(Graphics g, Room room) {
            int doorwayWidth = fp.getDoorwaySize();
            int wallWidth = fp.getWallWidth();
            char direction;

            int xPos, yPos;
            Color tempColor = room.getColorObject();

            if (room.getNorth() != null) {
                direction = 'n';
                xPos = room.getPosX() + room.getWidth()/2 - doorwayWidth/2;
                yPos = room.getPosY();
                if (!room.isAdjacentTo(room.getNorth(), direction)) {
                    g.setColor(Color.cyan);
                }
                g.fillRect(
                        xPos,
                        yPos,
                        doorwayWidth,
                        wallWidth
                );
                if (room.getNorth().getSouth() != room) {
                    paintPortalDirection(g, room, true, xPos, yPos, direction);
                }
                g.setColor(tempColor);
            }
            if (room.getSouth() != null) {
                direction = 's';
                xPos = room.getPosX() + room.getWidth()/2 - doorwayWidth/2;
                yPos = room.getPosY() + room.getHeight() - wallWidth;
                if (!room.isAdjacentTo(room.getSouth(), direction)) {
                    g.setColor(Color.cyan);
                }
                g.fillRect(
                        xPos,
                        yPos,
                        doorwayWidth,
                        wallWidth
                );
                if (room.getSouth().getNorth() != room) {
                    paintPortalDirection(g, room, true, xPos, yPos, direction);
                }
                g.setColor(tempColor);
            }
            if (room.getWest() != null) {
                direction = 'w';
                xPos = room.getPosX();
                yPos = room.getPosY() + room.getHeight()/2 - doorwayWidth/2;

                if(!room.isAdjacentTo(room.getWest(), direction)) {
                    g.setColor(Color.cyan);
                }
                g.fillRect(
                        xPos,
                        yPos,
                        wallWidth,
                        doorwayWidth
                );
                if (room.getWest().getEast() != room) {
                    paintPortalDirection(g, room, true, xPos, yPos, direction);
                }
                g.setColor(tempColor);
            }
            if (room.getEast() != null) {
                direction = 'e';
                xPos = room.getPosX() + room.getWidth() - wallWidth;
                yPos = room.getPosY() + room.getHeight()/2 - doorwayWidth/2;
                if (!room.isAdjacentTo(room.getEast(), direction)) {
                    g.setColor(Color.cyan);
                }
                g.fillRect(
                        xPos,
                        yPos,
                        wallWidth,
                        doorwayWidth
                );
                if (room.getEast().getWest() != room) {
                    paintPortalDirection(g, room, true, xPos, yPos, direction);
                }
                g.setColor(tempColor);
            }
        }

        private void paintPortalDirection(Graphics g, Room room, boolean outgoing, int xPos, int yPos, char direction) {
            int doorwayWidth = fp.getDoorwaySize();
            int wallWidth = fp.getWallWidth();
            int[] xArr;
            int[] yArr;

            switch(direction) {
                case 'n':
                    xArr = new int[]{xPos, xPos + doorwayWidth/2, xPos + doorwayWidth};
                    if (outgoing) {
                        yArr = new int[]{yPos, yPos - wallWidth, yPos};
                    } else {
                        yArr = new int[]{yPos - wallWidth, yPos, yPos - wallWidth};
                    }
                    break;
                case 's':
                    xArr = new int[]{xPos, xPos + doorwayWidth/2, xPos + doorwayWidth};
                    if (outgoing) {
                        yArr = new int[]{yPos, yPos + wallWidth, yPos};
                    } else {
                        yArr = new int[]{yPos, yPos + wallWidth, yPos};
                    }
                    break;
                case 'w':
                    yArr = new int[]{yPos, yPos + doorwayWidth/2, yPos + doorwayWidth};
                    if (outgoing) {
                        xArr = new int[]{xPos + wallWidth, xPos, xPos + wallWidth};
                    } else {
                        xArr = new int[]{xPos, xPos + wallWidth, xPos};
                    }
                    break;
                case 'e':
                    yArr = new int[]{yPos, yPos + doorwayWidth/2, yPos + doorwayWidth};
                    if (outgoing) {
                        xArr = new int[]{xPos, xPos + wallWidth, xPos};
                    } else {
                        xArr = new int[]{xPos + wallWidth, xPos, xPos + wallWidth};
                    }
                    break;
                default:
                    System.out.println("\'" + direction + "\' not a direction.");
                    return;
            }
            g.setColor(g.getColor().darker());
            g.fillPolygon(xArr, yArr, 3);
        }

        /**
         * Displaying a player icon from "images/Player.png"
         * @param g
         */
        private void paintPlayer(Graphics g){

            int size = (int)(((double)Integer.min(lv.getCurrentRoom().getWidth(), lv.getCurrentRoom().getHeight())) * 0.7);


            JLayeredPane jLayeredPane = new JLayeredPane();

            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("images/Player.png"));
            } catch (IOException e) {
            }

            g.drawImage(img,
                    (lv.getCurrentRoom().getPosX() + lv.getCurrentRoom().getWidth() / 2) - size / 2,
                    (lv.getCurrentRoom().getPosY() + lv.getCurrentRoom().getHeight() / 2) - size / 2,
                    size, size, jLayeredPane);

            this.add(jLayeredPane);
        }


        private class Listener implements KeyListener {


            public void keyPressed(KeyEvent arg0) {

            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyTyped(KeyEvent event) {

                //System.out.format("%s was pressed\n", event.getKeyChar());

                lv.move(event.getKeyChar());
            }
        }

    }


}
