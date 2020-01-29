import java.awt.image.BufferedImage;

public class Level {

    Handler handler;
    BufferedImageLoader loader;
    int w;
    int h;
    BufferedImage image;
    int[][] map;

    public Level(Handler handler, BufferedImageLoader loader)
    {
        this.handler = handler;
        this.loader = loader;
    }


    public  void loadLevel(BufferedImage image) {
        this.image = image;
        w = image.getWidth();
        h = image.getHeight();
        map = new int[w][h];

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255) {
                    handler.addObject(new Block(xx * 32, yy * 32, ID.Block, loader));
                    map[xx][yy] = 1;
                }
                if (blue == 255) {
                    handler.addObject(new Wizard(xx * 32, yy * 32, ID.Player, handler));
                }
                if (green == 255)
                {
                    handler.addObject(new Slime(xx *32, yy*32, ID.Enemy, handler));
                }

            }
        }
    }


}
