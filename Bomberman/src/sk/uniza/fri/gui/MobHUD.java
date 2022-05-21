package sk.uniza.fri.gui;

import sk.uniza.fri.ResourceCollection;
import sk.uniza.fri.characters.Mob;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class MobHUD {

    private Mob mob;

    private Obrazok player;
    private Obrazok hearth;
    private Obrazok bomb;

    private CiselnyDisplej numberOfLives;
    private CiselnyDisplej numberOfBombs;

    public MobHUD(Mob mob, int topLeftX, int topLeftY) {
        this.mob = mob;
        this.mob.setMobHUD(this);

        this.player = new Obrazok(mob.getTexture().getImage());
//        this.player = new Obrazok("./resources/textures/Charakter/CHARAKTER_" + this.mob.getColor() + "_" + Direction.DOWN + ".png");
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

    public void updateValues() {
        this.numberOfLives.setHodnota(this.mob.getNumberOfLives());
        this.numberOfBombs.setHodnota(this.mob.getCountOfSpecialAbility());
    }
}
