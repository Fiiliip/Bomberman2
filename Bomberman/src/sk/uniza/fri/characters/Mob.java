package sk.uniza.fri.characters;

import sk.uniza.fri.GameObject;
import sk.uniza.fri.Manazer;
import sk.uniza.fri.Map;
import sk.uniza.fri.MobController;
import sk.uniza.fri.gui.MobHUD;
import sk.uniza.fri.tiles.blocks.BreakableWall;
import sk.uniza.fri.tiles.blocks.UnbreakableWall;
import sk.uniza.fri.tiles.Bomb;
import sk.uniza.fri.tiles.TileObject;

import java.awt.image.BufferedImage;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public abstract class Mob extends GameObject {

    private static final int TIME_FOR_IMMUNITY = 10;

    private int countOfSpecialAbility;

    private int numberOfLives;
    private boolean isImmune;

    private Manazer manazer;
    private int tickCount;

    private MobController controller;

    private MobHUD mobHUD;

    public Mob(BufferedImage texture, int row, int column) {
        super(texture, row, column);
        this.isImmune = false;

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.tickCount = 0;

        Map.getMap().setMob(this);
    }

    public void tik() {
        this.tickCount++;
        if (this.isImmune && this.tickCount <= TIME_FOR_IMMUNITY) {
            this.showImmunityEffect();
        } else {
            this.isImmune = false;
        }
    }

    public void setCountOfSpecialAbility(int countOfSpecialAbility) {
        this.countOfSpecialAbility = countOfSpecialAbility;
    }

    public int getCountOfSpecialAbility() {
        return this.countOfSpecialAbility;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

    public int getNumberOfLives() {
        return this.numberOfLives;
    }

    public void setMobController(MobController controller) {
        this.controller = controller;
    }

    public void setMobHUD(MobHUD mobHUD) {
        this.mobHUD = mobHUD;
    }

    public MobHUD getMobHUD() {
        return this.mobHUD;
    }

    public void moveTo(Direction direction) {
        // Zistím nové hodnoty pre riadok a stĺpec, na ktorý chcem ísť.
        int newRow = this.row + direction.getRow();
        int newColumn = this.column + direction.getColumn();

        // Skontrolujem, či blok, na ktorý chcem ísť je "kráčateľný", teda či sa na neho dá ísť.
        TileObject[][] tileObjects = Map.getMap().getTileObjects();
//        if (tileObjects[newRow][newColumn] instanceof BreakableWall || tileObjects[newRow][newColumn] instanceof UnbreakableWall || tileObjects[newRow][newColumn] instanceof Bomb) {
//            return;
//        }
        if (!tileObjects[newRow][newColumn].isWalkable()) {
            return;
        }

        this.setPosition(newRow, newColumn); // Nastavím novú pozíciu.
        tileObjects[newRow][newColumn].handleCollision(this); // Blok, na ktorý som sa nastavil spracuje kolíziu s mobom.
        Map.getMap().setMob(this); // Nastavím moba na novú pozíciu na mape.
    }

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

    @Override
    public void destroy() {
        this.hideTexture();

        if (this.controller != null) {
            this.controller.remove();
        }
    }

    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void action();
}
