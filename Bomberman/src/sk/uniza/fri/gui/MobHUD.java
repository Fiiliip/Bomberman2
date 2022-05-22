package sk.uniza.fri.gui;

import sk.uniza.fri.gameobjects.characters.Mob;
import sk.uniza.fri.gui.numberdisplay.CiselnyDisplej;
import sk.uniza.fri.gui.shapes.Obrazok;

/**
 * Class that shows basic informations about mob (health, count of special ability).
 * 27. 4. 2022 - 23:03
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class MobHUD {

    private Mob mob;

    private Obrazok player;
    private Obrazok hearth;
    private Obrazok bomb;

    private CiselnyDisplej numberOfLives;
    private CiselnyDisplej numberOfBombs;

    /**
     * Creates mob HUD for specified mob at given coordinates.
     * @param mob mob to display information about
     * @param topLeftX top left X position at which to display mob HUD
     * @param topLeftY top left Y position at which to display mob HUD
     */
    public MobHUD(Mob mob, int topLeftX, int topLeftY) {
        this.mob = mob;
        this.mob.setMobHUD(this);

        this.player = new Obrazok(mob.getTexture().getImage());
        this.player.setPosition(topLeftX, topLeftY);
        this.player.zobraz();

        this.hearth = new Obrazok(ResourceCollection.Textures.HEARTH.getTexture());
        this.hearth.setPosition(topLeftX + 45, topLeftY + 1);
        this.hearth.zobraz();

        this.bomb = new Obrazok(ResourceCollection.Textures.BOMB.getTexture());
        this.bomb.setPosition(topLeftX + 45 + 30 + 45, topLeftY + 2);
        this.bomb.zobraz();

        this.numberOfLives = new CiselnyDisplej(100, topLeftX + 45 + 30, topLeftY + 5);
        this.numberOfLives.setHodnota(this.mob.getNumberOfLives());

        this.numberOfBombs = new CiselnyDisplej(100, topLeftX + 45 + 30 + 45 + 30, topLeftY + 5);
        this.numberOfBombs.setHodnota(this.mob.getCountOfSpecialAbility());
    }

    /**
     * Updates values on the HUD.
     */
    public void updateValues() {
        this.numberOfLives.setHodnota(this.mob.getNumberOfLives());
        this.numberOfBombs.setHodnota(this.mob.getCountOfSpecialAbility());
    }
}
