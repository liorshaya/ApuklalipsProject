import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Random;

public class MainScene extends JPanel {
    private Player player;
    private int width;
    private int height;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private Bullet[] bullets = new Bullet[100];

    private Zombie[] zombies = new Zombie[100];


    public MainScene(int x, int y, int width, int height){
        this.setBounds(x, y, width, height);
        this.setBackground(Color.BLUE);
        this.width = width;
        this.height = height;

        this.player = new Player(width/2 - 50,height/2 - 50, width, height);

        this.zombieSpawner();

        this.setFocusable(true);
        this.requestFocus();

        Timer timer = new Timer(100, 100, 100,100);
        this.add(timer);

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


    public void zombieSpawner(){
        new Thread(() -> {
            while (true){
                for (int i = 0; i < this.zombies.length; i++) {
                    if (this.zombies[i] == null){
                        int[] randomLocations = randomSpawn();
                        Zombie zombie = new Zombie(randomLocations[0], randomLocations[1], this.width, this.width);
                        this.zombies[i] = zombie;
                        break;
                    }
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public int[] randomSpawn(){
        Random rnd = new Random();
        int pickPlace = rnd.nextInt(0,4);
        int[] randoms = new int[2];
        System.out.println("pickPlace" + pickPlace);
        switch (pickPlace){
            case 0:
                randoms[0] = rnd.nextInt(0,this.getWidth()+1);
                randoms[1] = -50;
                break;
            case 1:
                randoms[1] = rnd.nextInt(0,this.getHeight()+1);
                randoms[0] = getWidth()+50;
                break;
            case 2:
                randoms[0] = rnd.nextInt(0,this.getWidth()+1);
                randoms[1] = this.getHeight()+50;
                break;
            case 3:
                randoms[1] = rnd.nextInt(0,this.getHeight()+1);
                randoms[0] = -50;
                break;
        }
        return randoms;
    }


    public void update(){
        if (upPressed) player.moveUp();
        if (downPressed) player.moveDown();
        if (leftPressed) player.moveLeft();
        if (rightPressed) player.moveRight();

        for (int i = 0; i < this.bullets.length; i++) {
            if (this.bullets[i] != null){

                for (int j = 0; j < this.zombies.length; j++) {
                    if (zombies[j] != null){
                        if (this.bullets[i].checkCollision(new Rectangle((int) zombies[j].getX(), (int) zombies[j].getY(), zombies[j].getZombieWidth(), zombies[j].getZombieHeight()))){
                            zombies[j].bulletHit();
                            System.out.println("shoot!");
                        }
                    }
                }

                this.bullets[i].update();

                if (this.bullets[i].isBulletOutOfScreen(width, height) || this.bullets[i].getIsHit()){
                    this.bullets[i] = null;
                }
            }
        }

        for (int i = 0; i < this.zombies.length; i++) {
            if (this.zombies[i] != null){
                this.zombies[i].move(this.player.getX(), this.player.getY());
                if (this.zombies[i].checkCollision(new Rectangle(this.player.getX(), this.player.getY() ,this.player.getPlayerWidth(), this.player.getPlayerHeight()))){
                    System.out.println("HIT!");
                }

                if (zombies[i].getHitCounter() == 0){
                    zombies[i] = null;
                    this.player.playerKill();
                }
            }
        }

    }
}
