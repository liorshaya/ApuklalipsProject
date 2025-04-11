import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    private static BufferedImage background;

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

    public static BufferedImage[] loadZombieImage(){
        BufferedImage[] walkZombieFrames = new BufferedImage[9];
        for (int i = 0; i < 9; i++) {
            try {
                walkZombieFrames[i] = ImageIO.read(new File("resources/Zombie/Walk/Walk_00" + i + ".png"));
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
                walkZombieDeathFrames[i] = ImageIO.read(new File("resources/Zombie/Death/Death_00" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error with zombie death image.");
            }
        }
        return walkZombieDeathFrames;
    }
}
