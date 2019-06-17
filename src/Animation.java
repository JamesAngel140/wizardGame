import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

    BufferedImage ImageSheet;
    ArrayList<BufferedImage> Images = new ArrayList<BufferedImage>();
    BufferedImage currentImg;


    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;

    public Animation(int speed, String path, int numOfImages, int width, int height) {
        this.speed = speed;
        BufferedImageLoader loader = new BufferedImageLoader();
        ImageSheet = loader.loadImage(path);
        for (int i = 0; i < numOfImages; i++) {
            Images.add(ImageSheet.getSubimage(i * width, 0, width, height));
        }
        frames = numOfImages;

    }

    public void runAnimation() {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame();
        }
    }

    public void nextFrame() {
        for (int i = 0; i < frames; i++) {
            if (count == i) {
                currentImg = Images.get(i);
            }
        }

        count++;

        if (count > frames) {
            count = 0;
        }
    }

    public void drawAnimation(Graphics g, int x, int y)
    {
        g.drawImage(currentImg, x, y, null);
    }
}
