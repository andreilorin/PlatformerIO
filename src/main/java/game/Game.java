package game;



import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import graphics.*;
import graphics.Renderer;

public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;

    public static int width = 300;
    public static int height = 300 / 16 * 9;
    public static int scale = 3;

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
        while (running) {
            update();
            render();
        }
    }

    //draw game logic at 60fps
    public void update(){

    }

    //draw images as fast as possible
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
        game.frame.setTitle("Game");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.start();
    }

}
