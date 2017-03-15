package level.tile;

import graphics.Renderer;
import graphics.Sprite;

public class VoidTile extends Tile {

    public VoidTile(Sprite voidSprite) {
        super(voidSprite);
    }

    public void render(int x, int y, Renderer renderer){
        renderer.renderTile(x, y, this);
    }
}
