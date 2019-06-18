import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Floor {
    ArrayList<ArrayList<BufferedImage>> floor = new ArrayList<>();
    BufferedImageLoader loader;

    public Floor() {
        loader = new BufferedImageLoader();
        Random rand = new Random();
        int randInt = rand.nextInt(9)+1;
        for(int x = 0; x < 63; x++)
        {
            ArrayList<BufferedImage> temp = new ArrayList<>();
            for(int y = 0; y < 35; y++)
            {
                temp.add(loader.loadImage("/tiles/floor/floor_"+randInt+".png"));
                randInt = rand.nextInt(9)+1;
            }
            floor.add(temp);
        }
    }

    public void render(Graphics g)
    {
        for (int xx = 0; xx < 63; xx++) {
            for (int yy = 0; yy < 35; yy++) {
                g.drawImage(floor.get(xx).get(yy), xx*32, yy*32, 32, 32, null);
            }
        }
    }
}

