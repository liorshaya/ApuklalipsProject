import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Character{
    public static final int PLAYER_WIDTH = 64;
    public static final int PLAYER_HEIGHT = 64;

    private int kills;
    private int bulletsLeft;
    private boolean isReloading;
    private int health;
    private int speed;
    private boolean isHitted;
    private boolean isShilded;
    private HudPanel hudPanel;

    private boolean isSpeedAbilityActive = false;
    private boolean isShiledAbilityActive = false;
    private boolean isDamageAbilityActive = false;

    private long speedAbilityStartTime = 0;
    private long shieldAbilityStartTime = 0;


    private BufferedImage[] deathPlayerFrames;
    private int currentFrame = 0;
    private long lastFrameTime = 0;
    private final int frameDelay = 100;
    private double angle = 0;
    private Point mouseLocation = new Point(0, 0);


    private BufferedImage[] walkPlayerFrames;
    private BufferedImage[] shieldPlayerFrames;
    private int deathFrame = 0;
    private long timeOfDeath = 0;
    private final long deathDelay = 2000;


    public Player(int x, int y, int width, int height, HudPanel hudPanel){
        super(x, y, width, height);
        this.kills = 0;
        this.bulletsLeft = 30;
        this.isReloading = false;
        this.health = 100;
        this.speed = 1;
        this.isShilded = false;
        this.isHitted = false;
        this.walkPlayerFrames = ImageManager.loadPlayerImage();
    }

    public void setHudPanel(HudPanel hudPanel) {
        this.hudPanel = hudPanel;
    }

    public void moveRight(){
        if (this.getX() + PLAYER_WIDTH + this.speed <= this.getWidth()){
            this.setX(this.getX() + this.speed);
        }
    }

    public void moveLeft(){
        if (this.getX() - this.speed >= 0){
            this.setX(this.getX() - this.speed);
        }
    }

    public void moveUp(){
        if (this.getY() - this.speed >= 0) {
            this.setY(this.getY() - this.speed);
        }
    }
    public void moveDown(){
        if (this.getY() + PLAYER_HEIGHT + this.speed <= this.getHeight()) {
            this.setY(this.getY() + this.speed);

        }
    }

    public int getPlayerCenterX() {
        return getX() + PLAYER_WIDTH / 2;
    }

    public int getPlayerCenterY() {
        return getY() + PLAYER_HEIGHT / 2;
    }

    public int getPlayerWidth(){
        return PLAYER_WIDTH;
    }

    public int getPlayerHeight(){
        return PLAYER_HEIGHT;
    }

    public void playerKill(){
        this.kills++;
    }

    public void shoot(){
        this.bulletsLeft--;

        if (this.bulletsLeft == 0){
            isReloading = true;
            reload();
        }
    }

    public boolean getIsReloading(){
        return this.isReloading;
    }

    public void reload(){
        new Thread(() -> {
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.bulletsLeft = 30;
            this.isReloading = false;
            this.hudPanel.shoot();
        }).start();
    }

    public void hurt(int damage){
        if (!this.isShilded){
            System.out.println("health:" + health);
            this.health -= damage;
            this.isHitted = true;
            hitDelay();
        }
        this.hudPanel.setHealth(this.health);
    }

    private void hitDelay(){
        new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.isHitted = false;
        }).start();
    }

    public int getBulletsLeft() {
        return bulletsLeft;
    }

    public boolean getIsHitted() {
        return isHitted;
    }

    public boolean isDead(){
        if(this.health <= 0){
            this.deathPlayerFrames = ImageManager.loadPlayerDeathImage();
        }
        return this.health <= 0;
    }

    public void setMouseLocation(Point p) {
        this.mouseLocation = p;
    }

    public int getKills(){
        return this.kills;
    }

    public void activateSpeedAbility(){
        if(!this.isSpeedAbilityActive){
            this.isSpeedAbilityActive = true;
            this.speed = 2;
            this.speedAbilityStartTime = System.currentTimeMillis();
        }

    }

    public void activateShieldAbility(){
        if (!this.isShiledAbilityActive) {
            shieldPlayerFrames = ImageManager.loadPlayerShieldImage();
            this.isShiledAbilityActive = true;
            this.isShilded = true;
            this.shieldAbilityStartTime = System.currentTimeMillis();
        }
    }

    public void activateHealAbility(){
        System.out.println("took boost old health:" + this.health);
        if (this.health <= 80){
            this.health += 20;
        } else {
            this.health = 100;
        }
        System.out.println("took boost new health:" + this.health);
        this.hudPanel.setHealth(this.health);
    }

    public void update() {
        long now = System.currentTimeMillis();

        if (isSpeedAbilityActive && now - speedAbilityStartTime >= 30000) {
            this.speed = 1;
            this.isSpeedAbilityActive = false;
        }

        if (isShiledAbilityActive && now - shieldAbilityStartTime >= 30000) {
            this.isShilded = false;
            this.isShiledAbilityActive = false;
        }
    }



    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        int playerCenterX = getX() + PLAYER_WIDTH / 2;
        int playerCenterY = getY() + PLAYER_HEIGHT / 2;

        double dx = mouseLocation.x - playerCenterX;
        double dy = mouseLocation.y - playerCenterY;

        angle = Math.atan2(dy, dx) + -Math.PI / 2;

        g.setColor(Color.RED);
        g.fillOval(mouseLocation.x - 3, mouseLocation.y - 3, 6, 6);

        if(deathPlayerFrames != null && deathFrame < deathPlayerFrames.length){
            long currentTime = System.currentTimeMillis();

            if(currentTime - lastFrameTime > frameDelay && deathFrame < deathPlayerFrames.length - 1){
                deathFrame++;
                lastFrameTime = currentTime;
            }

            if(deathPlayerFrames[deathFrame] != null){
                g2d.drawImage(deathPlayerFrames[deathFrame] , this.getX() , this.getY() , PLAYER_WIDTH , PLAYER_HEIGHT , null);
            }

        }

        long currentTime = System.currentTimeMillis();
        if(!isDead()){
            if (currentTime - lastFrameTime > frameDelay) {
                currentFrame = (currentFrame + 1) % walkPlayerFrames.length;
                lastFrameTime = currentTime;
            }

            BufferedImage frame;

            if (isShilded && shieldPlayerFrames != null) {
                frame = shieldPlayerFrames[currentFrame];
            } else {
                frame = walkPlayerFrames[currentFrame];
            }
            if (frame != null) {
                int cx = getX() + PLAYER_WIDTH / 2;
                int cy = getY() + PLAYER_HEIGHT / 2;

                g2d.rotate(angle, cx, cy);
                g2d.drawImage(frame, getX(), getY(), PLAYER_WIDTH, PLAYER_HEIGHT, null);
                g2d.rotate(-angle, cx, cy);
            } else {
                g.setColor(Color.BLUE);
                g.fillRect(getX(), getY(), PLAYER_WIDTH, PLAYER_HEIGHT);
            }
        }


    }


}
