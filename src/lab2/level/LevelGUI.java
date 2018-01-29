package lab2.level;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

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

        d.repaint();
    }

    public boolean hasChanged() {
        d.repaint();
        return true;
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
        }

        /**
         * Draws a rectangular room on the display.
         *
         * @param g Graphics Java Graphics object.
         * @param room Room The Room that is to be painted.
         */
        private void paintRoom(Graphics g, Room room) {

            int wallWidth = 10;

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

        private void paintPlayer(Graphics g){

            int size = 100;
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

                System.out.println(lv.move(event.getKeyChar()) ? "Moved." : "Could not move.");
            }
        }

    }

    public Level getLv(){
        return lv;
    }

}
