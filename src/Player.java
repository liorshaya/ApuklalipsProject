import java.awt.*;

public class Player{
    public static final int PLAYER_WIDTH = 100;
    public static final int PLAYER_HEIGHT = 100;

    private int x;
    private int y;
    private int width;
    private int height;

    public Player(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void moveRight(){
        if (this.x + PLAYER_WIDTH != width){
            this.x++;
        }
    }

    public void moveLeft(){
        if (this.x != 0){
            this.x--;
        }
    }

    public void moveUp(){
        if (this.y != 0) {
            this.y--;
        }
    }
    public void moveDown(){
        if (this.y + PLAYER_HEIGHT != height) {
            this.y++;
        }
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getPlayerCenterX() {
        return x + PLAYER_WIDTH / 2;
    }

    public int getPlayerCenterY() {
        return y + PLAYER_HEIGHT / 2;
    }

    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(this.x, this.y, PLAYER_WIDTH ,PLAYER_HEIGHT);
    }


}
