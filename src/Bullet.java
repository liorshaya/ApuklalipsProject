import java.awt.*;

public class Bullet {
    public static final int BULLET_WIDTH = 12;
    public static final int BULLET_HEIGHT = 12;

    private double x;
    private double y;
    private double dx;
    private double dy;
    private final double SPEED = 20;
    private boolean isHit;


    public Bullet(int x, int y, int targetX, int targetY){
        this.x = x;
        this.y = y;
        this.isHit = false;

        double angle = Math.atan2(targetY - y, targetX - x);
        this.dx = Math.cos(angle) * SPEED;
        this.dy = Math.sin(angle) * SPEED;
    }

    public void update(){
        this.x += dx;
        this.y += dy;
    }

    public boolean checkCollision(Rectangle zombieRect){
        Rectangle bulletRect = new Rectangle((int) this.x, (int)this.y, BULLET_WIDTH, BULLET_HEIGHT);
        if (bulletRect.intersects(zombieRect)){
            this.isHit = true;
            return true;
        }
        return false;
    }

    public void paint(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval((int) x , (int) y, BULLET_WIDTH, BULLET_HEIGHT);
    }

    public boolean isBulletOutOfScreen(int width, int height){
        return x < 0 || x > width || y < 0 || y > height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getbulletHight(){
        return BULLET_HEIGHT;
    }


    public int getBulletWidth(){
        return BULLET_WIDTH;
    }

    public boolean getIsHit() {
        return isHit;
    }
}
