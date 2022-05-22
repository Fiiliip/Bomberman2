package sk.uniza.fri.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class that stores loaded textures.
 * 27. 4. 2022 - 23:03
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class ResourceCollection {

    private static ResourceCollection resourceCollection;

    /**
     * Enum that stores loaded textures.
     */
    public enum Textures {
        BREAKABLE_WALL,
        UNBREAKABLE_WALL,
        EMPTY,
        BOMB,
        EXPLOSION_END_BIG,
        EXPLOSION_MIDDLE_BIG,
        EXPLOSION_START_BIG,
        ADD_BOMB,
        INCREASE_EXPLOSION,
        CROWN,
        HEARTH,
        BALLOM;

        private BufferedImage texture;

        /**
         * Sets texture.
         * @param texture to set
         */
        public void setTexture(BufferedImage texture) {
            this.texture = texture;
        }

        /**
         * Returns current texture.
         * @return texture
         */
        public BufferedImage getTexture() {
            return this.texture;
        }
    }

    /**
     * Creates resource collection.
     */
    public ResourceCollection() {
        resourceCollection = this;
    }

    /**
     * Loads resources.
     */
    public void loadResources() {
        Textures.BREAKABLE_WALL.setTexture(loadBufferedImage("./resources/textures/Blocks/BREAKABLE_WALL.png"));
        Textures.UNBREAKABLE_WALL.setTexture(loadBufferedImage("./resources/textures/Blocks/UNBREAKABLE_WALL.png"));
        Textures.EMPTY.setTexture(loadBufferedImage("./resources/textures/Blocks/EMPTY.png"));
        Textures.BOMB.setTexture(loadBufferedImage("./resources/textures/Bomba/Bomba.png"));
        Textures.EXPLOSION_END_BIG.setTexture(loadBufferedImage("./resources/textures/Explozia/EXPLOZIA_KONIEC_VELKA.png"));
        Textures.EXPLOSION_MIDDLE_BIG.setTexture(loadBufferedImage("./resources/textures/Explozia/EXPLOZIA_STRED_VELKA.png"));
        Textures.EXPLOSION_START_BIG.setTexture(loadBufferedImage("./resources/textures/Explozia/EXPLOZIA_ZACIATOK_VELKA.png"));
        Textures.ADD_BOMB.setTexture(loadBufferedImage("./resources/textures/Vylepšenie/PRIDAJ_BOMBU.png"));
        Textures.INCREASE_EXPLOSION.setTexture(loadBufferedImage("./resources/textures/Vylepšenie/ZVACSI_VYBUCH.png"));
        Textures.CROWN.setTexture(loadBufferedImage("./resources/textures/KORUNA.png"));
        Textures.HEARTH.setTexture(loadBufferedImage("./resources/textures/SRDCE.png"));
        Textures.BALLOM.setTexture(loadBufferedImage("./resources/textures/Enemies/Ballom.png"));
    }

    /**
     * Loads texture from given path in parameter.
     * @param pathToImage path to texture / image, which has to be loaded
     * @return loaded image
     */
    public static BufferedImage loadBufferedImage(String pathToImage) {
        BufferedImage loadedImage = null;

        try {
            loadedImage = ImageIO.read(new File(pathToImage));
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "File " + pathToImage + " was not found.");
        }

        return loadedImage;
    }
}
