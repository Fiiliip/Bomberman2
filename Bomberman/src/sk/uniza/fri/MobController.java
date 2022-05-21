package sk.uniza.fri;

import sk.uniza.fri.characters.Mob;
import sk.uniza.fri.gui.Platno;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class MobController implements KeyListener {

    private Mob mob;

    private int up;
    private int down;
    private int left;
    private int right;
    private int action;

    public MobController(Mob mob, int up, int down, int left, int right, int action) {
        this.mob = mob;

        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.action = action;

        this.mob.setMobController(this);
        Platno.dajPlatno().addKeyListener(this);
    }

    public void remove() {
        Platno.dajPlatno().removeKyeListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
