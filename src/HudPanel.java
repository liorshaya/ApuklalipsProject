import javax.swing.*;
import java.awt.*;

public class HudPanel extends JPanel {
    private Player player;
    private int kills;
    private int health;
    private int bullets;
    private String magazine;
    private int helathBarWidth;
    private JLabel healthText;
    private JLabel ammo;

    public HudPanel(Player player, int x, int y, int width, int height){
        this.player = player;
        this.setOpaque(false);
        this.setBounds(x, y, width, height);
        this.setLayout(null);


        this.kills = 0;
        this.health = 100;
        this.bullets = 30;
        this.magazine = "\u221E";
        this.helathBarWidth = 150;

        this.healthText = new JLabel("HP");
        this.healthText.setForeground(Color.WHITE);
        this.healthText.setBounds(10,0,100,20);
        this.healthText.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(this.healthText);

        this.ammo = new JLabel(this.bullets + "/" + this.magazine);
        this.ammo.setForeground(Color.WHITE);
        this.ammo.setBounds(220,0,100,20);
        this.ammo.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(this.ammo);
    }

    public void shoot(){
        this.bullets = player.getBulletsLeft();
        this.ammo.setText(this.bullets + "/" + this.magazine);
    }

    public void hurt(int hitCount){
        this.health -= hitCount;
        this.helathBarWidth -= (int) (hitCount * 1.5);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillRect(50,0,this.helathBarWidth,20);
    }
}
