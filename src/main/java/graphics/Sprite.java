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
    public static Sprite voidSprite = new Sprite(16, 0x15ff10);

    public Sprite(int size, int x, int y, SpriteSheet sheet){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        loadSprite();
    }

    public Sprite(int size, int color){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color){
        for (int i = 0; i < SIZE * SIZE; i++) {
            pixels[i] = color;
        }
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
