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


public class LevelGUI implements Observer {

    private Level lv;
    private Display d;


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
    }

    public void update(Observable arg0, Object arg1) {

    }

    private class Display extends JPanel {

        private Level fp;

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
         * @param g Java Graphics object.
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (Room room : fp.getRooms()) {
                paintRoom(g, room);
            }

            //super.paintComponent(g);


        }

        /**
         * Draws a rectangular room on the display.
         *
         * @param g Java Graphics object.
         * @param room Room object
         */
        private void paintRoom(Graphics g, Room room) {
            g.setColor(room.getColorObject());
            g.fillRect(
                    (int) room.getRectangleObject().getX(),
                    (int) room.getRectangleObject().getY(),
                    (int) room.getRectangleObject().getWidth(),
                    (int) room.getRectangleObject().getHeight()
            );
        }


        private class Listener implements KeyListener {


            public void keyPressed(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyTyped(KeyEvent event) {
            }
        }

    }

    public Level getLv(){
        return lv;
    }

}
