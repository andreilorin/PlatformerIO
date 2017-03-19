package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpriteSheet {

    private final static Logger logger = LogManager.getLogger(SpriteSheet.class);

    private String path;
    public final int SIZE;

    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("C:\\Users\\Lorin\\IdeaProjects\\PlatformerIO\\src\\main\\resources\\textures\\spritesheet.png", 256);

    public SpriteSheet(String path, int size){
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * size];
        loadImage(path);
    }

    private void loadImage(String path){
        logger.info("Loading spritesheet: " + path);
        try {
            BufferedImage image = ImageIO.read(new File(path));

            int width = image.getWidth();
            int height = image.getHeight();

            image.getRGB(0, 0, width, height, pixels, 0, width);

        } catch (IOException e) {
            logger.error("Can't read input File at: " + path);
        }
    }
}
