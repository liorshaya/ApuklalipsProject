import javax.swing.*;
import java.awt.*;

public class Timer extends JLabel {
    private int seconds;
    private int minutes;

    private boolean isPaused = false;

    public Timer(int x, int y, int width, int height){
        this.seconds = 0;
        this.minutes = 0;

        this.setForeground(Color.WHITE);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Arial", Font.BOLD, 40));

        this.startTimer();
    }

    public void startTimer(){
        new Thread(() -> {
            while (true) {
                if (isPaused) {
                    try {
                        Thread.sleep(100);
                        continue;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    this.seconds++;
                    if (this.seconds == 60) {
                        this.seconds = 0;
                        this.minutes++;
                    }

                    String textTime = String.format("%02d : %02d", this.minutes, this.seconds);
                    this.setText(textTime);
                }
            }

        }).start();
    }

    public void setPaused(boolean paused){
        this.isPaused = paused;
    }
}
