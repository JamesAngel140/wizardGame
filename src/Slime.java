import java.awt.*;

public class Slime extends GameObject  {

    public Animation idle;
    public Animation walk;

    public Slime(int x, int y, ID id) {
        super(x, y, id);
        this.idle = new Animation(5,"/enemies/slime/slime_idle_spritesheet.png", 6, 16, 16);
        this.walk = new Animation(5, "/enemies/slime/slime_run_spritesheeet.png", 6, 16,16);
    }

    @Override
    public void tick() {
        for

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }


}
