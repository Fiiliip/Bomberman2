package sk.uniza.fri;

import sk.uniza.fri.characters.Bomber;
import sk.uniza.fri.tiles.blocks.BreakableWall;
import sk.uniza.fri.tiles.blocks.Empty;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Bomberman {

    private ResourceCollection resourceCollection;

    private ArrayList<Bomber> bombers;
    private ArrayList<BomberController> bomberControllers;

    public Bomberman() {
        this.resourceCollection = new ResourceCollection();
        this.resourceCollection.loadResources();

        Map map = new Map("./resources/maps/map.csv");

        this.bombers = new ArrayList<Bomber>();
        this.bomberControllers = new ArrayList<BomberController>();

        this.createBomber("CERVENA", 1, 1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_G);
        this.createBomber("CIERNA", 18, 21, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
    }

    public void createBomber(String color, int row, int column, int up, int down, int left, int right, int action) {
        // Removes all breakable blocks in range 3x3, so Bomber can have safe start without blowing himself with bomb.
        for (int i = 0, r = row - 1; i < 3; i++, r++) {
            for (int j = 0, c = column - 1; j < 3; j++, c++) {
                if (Map.getMap().getTileObjects()[r][c] instanceof BreakableWall) {
                    Map.getMap().setTileObject(new Empty(r, c));
                }
            }
        }

        Bomber bomber = new Bomber(color, row, column);

        this.bombers.add(bomber);
        this.bomberControllers.add(new BomberController(bomber, up, down, left, right, action));
    }
}
