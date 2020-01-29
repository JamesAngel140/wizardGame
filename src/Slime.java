import java.awt.*;
import java.util.Random;

public class Slime extends GameObject {

    public Animation walk;
    public Random rand;
    Handler handler;

    int health;

    public Slime(int x, int y, ID id, Handler handler) {

        super(x, y, id);
        health = 30;
        rand = new Random();
        this.handler = handler;
        this.walk = new Animation(5, "/enemies/slime/slime_run_spritesheeet.png", 6, 16, 16);
    }

    @Override
    public void tick() {


        move();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);
            if (temp.getId() == ID.Bullet && getBounds().intersects(temp.getBounds())) {
                handler.removeObject(temp);
                health = health - 10;
            }
        }
        if (health < 0) {
            handler.removeObject(this);
        }
        walk.runAnimation();
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g) {
        if (velX > 0) {
            walk.drawAnimationRight(g, x, y);
        } else {
            walk.drawAnimationLeft(g, x, y);
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }



    public void move() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);

            if (temp.getId() == ID.Player) {
                int PX = temp.getX();
                int PY = temp.getY();
                if(this.x < PX )
                {
                    velX = 2;
                }
                else{ velX = -2;}
                if(this.y < PY )
                {
                    velY = 2;
                }
                else{ velY = -2;}

                int xDistance = this.x - PX;
                int yDistance = this.y - PY;
                for (int y = 0; y < handler.objects.size(); y++) {
                    temp = handler.objects.get(y);
                    if (temp.getId() == ID.Block) {

                        if (this.y > temp.y && this.y < temp.y + 32) {
                            if (temp.x > x && temp.x < x + xDistance) {
                                System.out.println("velx set to zero");
                                velX = 0;
                            }
                        }
                        if (this.x > temp.x && this.x < temp.x + 32) {
                            if (temp.y > y && temp.y < y + yDistance) {
                                System.out.println("vely set to zero");
                                velY = 0;
                            }
                        }
                    }
                }
            }
        }

    }
}
