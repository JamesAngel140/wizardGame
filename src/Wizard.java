import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Wizard extends GameObject {

    public Animation idle;
    public Animation walk;

    Handler handler;

    public Wizard(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.idle = new Animation(10,"/knight_idle_spritesheet.png", 6, 16, 16);
        this.walk = new Animation(10, "/knight_run_spritesheet.png", 6, 16,16);
    }

    public void tick() {
        x +=velX;
        y +=velY;

        collision();

        //movement
        if(handler.isUp()) velY = -5;
        else if(!handler.isDown()) velY = 0;

        if(handler.isDown()) velY = 5;
        else if(!handler.isUp()) velY = 0;


        if(handler.isRight()) velX = 5;
        else if(!handler.isLeft()) velX = 0;

        if(handler.isLeft()) velX = -5;
        else if(!handler.isRight()) velX = 0;
    }

    private void collision()
    {
        for(int i = 0; i <handler.objects.size(); i++)
        {
            GameObject temp = handler.objects.get(i);

            if(temp.getId()== ID.Block)
            {
                if(getBounds().intersects(temp.getBounds())){
                    x += velX *-1;
                    y += velY *-1;
                }
            }
        }
    }

    public void render(Graphics g) {
        if(handler.isDown() || handler.isLeft() || handler.isRight() || handler.isUp())
        {
            walk.drawAnimation(g, x,y);
        }
        else{
            idle.drawAnimation(g, x, y);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,16,16);
    }
}
