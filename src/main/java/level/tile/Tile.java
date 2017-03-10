package level.tile;

import graphics.Renderer;
import graphics.Sprite;

public class Tile {
    public int x, y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);

    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Renderer renderer){

    }

    public boolean solid(){
        return false;
    }
}
