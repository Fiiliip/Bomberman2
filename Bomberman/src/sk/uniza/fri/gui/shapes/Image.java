package sk.uniza.fri.gui.shapes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    private BufferedImage image;

    public Image(String fileWithTexture) {
        this.image = this.loadImageFromFile(fileWithTexture);
    }

    public int getWidth() {
        return this.image.getWidth();
    }

    public int getHeight() {
        return this.image.getHeight();
    }

    private BufferedImage loadImageFromFile(String fileWithTexture) {
        BufferedImage loadedImage = null;
        try {
            loadedImage = ImageIO.read(new File(fileWithTexture));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Canvas.getCanvas().draw(this, loadedImage);

        return loadedImage;
    }
}
