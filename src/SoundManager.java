import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SoundManager {

    private static Clip gunClip;
    private static Clip hurtClip;
    private static Clip deathClip;


    public static void loadAll(){
        loadShootSound();
        loadHurtSound();
        loadDeathSound();
    }

    public static void loadShootSound(){
        try {
            File shootSound = new File("resources/Sounds/SoundShoot.wav");
            AudioInputStream shot = AudioSystem.getAudioInputStream(shootSound);
            gunClip = AudioSystem.getClip();
            gunClip.open(shot);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public static void loadHurtSound(){
        try {
            File shootSound = new File("resources/Sounds/hitSound.wav");
            AudioInputStream hurt = AudioSystem.getAudioInputStream(shootSound);
            hurtClip = AudioSystem.getClip();
            hurtClip.open(hurt);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public static void loadDeathSound(){
        try {
            File deathSound = new File("resources/Sounds/deathSound.wav");
            AudioInputStream dead = AudioSystem.getAudioInputStream(deathSound);
            deathClip = AudioSystem.getClip();
            deathClip.open(dead);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public static void playShootSound() {
        if (gunClip == null) return;
        if (gunClip.isRunning()) {
            gunClip.stop();
        }
        gunClip.setFramePosition(0);
        gunClip.start();
    }

    public static void playHurtSound() {
        if (hurtClip == null) return;
        if (hurtClip.isRunning()) {
            hurtClip.stop();
        }
        hurtClip.setFramePosition(0);
        hurtClip.start();
    }

    public static void playDeathSound() {
        if (deathClip == null) return;
        if (deathClip.isRunning()) {
            deathClip.stop();
        }
        System.out.println("death sound");
        deathClip.setFramePosition(0);
        deathClip.start();
    }
}
