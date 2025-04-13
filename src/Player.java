import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Character{
    public static final int PLAYER_WIDTH = 64;
    public static final int PLAYER_HEIGHT = 64;

    private int kills;

    private BufferedImage[] walkPlayerFrames;
    private int currentFrame = 0;
    private long lastFrameTime = 0;
    private final int frameDelay = 100;
    private double angle = 0;
    private Point mouseLocation = new Point(0, 0);


    public Player(int x, int y, int width, int height, HudPanel hudPanel){
        super(x, y, width, height);
        this.kills = 0;
        this.walkPlayerFrames = ImageManager.loadPlayerImage();

    }




    public void moveRight(){
        if (this.getX() + PLAYER_WIDTH != this.getWidth()){
            this.setX(this.getX()+1);;
        }
    }

    public void moveLeft(){
        if (this.getX() != 0){
            this.setX(this.getX()-1);
        }
    }

    public void moveUp(){
        if (this.getY() != 0) {
            this.setY(this.getY()-1);
        }
    }
    public void moveDown(){
        if (this.getY() + PLAYER_HEIGHT != this.getHeight()) {
            this.setY(this.getY()+1);

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

    public void setMouseLocation(Point p) {
        this.mouseLocation = p;
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

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime > frameDelay) {
            currentFrame = (currentFrame + 1) % walkPlayerFrames.length;
            lastFrameTime = currentTime;
        }

        BufferedImage frame = walkPlayerFrames[currentFrame];
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
