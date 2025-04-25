import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MainScene extends JPanel {
    private final Random rnd = new Random();

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


    private int nextLvl1Spawn     = 2_500;    // כל 2.5 שניות
    private final int lvl1Interval = 2_500;

    private int nextBoss1Spawn = 20_000;         // מתחיל ב–20s
    private final int boss1Min = 10_000, boss1Max = 20_000;

    private int nextLvl2Spawn = 25_000;          // מתחיל ב–25s
    private final int lvl2Min = 7_000,  lvl2Max = 17_000;

    private int nextBoss2Spawn     = 60_000; // כל 60s
    private final int Boss2Interval = 60_000;

    private int nextBoss3Spawn     = 127_000;// כל 127s
    private final int Boss3Interval = 127_000;

    private int nextBoss4Spawn     = 215_000;// כל 215s
    private final int Boss4Interval = 215_000;

    private int nextBoss5Spawn     = 215_000;// כל 215s
    private final int Boss5Interval = 215_000;


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

        this.startSpawnerThread();

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
                    Thread.sleep(7);
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


//    public void zombieSpawner(){
//        Random rnd = new Random();
//
//        new Thread(() -> {
//            while (true) {
//                if (isPaused) {
//                    try {
//                        Thread.sleep(1000);
//                        continue;
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                } else {
//
//                    try {
//                        Thread.sleep(2500);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    for (int i = 0; i < this.zombies.length; i++) {
//                        if (this.zombies[i] == null){
//                            int[] randomLocations = randomSpawn();
//                            ZombieLvl1 zombie = new ZombieLvl1(randomLocations[0], randomLocations[1], this.width, this.height);
//                            this.zombies[i] = zombie;
//                            break;
//                        }
//                    }
//                }
//            }
//        }).start();
//
//            new Thread(() -> {
//                try {
//                    Thread.sleep(20000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                while (true){
//                    if (isPaused) {
//                        try {
//                            Thread.sleep(1000);
//                            continue;
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    } else {
//                        for (int i = 0; i < this.zombies.length; i++) {
//                            if (this.zombies[i] == null) {
//                                int[] randomLocations = randomSpawn();
//                                ZombieLvl1Boss zombieBoss = new ZombieLvl1Boss(randomLocations[0], randomLocations[1], this.width, this.height);
//                                this.zombies[i] = zombieBoss;
//                                break;
//                            }
//                        }
//                        try {
//                            Thread.sleep(rnd.nextInt(10000, 20000));
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//            }).start();
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(25000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            while (true){
//                if (isPaused) {
//                    try {
//                        Thread.sleep(1000);
//                        continue;
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                } else {
//                    for (int i = 0; i < this.zombies.length; i++) {
//                        if (this.zombies[i] == null) {
//                            int[] randomLocations = randomSpawn();
//                            ZombieLvl2 zombieLvl2 = new ZombieLvl2(randomLocations[0], randomLocations[1], this.width, this.height);
//                            this.zombies[i] = zombieLvl2;
//                            break;
//                        }
//                    }
//                    try {
//                        Thread.sleep(rnd.nextInt(7000, 17000));
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            while (true){
//                if (isPaused) {
//                    try {
//                        Thread.sleep(1000);
//                        continue;
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                } else {
//                    try {
//                        Thread.sleep(60000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    for (int i = 0; i < this.zombies.length; i++) {
//                        if (this.zombies[i] == null) {
//                            int[] randomLocations = randomSpawn();
//                            ZombieLvl2Boss zombieLvl2Boss = new ZombieLvl2Boss(randomLocations[0], randomLocations[1], this.width, this.height);
//                            this.zombies[i] = zombieLvl2Boss;
//
//                            break;
//                        }
//                    }
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            while (true){
//                if (isPaused) {
//                    try {
//                        Thread.sleep(1000);
//                        continue;
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                } else {
//                    try {
//                        Thread.sleep(127000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    for (int i = 0; i < this.zombies.length; i++) {
//                        if (this.zombies[i] == null) {
//                            int[] randomLocations = randomSpawn();
//                            ZombieLvl3Boss zombieLvl3Boss = new ZombieLvl3Boss(randomLocations[0], randomLocations[1], this.width, this.height);
//                            this.zombies[i] = zombieLvl3Boss;
//
//                            break;
//                        }
//                    }
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            while (true){
//                if (isPaused) {
//                    try {
//                        Thread.sleep(1000);
//                        continue;
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                } else {
//                    try {
//                        Thread.sleep(215000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    for (int i = 0; i < this.zombies.length; i++) {
//                        if (this.zombies[i] == null) {
//                            int[] randomLocations = randomSpawn();
//                            ZombieLvl4Boss zombieLvl4Boss = new ZombieLvl4Boss(randomLocations[0], randomLocations[1], this.width, this.height);
//                            this.zombies[i] = zombieLvl4Boss;
//
//                            break;
//                        }
//                    }
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            while (true){
//                if (isPaused) {
//                    try {
//                        Thread.sleep(1000);
//                        continue;
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                } else {
//                    try {
//                        Thread.sleep(21000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    for (int i = 0; i < this.zombies.length; i++) {
//                        if (this.zombies[i] == null) {
//                            int[] randomLocations = randomSpawn();
//                            ZombieLvl5Boss zombieLvl5Boss = new ZombieLvl5Boss(randomLocations[0], randomLocations[1], this.width, this.height);
//                            this.zombies[i] = zombieLvl5Boss;
//
//                            break;
//                        }
//                    }
//                }
//            }
//        }).start();
//
//    }

    private void startSpawnerThread() {
        new Thread(() -> {
            int delta = 500;

            while (isGameLive) {
                if (!isPaused) {

                    this.nextLvl1Spawn -= delta;
                    if (this.nextLvl1Spawn <= 0) {
                        int[] randomLocations = randomSpawn();
                        spawn(new ZombieLvl1(randomLocations[0], randomLocations[1], width, height));
                        this.nextLvl1Spawn = lvl1Interval;
                    }
                    this.nextBoss1Spawn -= delta;
                    if (this.nextBoss1Spawn <= 0) {
                        System.out.println("BOSS1");
                        int[] randomLocations = randomSpawn();
                        spawn(new ZombieLvl1Boss(randomLocations[0], randomLocations[1], width, height));
                        this.nextBoss1Spawn = rnd.nextInt(boss1Min, boss1Max);
                    }
                    this.nextLvl2Spawn -= delta;
                    if (this.nextLvl2Spawn <= 0) {
                        int[] randomLocations = randomSpawn();
                        spawn(new ZombieLvl2(randomLocations[0], randomLocations[1], width, height));
                        this.nextLvl2Spawn = rnd.nextInt(lvl2Min, lvl2Max);
                    }
                    this.nextBoss2Spawn -= delta;
                    if (this.nextBoss2Spawn <= 0) {
                        int[] randomLocations = randomSpawn();
                        spawn(new ZombieLvl2Boss(randomLocations[0], randomLocations[1], width, height));
                        this.nextBoss2Spawn = Boss2Interval;
                    }
                    this.nextBoss3Spawn -= delta;
                    if (this.nextBoss3Spawn <= 0) {
                        int[] randomLocations = randomSpawn();
                        spawn(new ZombieLvl3Boss(randomLocations[0], randomLocations[1], width, height));
                        this.nextBoss3Spawn = Boss3Interval;
                    }
                    this.nextBoss4Spawn -= delta;
                    if (this.nextBoss4Spawn <= 0) {
                        int[] randomLocations = randomSpawn();
                        spawn(new ZombieLvl3Boss(randomLocations[0], randomLocations[1], width, height));
                        this.nextBoss4Spawn = Boss4Interval;
                    }
                    this.nextBoss5Spawn -= delta;
                    if (this.nextBoss5Spawn <= 0) {
                        int[] randomLocations = randomSpawn();
                        spawn(new ZombieLvl3Boss(randomLocations[0], randomLocations[1], width, height));
                        this.nextBoss5Spawn = Boss5Interval;
                    }


                }
                try {
                    Thread.sleep(delta);
                } catch (InterruptedException ignored){}
            }
        }).start();
    }

    public void spawn(Zombie z) {
        for (int i = 0; i < zombies.length; i++) {
            if (zombies[i] == null) {
                zombies[i] = z;
                break;
            }
        }
    }

    public int[] randomSpawn(){
        Random rnd = new Random();
        int pickPlace = rnd.nextInt(0,4);
        int[] randoms = new int[2];
//        System.out.println("pickPlace" + pickPlace);
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
//                                System.out.println("botHit!");
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

            if (!isEndSequenceStarted) {
                isEndSequenceStarted = true;
                new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    this.isGameLive = false;

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
//                        System.out.println("HIT!");
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
