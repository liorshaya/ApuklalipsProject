import java.awt.*;
import java.util.Random;

public class Zombie{
    public static final int ZOMBIE_WIDTH = 50;
    public static final int ZOMBIE_HEIGHT = 50;

    private double x;
    private double y;
    private int width;
    private int height;
    private int hitCounter;

    private double dx;
    private double dy;
    private final double SPEED = 0.5;

    public Zombie(int x, int y , int width , int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitCounter = 3;
    }



    public void move(int playerX , int playerY){
        double angle = Math.atan2(playerY + ZOMBIE_HEIGHT/2 - this.y, playerX + ZOMBIE_WIDTH/2 - this.x);
        this.dx = Math.cos(angle) * SPEED;
        this.dy = Math.sin(angle) * SPEED;

        this.x += dx;
        this.y += dy;
    }


    public boolean checkCollision(Rectangle playerRect){
        Rectangle zombieRect = new Rectangle((int) this.x, (int)this.y, ZOMBIE_WIDTH, ZOMBIE_HEIGHT);
        if (zombieRect.intersects(playerRect)){
            return true;
        }
        return false;
    }


    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int) this.x, (int) this.y, ZOMBIE_WIDTH ,ZOMBIE_HEIGHT);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getZombieWidth() {
        return ZOMBIE_WIDTH;
    }

    public int getZombieHeight() {
        return ZOMBIE_HEIGHT;
    }

    public int getHitCounter() {
        return hitCounter;
    }

    public void bulletHit(){
        this.hitCounter--;
    }
}
