package level;

import graphics.Renderer;
import level.tile.Tile;

public class Level {

    protected int width, height;
    protected int[] tiles;

    public Level(int width, int height){
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public Level(String path){
        loadLevel(path);
    }

    protected void generateLevel(){

    }

    private void loadLevel(String path){

    }

    public void update(){

    }

    private void time(){

    }

    public void render(int xScroll, int yScroll, Renderer renderer){
        renderer.setOffset(xScroll, yScroll);
        int x0CornerPin = xScroll >> 4;
        int x1CornerPin = (xScroll + renderer.width) >> 4;
        int y0CornerPin = yScroll >> 4;
        int y1CornerPin = (yScroll + renderer.height) >> 4;

        for (int y = y0CornerPin; y < y1CornerPin; y++) {
            for (int x = x0CornerPin; x < x1CornerPin; x++) {
                getTile(x, y).render(x, y, renderer);
            }
        }
    }

    public Tile getTile(int x, int y){
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        if(tiles[x + y * width] == 0) return Tile.grass;
        return Tile.voidTile;
    }
}
