import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    private static BufferedImage background;
    private static BufferedImage menuBackground;
    private static BufferedImage startButton;
    private static BufferedImage pauseBackground;
    private static BufferedImage backBackground;


    public static void loadBackground() {
        try {
            background = ImageIO.read(new File("resources/MainBackground/newBackground3.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static BufferedImage getBackground() {
        return background;
    }

    public static void loadMenuBackground() {
        try {
            menuBackground = ImageIO.read(new File("resources/MainMenu/background.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static BufferedImage getMenuBackground() {
        return menuBackground;
    }

    public static void loadPauseBackground() {
        try {
            pauseBackground = ImageIO.read(new File("resources/PausePanel/background2.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static BufferedImage getPauseBackground() {
        return pauseBackground;
    }

    public static void loadRulesBackground() {
        try {
            backBackground = ImageIO.read(new File("resources/RulesMenu/HowToPlayBackground.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static BufferedImage getRulesBackground() {
        return backBackground;
    }

//    public static void loadStartButton() {
//        try {
//            startButton = ImageIO.read(new File("resources/MainMenu/OldStartButton.png"));
//        } catch (IOException e) {
//            System.out.println("Error");
//        }
//    }
//
//    public static BufferedImage getStartButton() {
//        return startButton;
//    }

    public static BufferedImage[] loadPlayerImage() {
        BufferedImage[] walkPlayerFrames = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            try {
                 walkPlayerFrames[i] = ImageIO.read(new File("resources/player/walk/Walk_riffle_00" + i + ".png"));
               // walkPlayerFrames[i] = ImageIO.read(new File("resources/player/withShield/walkShield_00" + i + ".png"));

            } catch (IOException e) {
                System.out.println("Error with player image.");
            }
        }
        return walkPlayerFrames;
    }

    public static BufferedImage[] loadPlayerDeathImage() {
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

    public static BufferedImage[] loadZombieImage() {
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

    public static BufferedImage[] loadZombieDeathImage() {
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

    public static BufferedImage[] loadZombieBossLvl1Image() {
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

    public static BufferedImage[] loadZombieDeathBossLvl1Image() {
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

    public static BufferedImage[] loadZombieLvl2Image() {
        BufferedImage[] walkZombieLvl2Frames = new BufferedImage[9];
        for (int i = 0; i < 9; i++) {
            try {
                walkZombieLvl2Frames[i] = ImageIO.read(new File("resources/Zombies/Tier2Regular/Walk/walk_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie lvl 2 walk image.");
            }
        }
        return walkZombieLvl2Frames;
    }

    public static BufferedImage[] loadZombieLvl2DeathImage() {
        BufferedImage[] deathZombieLvl2Frames = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            try {
                deathZombieLvl2Frames[i] = ImageIO.read(new File("resources/Zombies/Tier2Regular/Death/death_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie lvl 2 death image.");
            }
        }
        return deathZombieLvl2Frames;
    }

    public static BufferedImage[] loadZombieLvl2BossImage() {
        BufferedImage[] walkZombieLvl2BossFrames = new BufferedImage[9];
        for (int i = 0; i < 9; i++) {
            try {
                walkZombieLvl2BossFrames[i] = ImageIO.read(new File("resources/Zombies/Tier2Boss/Walk/walk_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie boss lvl 2 Boss walk image.");
            }
        }
        return walkZombieLvl2BossFrames;
    }

    public static BufferedImage[] loadZombieLvl2BossDeathImage() {
        BufferedImage[] deathZombieLvl2BossFrames = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            try {
                deathZombieLvl2BossFrames[i] = ImageIO.read(new File("resources/Zombies/Tier2Boss/Death/death_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie boss lvl 2 Boss death image.");
            }
        }
        return deathZombieLvl2BossFrames;
    }

    public static BufferedImage[] loadZombieLvl3BossImage() {
        BufferedImage[] walkZombieLvl3BossFrames = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            try {
                walkZombieLvl3BossFrames[i] = ImageIO.read(new File("resources/Zombies/Tier3Boss/Walk/walk_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie boss lvl 3 walk image.");
            }
        }
        return walkZombieLvl3BossFrames;
    }

    public static BufferedImage[] loadZombieLvl3BossDeathImage() {
        BufferedImage[] deathZombieLvl3BossFrames = new BufferedImage[10];
        for (int i = 0; i < 10; i++) {
            try {
                deathZombieLvl3BossFrames[i] = ImageIO.read(new File("resources/Zombies/Tier3Boss/Death/death_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie boss lvl 3 death image.");
            }
        }
        return deathZombieLvl3BossFrames;
    }

    public static BufferedImage[] loadZombieLvl4BossImage() {
        BufferedImage[] walkZombieLvl4BossFrames = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            try {
                walkZombieLvl4BossFrames[i] = ImageIO.read(new File("resources/Zombies/Tier4Boss/Walk/walk_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie boss lvl 4 walk image.");
            }
        }
        return walkZombieLvl4BossFrames;
    }

    public static BufferedImage[] loadZombieLvl4BossDeathImage() {
        BufferedImage[] deathZombieLvl4BossFrames = new BufferedImage[10];
        for (int i = 0; i < 10; i++) {
            try {
                deathZombieLvl4BossFrames[i] = ImageIO.read(new File("resources/Zombies/Tier4Boss/Death/death_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie boss lvl 4 death image.");
            }
        }
        return deathZombieLvl4BossFrames;
    }

    public static BufferedImage[] loadZombieLvl5BossImage() {
        BufferedImage[] walkZombieLvl5BossFrames = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            try {
                walkZombieLvl5BossFrames[i] = ImageIO.read(new File("resources/Zombies/Tier5Boss/Walk/Walk_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie boss lvl 5 walk image.");
            }
        }
        return walkZombieLvl5BossFrames;
    }

    public static BufferedImage[] loadZombieLvl5BossDeathImage() {
        BufferedImage[] deathZombieLvl5BossFrames = new BufferedImage[14];
        for (int i = 0; i < 14; i++) {
            try {
                if (i >= 10){
                    deathZombieLvl5BossFrames[i] = ImageIO.read(new File("resources/Zombies/Tier5Boss/Death/Death_0" + i + ".png"));
                } else {
                    deathZombieLvl5BossFrames[i] = ImageIO.read(new File("resources/Zombies/Tier5Boss/Death/Death_00" + i + ".png"));
                }
            } catch (IOException e) {
                System.out.println("Error with zombie boss lvl 5 death image.");

            }
        }
        return deathZombieLvl5BossFrames;
    }

    //ABILITIES
    private static BufferedImage healthAbility;
    private static BufferedImage speedAbility;
    private static BufferedImage shieldAbility;


    public static void loadHealthAbility() {
        healthAbility = null;
            try {
                healthAbility = ImageIO.read(new File("resources/Abilities/healthAbility.png"));
            } catch (IOException e) {
                System.out.println("Error loading healthAbility image.");
            }

    }

    public static BufferedImage getHealthAbility() {
        return healthAbility;
    }


    public static void loadSpeedAbility() {
        speedAbility = null;
        try {
            speedAbility = ImageIO.read(new File("resources/Abilities/speedAbility.png"));
        } catch (IOException e) {
            System.out.println("Error loading speedAbility image.");
        }

    }

    public static BufferedImage getSpeedAbility() {
        return speedAbility;
    }

    public static void loadShieldAbility() {
        shieldAbility = null;
        try {
            shieldAbility = ImageIO.read(new File("resources/Abilities/shiledAbility.png"));
        } catch (IOException e) {
            System.out.println("Error loading shieldAbility image.");
        }

    }

    public static BufferedImage getShieldAbility() {
        return shieldAbility;
    }
}
