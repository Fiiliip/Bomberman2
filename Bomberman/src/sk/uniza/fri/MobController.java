package sk.uniza.fri;

import sk.uniza.fri.gameobjects.characters.Mob;
import sk.uniza.fri.gui.shapes.Platno;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Class that manages player control.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class MobController implements KeyListener {

    private Mob mob;

    private int up;
    private int down;
    private int left;
    private int right;
    private int action;

    private HashMap<Integer, Runnable> controller;

    /**
     * Creates mob controller.
     * @param mob player object
     * @param up key code to move up
     * @param down key code to move down
     * @param left key code to move left
     * @param right key code to move right
     * @param action key code to activate the action
     */
    public MobController(Mob mob, int up, int down, int left, int right, int action) {
        this.mob = mob;

//        this.controller = new HashMap<Integer, Runnable>();
//        this.controller.put(up, () -> this.mob.moveUp());
//        this.controller.put(down, () -> this.mob.moveDown());
//        this.controller.put(left, () -> this.mob.moveLeft());
//        this.controller.put(right, () -> this.mob.moveRight());
//        this.controller.put(action, () -> this.mob.action());

        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.action = action;

        this.mob.setMobController(this);
        Platno.dajPlatno().addKeyListener(this);
    }

    /**
     * Removes current controller.
     */
    public void remove() {
        Platno.dajPlatno().removeKyeListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Registers every pressed key and performs action corresponding to the key.
     * @param event event
     */
    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == this.up) {
            this.mob.moveUp();
        } else if (event.getKeyCode() == this.down) {
            this.mob.moveDown();
        } else if (event.getKeyCode() == this.left) {
            this.mob.moveLeft();
        } else if (event.getKeyCode() == this.right) {
            this.mob.moveRight();
        } else if (event.getKeyCode() == this.action) {
            this.mob.action();
        }

//        this.controller.get(event.getKeyCode()).run();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
