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
        this.x++;
    }

    public void moveLeft(){
        this.x--;
    }

    public void moveUp(){
        this.y--;
    }
    public void moveDown(){
        this.y++;
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

    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(this.x, this.y, PLAYER_WIDTH ,PLAYER_HEIGHT);
    }


}
