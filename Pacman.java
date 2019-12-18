import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pacman extends JFrame implements KeyListener, Runnable {
    private int angle = 0, arrow = KeyEvent.VK_RIGHT, iterasi = 1;
    private body pacman, food;

    public Pacman() {
        pacman = new body(0, 30);
        food = new body(60, 60);
        init();
    }

    private void init() {
        this.setSize(new Dimension(600, 600));
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Pacman");
        this.addKeyListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void run() {
        while (true) {
            move();
            repaint();
            try {
                if (iterasi >= 3)
                    Thread.sleep(100);
                if (iterasi >= 7)
                    Thread.sleep(90);
                else
                    Thread.sleep(120);
            } catch (Exception e) {
                System.out.println("Missing");
            }
        }
    }

    public void move() {
        switch (arrow) {
        case KeyEvent.VK_LEFT: {
            pacman.setX(pacman.getX() - 30);
            angle = 240;
            break;
        }
        case KeyEvent.VK_RIGHT: {
            pacman.setX(pacman.getX() + 30);
            angle = 0;
            break;
        }
        case KeyEvent.VK_UP: {
            pacman.setY(pacman.getY() - 30);
            angle = 90;
            break;
        }
        case KeyEvent.VK_DOWN: {
            pacman.setY(pacman.getY() + 30);
            angle = 270;
            break;
        }
        }

        if (pacman.getX() >= getWidth())
            pacman.setX(0);
        else if (pacman.getY() >= getHeight())
            pacman.setY(30);
        else if (pacman.getY() <= 30)
            pacman.setY(getHeight());
        else if (pacman.getX() <= 0)
            pacman.setX(getWidth());

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
        case KeyEvent.VK_LEFT:
            arrow = KeyEvent.VK_LEFT;
            break;
        case KeyEvent.VK_RIGHT:
            arrow = KeyEvent.VK_RIGHT;
            break;
        case KeyEvent.VK_UP:
            arrow = KeyEvent.VK_UP;
            break;
        case KeyEvent.VK_DOWN:
            arrow = KeyEvent.VK_DOWN;
            break;
        }

    }

    public boolean isEat() {
        if (pacman.getX() == food.getX() && pacman.getY() == food.getY()) {
            int foodX = (int) (Math.random() * 19) + 1;
            int foodY = (int) (Math.random() * 19) + 1;
            food.setX(30 * foodX);
            food.setY(30 * foodY);
            iterasi++;
            return true;
        }
        return false;
    }

    public void paint(Graphics p) {
        p.setColor(Color.BLACK);
        p.fillRect(getX(), getY(), getWidth(), getHeight());

        p.setColor(Color.YELLOW);
        if (isEat()) {
            p.fillArc(pacman.getX(), pacman.getY(), pacman.getWidth(), pacman.getHeight(), angle, 360);
        } else {
            p.fillArc(pacman.getX(), pacman.getY(), pacman.getWidth(), pacman.getHeight(), angle, 315);
        }

        p.setColor(Color.RED);
        p.fillRect(food.getX(), food.getY(), food.getWidth(), food.getHeight());
    }

}