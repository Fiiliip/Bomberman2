package sk.uniza.fri.characters.enemies;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Minvo extends Enemy {

    public Minvo(String fileWithTexture, int x, int y) {
        super(fileWithTexture, x, y);

        this.setNumberOfLives(1);
    }
}
