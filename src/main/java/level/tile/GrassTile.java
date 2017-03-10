package level.tile;

import graphics.Renderer;
import graphics.Sprite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GrassTile extends Tile{

    private final static Logger logger = LogManager.getLogger(GrassTile.class);

    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Renderer renderer){
        logger.info("Generating grass tile");
        renderer.renderTile(x, y, this);
    }


}
