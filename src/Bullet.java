import java.awt.*;

public class Bullet {
    public static final int BULLET_WIDTH = 12;
    public static final int BULLET_HEIGHT = 12;

    private double x;
    private double y;
    private double dx;
    private double dy;
    private final double SPEED = 7;
//    private final int size = 8;

    public Bullet(int x, int y, int targetX, int targetY){
        this.x = x;
        this.y = y;

        double angle = Math.atan2(targetY - y, targetX - x);
        this.dx = Math.cos(angle) * SPEED;
        this.dy = Math.sin(angle) * SPEED;
    }

    public void update(){
        x += dx;
        y += dy;
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval((int) x , (int) y, BULLET_WIDTH, BULLET_HEIGHT);
    }

    public boolean isBulletOutOfScreen(int width, int height){
        return x < 0 || x > width || y < 0 || y > height;
    }

}
