package sk.uniza.fri.gameobjects.characters;

import sk.uniza.fri.gameobjects.GameObject;
import sk.uniza.fri.Manazer;
import sk.uniza.fri.Map;
import sk.uniza.fri.MobController;
import sk.uniza.fri.gui.MobHUD;

import java.awt.image.BufferedImage;

/**
 * Abstract class for Mobs which stores basic informations.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public abstract class Mob extends GameObject {

    private static final int TIME_FOR_IMMUNITY = 10;

    protected int numberOfLives;
    private boolean isImmune;

    private int tickCount;
    private Manazer manazer;

    private MobController controller;

    private MobHUD mobHUD;

    /**
     * Creates new Mob with given parameters.
     * @param texture texture of Mob
     * @param row position Y
     * @param column position X
     */
    public Mob(BufferedImage texture, int row, int column) {
        super(texture, row, column);
        this.isImmune = false;

        Map.getMap().setMob(this);

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.tickCount = 0;
    }

    /**
     * Method, which is called every x (set in class Manazer, current 0.25 seconds) nanosecond by Manazer.
     */
    public void tik() {
        this.tickCount++;
        if (this.isImmune && this.tickCount <= TIME_FOR_IMMUNITY) {
            this.showImmunityEffect();
        } else {
            this.isImmune = false;
        }
    }

    /**
     * Returns current number of lives.
     * @return number of lives
     */
    public int getNumberOfLives() {
        return this.numberOfLives;
    }

    /**
     * Sets controller, which controls this Mob.
     * @param controller controller with controls
     */
    public void setMobController(MobController controller) {
        this.controller = controller;
    }

    /**
     * Sets HUD, which displays informations about this Mob.
     * @param mobHUD to set
     */
    public void setMobHUD(MobHUD mobHUD) {
        this.mobHUD = mobHUD;
    }

    /**
     * Returns set MobHUD.
     * @return mobHUD to set
     */
    public MobHUD getMobHUD() {
        return this.mobHUD;
    }

    /**
     * Moves this Mob to direction given in params, if possible.
     * @param direction direction to move in
     */
    public void moveTo(Direction direction) {
        // Zistím nové hodnoty pre riadok a stĺpec, na ktorý chcem ísť.
        int newRow = this.row + direction.getRow();
        int newColumn = this.column + direction.getColumn();


        // Skontrolujem, či blok, na ktorý chcem ísť je "kráčateľný", teda či sa na neho dá ísť.
        if (!Map.getMap().getTileObjects()[newRow][newColumn].isWalkable()) {
            return;
        }

        this.setPosition(newRow, newColumn); // Nastavím novú pozíciu.
        Map.getMap().setMob(this); // Nastavím moba na novú pozíciu na mape.
    }

    /**
     * Takes Mob life if not immune. If it takes last Mob life, it will destroy him.
     */
    public void takeLife() {
        if (this.isImmune) {
            return;
        }

        this.numberOfLives--;
        this.mobHUD.updateValues();
        if (this.numberOfLives <= 0) {
            this.destroy();
        } else {
            this.tickCount = 0;
            this.isImmune = true;
        }
    }

    /**
     * Show effect for Immunity.
     */
    public void showImmunityEffect() {
        if (this.tickCount >= TIME_FOR_IMMUNITY) {
            this.showTexture();
        } else {
            if (this.tickCount % 2 == 0) {
                this.showTexture();
            } else {
                this.hideTexture();
            }
        }
    }

    public abstract int getCountOfSpecialAbility();

    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void action();

    /**
     * Destroys current object.
     */
    @Override
    public void destroy() {
        this.hideTexture();

        if (this.controller != null) {
            this.controller.remove();
        }
    }
}
