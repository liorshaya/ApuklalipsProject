import java.awt.*;
import java.awt.image.BufferedImage;

public class Ability {
    public static final int TYPE_HEALTH = 0;
    public static final int TYPE_SPEED = 1;
    public static final int TYPE_SHIELD = 2;
    public static final int TYPE_DAMAGE = 3;


    private int x;
    private int y;
    private int width;
    private int height;
    private int type;
    private boolean isActive;
    private long spawnTime;
    private Player player;
    private BufferedImage abilityImg;
    private boolean istaken = false;

    public Ability(int x, int y, int type, Player player){
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 40;
        this.type = type;
        this.isActive = true;
        this.spawnTime = System.currentTimeMillis();
        this.player = player;

        if (type == TYPE_HEALTH){
            ImageManager.loadHealthAbility();
            this.abilityImg = ImageManager.getHealthAbility();
        }
        if (type == TYPE_SPEED){
            ImageManager.loadSpeedAbility();
            this.abilityImg = ImageManager.getSpeedAbility();
        }
        if (type == TYPE_SHIELD){
            ImageManager.loadShieldAbility();
            this.abilityImg = ImageManager.getShieldAbility();
        }
        if (type == TYPE_DAMAGE){
            ImageManager.loadDamageAbility();
            this.abilityImg = ImageManager.getDamageAbility();
        }
    }

    public void activate(){
        if (!this.istaken){
            if (this.type == TYPE_HEALTH) {
                player.activateHealAbility();
            }
            if (this.type == TYPE_SHIELD) {
                player.activateShieldAbility();
            }
            if (this.type == TYPE_SPEED) {
                player.activateSpeedAbility();
            }
           if(this.type == TYPE_DAMAGE){
               player.activateDamageAbility();
           }

            this.istaken = true;
        }
    }

    public boolean checkCollision(Rectangle playerRect){
        Rectangle abilityRect = new Rectangle((int) this.x, (int)this.y, this.width, this.height);
        if (abilityRect.intersects(playerRect)){
            return true;
        }

        return false;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//        this.abilityImg = ImageManager.getHealthAbility();
        g2d.drawImage(this.abilityImg, this.x, this.y, this.width, this.height, null);

    }
}
