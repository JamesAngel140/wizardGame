import java.awt.*;

public class Bullet extends GameObject{

    public Handler handler;
    public float velocity = 15;


    public Bullet(int x, int y, ID id, Handler handler, int mx, int my) {
        super(x, y, id);
        this.handler = handler;

        float dirX = mx-x;
        float dirY = my-y;
        float dirLength =(float) Math.sqrt(dirX*dirX + dirY*dirY);
        dirX = dirX/dirLength;
        dirY = dirY/dirLength;

        velX = dirX*velocity;
        velY = dirY*velocity;
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        for(int i = 0; i<handler.objects.size(); i++)
        {
            GameObject temp = handler.objects.get(i);

            if(temp.getId()== ID.Block)
            {
                if(getBounds().intersects(temp.getBounds()))
                {
                    handler.removeObject(this);
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x,y,8,8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,8,8);
    }
}
