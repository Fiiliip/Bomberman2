package sk.uniza.fri.gui.shapes;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Canvas {

    private static Canvas platno;
    private JFrame canvas;

    private ArrayList<Object> objects;

    public Canvas(String title, int width, int height) {
        this.canvas = new JFrame(title);
        this.canvas.setSize(width, height);
        this.canvas.setVisible(true);

        this.objects = new ArrayList<Object>();

        platno = this;
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static Canvas getCanvas() {
        return platno;
    }

    public void redraw() {
        this.canvas.repaint();
    }

    public void draw(Object object, BufferedImage image) {
        this.objects.add(object);
        this.redraw();
    }
}
