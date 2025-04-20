import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MainScene extends JPanel {
    private Player player;
    private HudPanel hudPanel;
    private KillsHud killsHud;
    private endGameText endGameText;
    private int width;
    private int height;

    private boolean isGameLive;
    private boolean isEndSequenceStarted = false;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean isPaused = false;

    private Bullet[] bullets = new Bullet[100];

    private Zombie[] zombies = new Zombie[100];

    private Timer timer;

    private PausePanel pausePanel;


    public MainScene(int x, int y, int width, int height){
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        ImageManager.loadBackground();

        this.endGameText = new endGameText(width/2-212 , height/2-100 , 424 , 80);
        this.add(endGameText);

        this.width = width;
        this.height = height;
        this.isGameLive = true;

        this.player = new Player(width/2 - 50,height/2 - 50, width, height , hudPanel);

        this.killsHud = new KillsHud(width-150 , 0, 140, 65, this.player);
        this.add(killsHud);

        this.zombieSpawner();

        this.setFocusable(true);
        this.requestFocus();

        this.timer = new Timer(this.width/2 - 60, 0, 300,100);
        this.add(this.timer);

        this.hudPanel = new HudPanel(this.player, 0, this.height - 80, 330, 80);
        this.add(hudPanel);

        this.player.setHudPanel(this.hudPanel);

        MouseListenerEvents mouseListener = new MouseListenerEvents(this.player, this.bullets, this.hudPanel);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        this.addKeyListener(new MovmentListener(this,this.player));

        this.pausePanel = new PausePanel(width / 2 - 200, height / 2 - 100, 400, 200, this);

        this.mainGameLoop();

    }

    public void mainGameLoop(){
        new Thread(() -> {
            while (isGameLive && !isPaused){
                try {
                    update();
                    repaint();
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.add(this.pausePanel);
            repaint();

        }).start();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        BufferedImage background = ImageManager.getBackground();
        if (background != null) {
            g.drawImage(background, 0, 0, null);
        }

        this.player.paint(g);
        for (int i = 0; i < this.zombies.length; i++) {
            if (this.zombies[i] != null && !zombies[i].isToBeRemoved()){
                zombies[i].paint(g);
            }
            else{
                zombies[i] = null;
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

    public void setPaused(boolean paused) {
        isPaused = paused;
        this.timer.setPaused(paused);
    }


    public void zombieSpawner(){
        Random rnd = new Random();

            new Thread(() -> {
                while (true){
                    for (int i = 0; i < this.zombies.length; i++) {
                        if (this.zombies[i] == null){
                            int[] randomLocations = randomSpawn();
                            ZombieLvl1 zombie = new ZombieLvl1(randomLocations[0], randomLocations[1], this.width, this.width);
                            this.zombies[i] = zombie;
                            break;
                        }
                    }
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();

            new Thread(() -> {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                while (true){
                    for (int i = 0; i < this.zombies.length; i++) {
                        if (this.zombies[i] == null){
                            int[] randomLocations = randomSpawn();
                            ZombieLvl1Boss zombieBoss = new ZombieLvl1Boss(randomLocations[0], randomLocations[1], this.width, this.width);
                            this.zombies[i] = zombieBoss;
                            break;
                        }
                    }
                    try {
                        Thread.sleep(rnd.nextInt(10000,20000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();

        new Thread(() -> {
            try {
                Thread.sleep(25000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (true){
                for (int i = 0; i < this.zombies.length; i++) {
                    if (this.zombies[i] == null){
                        int[] randomLocations = randomSpawn();
                        ZombieLvl2 zombieLvl2 = new ZombieLvl2(randomLocations[0], randomLocations[1], this.width, this.width);
                        this.zombies[i] = zombieLvl2;
                        break;
                    }
                }
                try {
                    Thread.sleep(rnd.nextInt(7000,20000));
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
        if (!this.player.isDead()){
//            this.isGameLive = false;
            if (upPressed) player.moveUp();
            if (downPressed) player.moveDown();
            if (leftPressed) player.moveLeft();
            if (rightPressed) player.moveRight();

            for (int i = 0; i < this.bullets.length; i++) {
                if (this.bullets[i] != null){

                    for (int j = 0; j < this.zombies.length; j++) {
                        if (zombies[j] != null && !zombies[j].isDead()){
                            if (this.bullets[i].checkCollision(new Rectangle((int) zombies[j].getX(), (int) zombies[j].getY(), zombies[j].getZombieWidth(), zombies[j].getZombieHeight()))){
                                zombies[j].bulletHit();
                                System.out.println("botHit!");
                                this.zombies[j].zombieHurt();
                            }
                        }
                    }

                    this.bullets[i].update();

                    if (this.bullets[i].isBulletOutOfScreen(width, height) || this.bullets[i].getIsHit()){
                        this.bullets[i] = null;
                    }
                }

            }
        } else{
            endGameText.ChangeVisible(true);
            endGameText.ChangeVisible(true);

            if (!isEndSequenceStarted) {
                isEndSequenceStarted = true;
                new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    SwingUtilities.invokeLater(() -> {
                        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                        if (topFrame != null) {
                            topFrame.getContentPane().removeAll();
                            MainMenu mainMenu = new MainMenu(0, 0, topFrame.getWidth(), topFrame.getHeight());
                            topFrame.add(mainMenu);
                            topFrame.revalidate();
                            topFrame.repaint();
                            mainMenu.requestFocusInWindow();
                        }
                    });
                }).start();
            }
        }




        for (int i = 0; i < this.zombies.length; i++) {
            if (this.zombies[i] != null){
                this.zombies[i].move(this.player.getX(), this.player.getY());
                if (!this.player.getIsHitted()){
                    if (this.zombies[i].checkCollision(new Rectangle(this.player.getX(), this.player.getY() ,this.player.getPlayerWidth(), this.player.getPlayerHeight()))){
                        System.out.println("HIT!");
                        this.player.hurt(this.zombies[i].getHitDamage());
                        this.hudPanel.hurt(this.zombies[i].getHitDamage());
                    }
                }

                if (zombies[i].getHitCounter() == 0 && !zombies[i].isCountedAsKill()){
                    this.zombies[i].setCountedAsKill(true);
                    this.player.playerKill();
                    this.killsHud.updateKills();
                }
            }
        }

    }
}
