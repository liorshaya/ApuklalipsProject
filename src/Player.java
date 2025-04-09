import java.awt.*;

public class Player extends Character{
    public static final int PLAYER_WIDTH = 100;
    public static final int PLAYER_HEIGHT = 100;

    private int kills;

    public Player(int x, int y, int width, int height){
        super(x, y, width, height);
        this.kills = 0;
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

    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(this.getX(), this.getY(), PLAYER_WIDTH ,PLAYER_HEIGHT);
    }


}
