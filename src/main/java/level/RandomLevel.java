package level;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class RandomLevel extends Level{

    private static final Logger logger = LogManager.getLogger(RandomLevel.class);

    private final Random random = new Random();

    public RandomLevel(int width, int height){
        super(width, height);
    }

    protected void generateLevel(){
        logger.info("Generating random level");
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles[x + y * width] = random.nextInt(4);
            }
        }
    }
}
