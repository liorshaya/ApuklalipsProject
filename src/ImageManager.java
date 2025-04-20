import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    private static BufferedImage background;
    private static BufferedImage menuBackground;
    private static BufferedImage startButton;
    private static BufferedImage pauseBackground;

    public static void loadBackground(){
        try {
            background = ImageIO.read(new File("resources/MainBackground/background.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static BufferedImage getBackground(){
        return background;
    }

    public static void loadMenuBackground(){
        try {
            menuBackground = ImageIO.read(new File("resources/MainMenu/background.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static BufferedImage getMenuBackground(){
        return menuBackground;
    }

    public static void loadPauseBackground(){
        try {
            pauseBackground = ImageIO.read(new File("resources/PausePanel/background2.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static BufferedImage getPauseBackground(){
        return pauseBackground;
    }

    public static void loadStartButton(){
        try {
            startButton = ImageIO.read(new File("resources/MainMenu/startButton.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
    public static BufferedImage getStartButton(){
        return startButton;
    }

    public static BufferedImage[] loadPlayerImage(){
        BufferedImage[] walkPlayerFrames = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            try {
                walkPlayerFrames[i] = ImageIO.read(new File("resources/player/walk/Walk_riffle_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with player image.");
            }
        }
        return walkPlayerFrames;
    }

    public static BufferedImage[] loadPlayerDeathImage(){
        BufferedImage[] walkPlayerDeathFrames = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            try {
                walkPlayerDeathFrames[i] = ImageIO.read(new File("resources/player/Death/death_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie death image.");
            }
        }
        return walkPlayerDeathFrames;
    }

    public static BufferedImage[] loadZombieImage(){
        BufferedImage[] walkZombieFrames = new BufferedImage[9];
        for (int i = 0; i < 9; i++) {
            try {
                walkZombieFrames[i] = ImageIO.read(new File("resources/Zombies/Tier1Regular/Walk/Walk_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie walk image.");
            }
        }
        return walkZombieFrames;
    }

    public static BufferedImage[] loadZombieDeathImage(){
        BufferedImage[] walkZombieDeathFrames = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            try {
                walkZombieDeathFrames[i] = ImageIO.read(new File("resources/Zombies/Tier1Regular/Death/Death_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie death image.");
            }
        }
        return walkZombieDeathFrames;
    }

    public static BufferedImage[] loadZombieBossLvl1Image(){
        BufferedImage[] walkZombieBossLvl1Frames = new BufferedImage[9];
        for (int i = 0; i < 9; i++) {
            try {
                walkZombieBossLvl1Frames[i] = ImageIO.read(new File("resources/Zombies/Tier1Boss/Walk/walk_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie boss lvl 1 walk image.");
            }
        }
        return walkZombieBossLvl1Frames;
    }

    public static BufferedImage[] loadZombieDeathBossLvl1Image(){
        BufferedImage[] deathZombieBossLvl1Frames = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            try {
                deathZombieBossLvl1Frames[i] = ImageIO.read(new File("resources/Zombies/Tier1Boss/Death/death_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie boss lvl 1 death image.");
            }
        }
        return deathZombieBossLvl1Frames;
    }

    public static BufferedImage[] loadZombieLvl2Image(){
        BufferedImage[] walkZombieLvl2Frames = new BufferedImage[9];
        for (int i = 0; i < 9; i++) {
            try {
                walkZombieLvl2Frames[i] = ImageIO.read(new File("resources/Zombies/Tier2Regular/Walk/walk_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie boss lvl 1 walk image.");
            }
        }
        return walkZombieLvl2Frames;
    }

    public static BufferedImage[] loadZombieLvl2DeathImage(){
        BufferedImage[] deathZombieLvl2Frames = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            try {
                deathZombieLvl2Frames[i] = ImageIO.read(new File("resources/Zombies/Tier2Regular/Death/death_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie boss lvl 1 death image.");
            }
        }
        return deathZombieLvl2Frames;
    }
}
