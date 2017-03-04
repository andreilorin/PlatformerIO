package game;



import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import graphics.Renderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(Game.class);


    public static int width = 300;
    public static int height = 300 / 16 * 9;
    public static int scale = 3;

    private static String title = "PlatformerIO";

    private Thread thread;

    private boolean running = false;

    private Renderer screen;

    //create image
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    //get access to it's pixels
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    private JFrame frame;

    //Constructor
    public Game(){

        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);

        screen = new Renderer(width, height);

        frame = new JFrame();

    }

    //Methods
    public synchronized void start(){
        running = true;
        thread = new Thread(this, "Game Thread");
        thread.start();
    }

    public synchronized void stop(){
        running = false;
        try{
            thread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            //draw game logic at 60fps
            while (delta >= 1){
                update();
                updates++;
                delta--;
            }

            //draw images as fast as possible
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " UPS, " + frames + " fps");
                frame.setTitle(title + "    " + updates + " UPS, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }


    public void update(){

    }

    public void render(){
        BufferStrategy bufferStrategy = getBufferStrategy();
        if(bufferStrategy == null){
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        screen.render();

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

//		graphics.setColor(Color.BLACK);
//		graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);


        graphics.dispose();
        bufferStrategy.show();

    }



    public static void main(String[] args){
        Game game = new Game();

        game.frame.setResizable(false);
        game.frame.setTitle(Game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.start();
    }

}
