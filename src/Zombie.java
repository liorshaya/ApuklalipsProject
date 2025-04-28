import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Zombie{
    private double x;
    private double y;
    private int width;
    private int height;

    private int hitCounter;
    private int hitDamage;
    private int totalHealth;
    private int healthBarWidth;

    private int zombieWidth;
    private int zombieHeight;
    private int staticHealthBarWidth;

    private BufferedImage[] walkZombieFrames;
    private int currentFrame = 0;
    private long lastFrameTime = 0;

    private final int frameDelay = 100;
    private double angle = 0;

    private BufferedImage[] deathZombieFrames;
    private BufferedImage[] tempDeathZombieFrames;
    private int deathFrame = 0;
    private long timeOfDeath = 0;
    private final long deathDelay = 2000;
    private boolean toBeRemoved = false;
    private boolean isCountedAsKill;

    private double dx;
    private double dy;
    private double speed;

    public Zombie(int x, int y , int width , int height,
                      int hitCounter, int totalHealth, int hitDamage,
                      int healthBarWidth, double speed,
                      int zombieWidth, int zombieHeight, int staticHealthBarWidth,
                      BufferedImage[] walkFrames, BufferedImage[] deathFrames){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.hitCounter = hitCounter;
        this.totalHealth = totalHealth;
        this.hitDamage = hitDamage;
        this.healthBarWidth = healthBarWidth;
        this.speed = speed;

        this.zombieWidth = zombieWidth;
        this.zombieHeight = zombieHeight;
        this.staticHealthBarWidth = staticHealthBarWidth;
        this.isCountedAsKill = false;

        this.walkZombieFrames = walkFrames;
        this.tempDeathZombieFrames = deathFrames;
    }



    public void move(int playerX , int playerY){
        this.angle = Math.atan2(playerY + zombieHeight / 2 - this.y, playerX + zombieWidth / 2 - this.x);
        this.dx = Math.cos(angle) * speed;
        this.dy = Math.sin(angle) * speed;

        if(deathZombieFrames == null){
            this.x += dx;
            this.y += dy;
        }
    }


    public boolean checkCollision(Rectangle playerRect){
        if (this.deathZombieFrames == null){
            Rectangle zombieRect = new Rectangle((int) this.x, (int)this.y, zombieWidth, zombieHeight);
            if (zombieRect.intersects(playerRect)){
                return true;
            }
        }
        return false;
    }

    public int getHitDamage(){
        return this.hitDamage;
    }

    public void zombieHurt(int damage){
        this.healthBarWidth -= (staticHealthBarWidth / this.totalHealth * damage);
    }

    public boolean isDead(){
        return this.hitCounter <= 0;
    }

    public boolean isCountedAsKill() {
        return isCountedAsKill;
    }

    public void setCountedAsKill(boolean countedAsKill) {
        isCountedAsKill = countedAsKill;
    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.RED);
        g.fillRect((int) this.x, (int) this.y, this.healthBarWidth, 5);


        int cx = (int) x + zombieWidth / 2;
        int cy = (int) y + zombieHeight / 2;


        if (deathZombieFrames != null && deathFrame < deathZombieFrames.length) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastFrameTime > frameDelay && deathFrame < deathZombieFrames.length - 1) {
                deathFrame++;
                lastFrameTime = currentTime;
            }

            if (deathZombieFrames[deathFrame] != null) {
                g2d.drawImage(deathZombieFrames[deathFrame], (int) x, (int) y, zombieWidth +32, zombieHeight +32, null);
            }

            if (deathFrame == deathZombieFrames.length - 1 && currentTime - timeOfDeath > deathDelay) {
                this.toBeRemoved = true;
//                this.deathZombieFrames = null;
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
            g2d.drawImage(walkZombieFrames[currentFrame], (int) x, (int) y, zombieWidth, zombieHeight, null);
            g2d.rotate(-angle + Math.PI / 2, cx, cy);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect((int) x, (int) y, zombieWidth, zombieHeight);
        }



    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getZombieWidth() {
        return zombieWidth;
    }

    public int getZombieHeight() {
        return zombieHeight;
    }

    public int getHitCounter() {
        return hitCounter;
    }

    public boolean isToBeRemoved() {
        return toBeRemoved;
    }

    public void bulletHit(int damage){
        this.hitCounter -= damage;
        if(hitCounter <= 0){
            this.deathZombieFrames = this.tempDeathZombieFrames;
            this.deathFrame = 0;
            this.currentFrame = 0;
            this.lastFrameTime = System.currentTimeMillis();
            this.timeOfDeath = System.currentTimeMillis();
        }
    }
}
