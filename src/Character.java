import java.awt.*;

public abstract class Character {
    private int x;
    private int y;
    private int width;
    private int height;

    public Character(int x, int y , int width , int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void paint(Graphics g);
}
