import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public int frames;
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera camera;
    Level level;
    private BufferedImage levelImage;
    private Floor floor;
    public BufferedImageLoader loader;
    Random rand;


    public Game() {
        floor = new Floor();
        rand = new Random();
        new Window(563, 1000, "Wizard Game", this);
        start();
        camera = new Camera(0, 0);
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler, camera));
        loader = new BufferedImageLoader();
        levelImage = loader.loadImage("/WizardWorld1.png");
        level = new Level(handler, loader);
        level.loadLevel(levelImage);

    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    ;

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    ;

    public static void main(String[] args) {
        new Game();
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                //updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                //updates = 0;
            }
        }
        stop();
    }

    public void tick() {
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                camera.tick(handler.objects.get(i));
            }
        }
        handler.tick();

    }


    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        //////////////////////
        g2d.translate(-camera.getX(), -camera.getY()); //camera
        floor.render(g);
        handler.render(g); //render all the items in the game
        g2d.translate(camera.getX(), camera.getY());
        ///////////////////////
        g.dispose();
        bs.show();
    }

}
