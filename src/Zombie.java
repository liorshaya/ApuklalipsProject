import java.awt.*;
import java.awt.image.BufferedImage;

public class Zombie{
    public static final int ZOMBIE_WIDTH = 64;
    public static final int ZOMBIE_HEIGHT = 64;
    public static final int HEALTH_BAR_WIDTH = 50;

    private double x;
    private double y;
    private int width;
    private int height;
    private int hitCounter;
    private int hitDamage;
    private int healthBarWidth;
    private int totalHealth;

    private BufferedImage[] walkZombieFrames;
    private int currentFrame = 0;
    private long lastFrameTime = 0;
    private final int frameDelay = 100;
    private double angle = 0;

    private BufferedImage[] deathZombieFrames;
    private int deathFrame = 0;
    private long timeOfDeath = 0;
    private final long deathDelay = 2000;
    private boolean toBeRemoved = false;

    private double dx;
    private double dy;
    private final double SPEED = 0.5;

    public Zombie(int x, int y , int width , int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitCounter = 10;
        this.totalHealth = 10;
        this.hitDamage = 10;
        this.healthBarWidth = 50;

        this.walkZombieFrames = ImageManager.loadZombieImage();
    }



    public void move(int playerX , int playerY){
        this.angle = Math.atan2(playerY + ZOMBIE_HEIGHT/2 - this.y, playerX + ZOMBIE_WIDTH/2 - this.x);
        this.dx = Math.cos(angle) * SPEED;
        this.dy = Math.sin(angle) * SPEED;

        if(deathZombieFrames == null){
            this.x += dx;
            this.y += dy;
        }
    }


    public boolean checkCollision(Rectangle playerRect){
        Rectangle zombieRect = new Rectangle((int) this.x, (int)this.y, ZOMBIE_WIDTH, ZOMBIE_HEIGHT);
        if (zombieRect.intersects(playerRect)){
            return true;
        }
        return false;
    }

    public int getHitDamage(){
        return this.hitDamage;
    }

    public void zombieHurt(){
        this.healthBarWidth -= (HEALTH_BAR_WIDTH/this.totalHealth);
    }


    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.RED);
        g.fillRect((int) this.x, (int) this.y, this.healthBarWidth, 5);


        int cx = (int) x + ZOMBIE_WIDTH / 2;
        int cy = (int) y + ZOMBIE_HEIGHT / 2;


        if (deathZombieFrames != null && deathFrame < deathZombieFrames.length) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastFrameTime > frameDelay && deathFrame < deathZombieFrames.length - 1) {
                deathFrame++;
                lastFrameTime = currentTime;
            }

            if (deathZombieFrames[deathFrame] != null) {
                g2d.drawImage(deathZombieFrames[deathFrame], (int) x, (int) y, ZOMBIE_WIDTH+32, ZOMBIE_HEIGHT+32, null);
            }

            if (deathFrame == deathZombieFrames.length - 1 && currentTime - timeOfDeath > deathDelay) {
                this.toBeRemoved = true;
                this.deathZombieFrames = null;
            }
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime > frameDelay) {
            currentFrame = (currentFrame + 1) % walkZombieFrames.length;
            lastFrameTime = currentTime;
        }

        if (walkZombieFrames[currentFrame] != null) {
            g2d.rotate(angle - Math.PI / 2, cx, cy);
            g2d.drawImage(walkZombieFrames[currentFrame], (int) x, (int) y, ZOMBIE_WIDTH, ZOMBIE_HEIGHT, null);
            g2d.rotate(-angle + Math.PI / 2, cx, cy);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect((int) x, (int) y, ZOMBIE_WIDTH, ZOMBIE_HEIGHT);
        }



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

    public boolean isToBeRemoved() {
        return toBeRemoved;
    }

    public void bulletHit(){
        this.hitCounter--;
        if(hitCounter == 0){
            this.deathZombieFrames = ImageManager.loadZombieDeathImage();
            this.deathFrame = 0;
            this.currentFrame = 0;
            this.lastFrameTime = System.currentTimeMillis();
            this.timeOfDeath = System.currentTimeMillis();
        }
    }
}
