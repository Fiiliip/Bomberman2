package sk.uniza.fri;

import sk.uniza.fri.gameobjects.characters.Mob;
import sk.uniza.fri.gameobjects.tileobjects.blocks.BreakableWall;
import sk.uniza.fri.gameobjects.tileobjects.blocks.Empty;
import sk.uniza.fri.gameobjects.tileobjects.blocks.UnbreakableWall;
import sk.uniza.fri.gameobjects.tileobjects.TileObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that handles loading the map from a semicolon-separated character set (;) and its generation according to the characters being read.
 * Characters representing individual Typees of blocks are:
 * N - indestructible wall,
 * Z - destructible wall,
 * O - empty block
 *
 * @author Fiiliip (https://github.com/Fiiliip)
 */
public class Map {

    private static Map map;

    private static final float CHANCE_FOR_BREAKABLE_WALL = 0.6f;

    private static final char BREAKABLE_WALL = 'Z';
    private static final char UNBREAKABLE_WALL = 'N';
    private static final char EMPTY = 'O';

    private int width;
    private int height;

    private TileObject[][] tileObjects;
    private Mob[][] mobs;

    /**
     * Creates new map.
     */
    public Map() {
        map = this;

        this.width = 0;
        this.height = 0;
    }

    /**
     * Creates new map based on characters stored in file with map.
     * @param fileWithMap path to file with map
     */
    public Map(String fileWithMap) {
        this();
        this.loadFromFile(fileWithMap);

        this.mobs = new Mob[this.height][this.width];
    }

    /**
     * Returns static Map.
     * @return map
     */
    public static Map getMap() {
        return map;
    }

    /**
     * Returns an array with tile objects.
     * @return array with tile objects
     */
    public TileObject[][] getTileObjects() {
        return this.tileObjects;
    }

    /**
     * Sets tile object at tile position.
     * @param tileObject to set to array with tile objects
     */
    public void setTileObject(TileObject tileObject) {
        this.tileObjects[tileObject.getRow()][tileObject.getColumn()] = tileObject;
    }

    /**
     * Sets mob at mob position.
     * @param mob to set to array with mobs
     */
    public void setMob(Mob mob) {
        this.mobs[mob.getRow()][mob.getColumn()] = mob;
    }

    /**
     * Generates a tile map based on the characters the ArrayList.
     * @param map arraylist of arraylist of characters
     */
    public void generate(ArrayList<ArrayList<Character>> map) {
        this.width = map.get(0).size();
        this.height = map.size();

        this.tileObjects = new TileObject[this.height][this.width];
        for (int row = 0; row < this.height; row++) {
            for (int column = 0; column < this.width; column++) {
                char character = map.get(row).get(column);
                switch (character) {
                    case UNBREAKABLE_WALL:
                        this.tileObjects[row][column] = new UnbreakableWall(row, column);
                        break;
                    case EMPTY:
                        this.tileObjects[row][column] = new Empty(row, column);
                        break;
                    case BREAKABLE_WALL:
                        this.tileObjects[row][column] = new BreakableWall(row, column);
                        break;
                    default:
                        this.tileObjects[row][column] = new Empty(row, column);
                        System.out.println("Unknow value | " + character + " | at map generation on " + column + ". columnd and " + row + ". row.");
                        //throw new RuntimeException("Unknow value | " + character + " | at map generation on " + column + ". columnd and " + row + ". row."); TODO: dokonči fungujúci Exception.
                        break;
                }
            }
        }
    }

    /**
     * Returns array list of array list of characters, which are loaded from map file.
     * @param fileWithMap path to the .csv file with characters
     */
    public void loadFromFile(String fileWithMap) {
        ArrayList<ArrayList<Character>> mapFromFile = new ArrayList<ArrayList<Character>>();

        boolean containsBreakableWall = false;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileWithMap));
            String row = "";
            while ((row = bufferedReader.readLine()) != null) {
                ArrayList<Character> characters = new ArrayList<Character>();
                for (int i = 0; i < row.length(); i++) {
                    if (row.charAt(i) != ';') {
                        if (row.charAt(i) == BREAKABLE_WALL) {
                            containsBreakableWall = true;
                        }
                        characters.add(row.charAt(i));
                    }
                }
                mapFromFile.add(characters);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        if (!containsBreakableWall) {
            for (int list = 0; list < mapFromFile.size(); list++) {
                for (int character = 0; character < mapFromFile.get(list).size(); character++) {
                    if (mapFromFile.get(list).get(character) == EMPTY && random.nextFloat() <= CHANCE_FOR_BREAKABLE_WALL) {
                        mapFromFile.get(list).set(character, BREAKABLE_WALL);
                    }
                }
            }
        }

        this.generate(mapFromFile);
    }
}
