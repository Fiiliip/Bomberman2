package sk.uniza.fri;

import sk.uniza.fri.characters.Bomber;
import sk.uniza.fri.gui.Platno;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class BomberController implements KeyListener {

    private Bomber bomber;
    private int up;
    private int down;
    private int left;
    private int right;
    private int action;

    private HashMap<Integer, Runnable> controller;

    public BomberController(Bomber bomber, int up, int down, int left, int right, int action) {
        this.controller = new HashMap<Integer, Runnable>();

        this.controller.put(up, () -> bomber.moveUp());
        this.controller.put(down, () -> bomber.moveDown());
        this.controller.put(left, () -> bomber.moveLeft());
        this.controller.put(right, () -> bomber.moveRight());
        this.controller.put(up, () -> bomber.moveUp());

        this.bomber = bomber;

        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.action = action;

        Platno.dajPlatno().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == this.up) {
            this.bomber.moveUp();
        } else if (event.getKeyCode() == this.down) {
            this.bomber.moveDown();
        } else if (event.getKeyCode() == this.left) {
            this.bomber.moveLeft();
        } else if (event.getKeyCode() == this.right) {
            this.bomber.moveRight();
        } else if (event.getKeyCode() == this.action) {
            this.bomber.action();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
