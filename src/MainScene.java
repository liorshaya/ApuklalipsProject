import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class MainScene extends JPanel {
    private Player player;
    private int width;
    private int height;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private Bullet[] bullets = new Bullet[100];




    public MainScene(int x, int y, int width, int height){
        this.setBounds(x, y, width, height);
        this.setBackground(Color.BLUE);
        this.width = width;
        this.height = height;

        this.player = new Player(width/2 - 50,height/2 - 50, width, height);

        this.setFocusable(true);
        this.requestFocus();


        MouseListenerEvents mouseListener = new MouseListenerEvents(this.player, this.bullets);
        this.addMouseListener(mouseListener);

        this.addKeyListener(new MovmentListener(this,this.player));
        this.mainGameLoop();

    }

    public void mainGameLoop(){
        new Thread(() -> {
            while (true){
                try {
                    update();
                    repaint();
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.player.paint(g);
        for (int i = 0; i < this.zombies.length; i++) {
            if (this.zombies[i] != null){
                zombies[i].paint(g);
            }
        }

        for (int i = 0; i < bullets.length; i++) {
            if (bullets[i] != null) {
                bullets[i].paint(g);
            }
        }
    }


    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }


    public void update(){
        if (upPressed) player.moveUp();
        if (downPressed) player.moveDown();
        if (leftPressed) player.moveLeft();
        if (rightPressed) player.moveRight();

        for (int i = 0; i < this.bullets.length; i++) {
            if (this.bullets[i] != null){
                this.bullets[i].update();
                if (this.bullets[i].isBulletOutOfScreen(width, height)){
                    this.bullets[i] = null;
                }
            }
        }
    }
}
