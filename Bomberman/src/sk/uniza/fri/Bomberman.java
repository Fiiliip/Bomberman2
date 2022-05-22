package sk.uniza.fri;

import sk.uniza.fri.gameobjects.characters.Bomber;
import sk.uniza.fri.gameobjects.characters.Mob;
import sk.uniza.fri.gui.MobHUD;
import sk.uniza.fri.gui.ResourceCollection;
import sk.uniza.fri.gui.shapes.Obrazok;
import sk.uniza.fri.gameobjects.tileobjects.PowerUp;
import sk.uniza.fri.gameobjects.tileobjects.blocks.BreakableWall;
import sk.uniza.fri.gameobjects.tileobjects.blocks.Empty;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that manages the basic elements of the game as it is created.
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class Bomberman {

    private static final float CHANCE_FOR_POWERUP = 0.4f; // The larger the number, the greater the chance. Percentage principle, so 0.5f = 50%.

    private static Bomberman bomberman;

    private ResourceCollection resourceCollection;

    private ArrayList<Mob> mobs;
    private ArrayList<MobController> mobControllers;

    private ArrayList<MobHUD> bomberHUDS;

    /** Creates a Bomberman. */
    public Bomberman() {
        bomberman = this;

        this.resourceCollection = new ResourceCollection();
        this.resourceCollection.loadResources();

        Map map = new Map("./resources/maps/map.csv");

        this.mobs = new ArrayList<Mob>();
        this.mobControllers = new ArrayList<MobController>();

        this.bomberHUDS = new ArrayList<MobHUD>();

        this.createBomber("CERVENA", 60, 0, 1, 1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_G);
        this.createBomber("CIERNA", 450, 0, 18, 21, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
    }

    /**
     * Returns Bomberman.
     * @return bomberman
     */
    public static Bomberman getBomberman() {
        return bomberman;
    }

    /**
     * Creates a new player.
     * @param color player color
     * @param row position of the row on which the player is to be created
     * @param column position of the column on which the player is to be created
     * @param topLeftXHUD position of the upper left X on which the HUD is to be created
     * @param topLeftYHUD the position of the upper left Y on which the HUD is to be created
     * @param up key code to move up
     * @param down key code to move down
     * @param left key code to move left
     * @param right key code to move right
     * @param action the key code to activate the action
     */
    public void createBomber(String color, int topLeftXHUD, int topLeftYHUD, int row, int column, int up, int down, int left, int right, int action) {
        // Removes all breakable blocks in range 3x3, so Bomber can have safe start without blowing himself with bomb.
        for (int i = 0, r = row - 1; i < 3; i++, r++) {
            for (int j = 0, c = column - 1; j < 3; j++, c++) {
                if (Map.getMap().getTileObjects()[r][c] instanceof BreakableWall) {
                    Map.getMap().setTileObject(new Empty(r, c));
                }
            }
        }

        Bomber bomber = new Bomber(color, row, column);

        this.mobs.add(bomber);
        this.mobControllers.add(new MobController(bomber, up, down, left, right, action));
        this.bomberHUDS.add(new MobHUD(bomber, topLeftXHUD, topLeftYHUD));
    }

    /**
     * Takes the lives of all mobs at given coordinates.
     * @param row coordinates of the row in the array of mobs
     * @param column coordinates of the column in the array of mobs
     */
    public void takeMobsLivesAt(int row, int column) {
        for (Mob mob : this.mobs) {
            if (mob.getRow() == row && mob.getColumn() == column) {
                mob.takeLife();

                if (mob.getNumberOfLives() <= 0) {
                    this.mobs.remove(mob);

                    if (this.mobs.size() == 1) {
                        this.showWinner(this.mobs.get(0));
                    }
                }
            }
        }
    }

    /**
     * Displays the specified mob as the winner.
     * @param winner mob object
     */
    public void showWinner(Mob winner) {
        Obrazok iWinner = new Obrazok(winner.getTexture().getImage());
        iWinner.setPosition(330, 0);
        iWinner.zobraz();

        Obrazok crown = new Obrazok(ResourceCollection.Textures.CROWN.getTexture());
        crown.setPosition(330, 0);
        crown.zobraz();
    }

    /**
     * Generates power up at given coordinates.
     * @param row coordinates of the row in the tile array
     * @param column coordinates of the column in the tile array
     */
    public void generatePowerUpAt(int row, int column) {
        Random random = new Random();
        if (random.nextFloat() <= CHANCE_FOR_POWERUP) {
            Map.getMap().setTileObject(new PowerUp(row, column));
        }
    }
}
