import javax.swing.*;
import java.awt.*;

public class KillsHud extends JLabel {
    private int killCounter;
    private Player player;

    public KillsHud(int x, int y, int width, int height, Player player){
        this.player = player;
        this.killCounter = 0;

        this.setFont(new Font("Arial", Font.BOLD, 25));
        this.setForeground(Color.WHITE);
        this.setBounds(x, y, width, height);
        this.setText("Kills: " + this.killCounter);
    }

    public void updateKills(){
        this.killCounter = this.player.getKills();
        this.setText("Kills: " + this.killCounter);
    }
}
