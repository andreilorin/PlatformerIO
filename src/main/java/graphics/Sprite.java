package graphics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Sprite {

    private final static Logger logger = LogManager.getLogger(Sprite.class);

    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);

    public Sprite(int size, int x, int y, SpriteSheet sheet){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        loadSprite();
    }

    private void loadSprite(){
        logger.info("Loading Sprite");
        for(int y = 0; y < SIZE; y++){
            for(int x = 0; x < SIZE; x++){
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }

}
