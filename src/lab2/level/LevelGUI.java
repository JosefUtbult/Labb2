package lab2.level;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class that implements a GUI for a simple 2D-game level with rooms.
 *
 * @author Håkan Jonsson (Course instructor)
 * @author Josef Utbult
 * @author Oscar Brink
 */
public class LevelGUI implements Observer {

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

    /**
     * Repains the level when
     * @param arg0
     * @param arg1
     */
    public void update(Observable arg0, Object arg1) {

        d.repaint();
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
        }

        /**
         * Draws a rectangular room on the display.
         *
         * @param g Graphics Java Graphics object.
         * @param room Room The Room that is to be painted.
         */
        private void paintRoom(Graphics g, Room room) {

            int wallWidth = 10;

            g.setColor(room.getColorObject());
            g.fillRect(
                    room.getPosX(),
                    room.getPosY(),
                    room.getWidth(),
                    room.getHeight()
            );


            g.setColor(room.getColorObject().darker().darker());
            g.fillRect(
                    (int) room.getPosX(),
                    (int) room.getPosY(),
                    (int) room.getWidth(),
                    wallWidth
            );

            g.fillRect(
                    (int) room.getPosX() + (int) room.getWidth() - wallWidth,
                    (int) room.getPosY(),
                    wallWidth,
                    (int) room.getHeight()
            );

            g.fillRect(
                    (int) room.getPosX(),
                    (int) room.getPosY(),
                    wallWidth,
                    (int) room.getHeight()
            );

            g.fillRect(
                    (int) room.getPosX(),
                    (int) room.getPosY() + (int) room.getHeight() - wallWidth,
                    (int) room.getWidth(),
                    wallWidth
            );


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
