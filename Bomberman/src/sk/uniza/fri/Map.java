package sk.uniza.fri;

import sk.uniza.fri.tiles.blocks.BreakableWall;
import sk.uniza.fri.tiles.blocks.Empty;
import sk.uniza.fri.tiles.blocks.UnbreakableWall;
import sk.uniza.fri.tiles.TileObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Map {

    private static Map map;

    private static final float CHANCE_FOR_BREAKABLE_WALL = 0.6f;

    private static final char BREAKABLE_WALL = 'Z';
    private static final char UNBREAKABLE_WALL = 'N';
    private static final char EMPTY = 'O';

    private int width;
    private int height;

    private TileObject[][] tileObjects;

    public Map() {
        map = this;

        this.width = 0;
        this.height = 0;

        // TODO: vygeneruj nejaku defaultnu mapu alebo nahodnu
    }

    public Map(String fileWithMap) {
        this();
        this.loadFromFile(fileWithMap);
    }

    public static Map getMap() {
        return map;
    }

    public TileObject[][] getTileObjects() {
        return this.tileObjects;
    }

    public void setTileObject(TileObject tileObject) {
        this.tileObjects[tileObject.getRow()][tileObject.getColumn()] = tileObject;
    }

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
