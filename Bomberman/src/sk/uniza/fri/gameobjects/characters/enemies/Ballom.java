package sk.uniza.fri.gameobjects.characters.enemies;

import sk.uniza.fri.gui.ResourceCollection;

/**
 * 27. 4. 2022 - 23:03
 *
 * @author Fíla
 */
public class Ballom extends Enemy {

    public Ballom(int row, int column) {
        super(ResourceCollection.Textures.BALLOM.getTexture(), row, column);
        this.numberOfLives = 1;
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void action() {

    }

    @Override
    public void destroy() {

    }
}
