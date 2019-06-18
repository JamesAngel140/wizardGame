import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Block extends GameObject {

    BufferedImage wall;
    BufferedImageLoader loader;


    public Block(int x, int y, ID id, BufferedImageLoader loader)
    {
        super(x, y, id);
        this.loader = loader;
        Random rand = new Random();
        int randInt = rand.nextInt(2)+1;
        wall = loader.loadImage("/tiles/wall/wall_"+randInt+".png");

    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(wall, x, y,32,32, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
